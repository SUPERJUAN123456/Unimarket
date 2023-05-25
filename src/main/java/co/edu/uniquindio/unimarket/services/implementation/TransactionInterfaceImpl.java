package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.*;
import co.edu.uniquindio.unimarket.model.entities.*;
import co.edu.uniquindio.unimarket.repositories.ProductRepo;
import co.edu.uniquindio.unimarket.repositories.TransactionDetailRepo;
import co.edu.uniquindio.unimarket.repositories.TransactionRepo;
import co.edu.uniquindio.unimarket.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionInterfaceImpl implements TransactionInterface {
    @Autowired
    private PaymentMethodInterface paymentMethodInterface;
    @Autowired
    private PersonInterface personInterface;
    @Autowired
    private ProductInterface productInterface;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionDetailInterface transactionDetailInterface;
    @Autowired
    TransactionDetailRepo transactionDetailRepo;

    @Autowired
    private EmailInterface emailInterface;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createTransaction(TransactionDTO transactionDTO) throws Exception {

        Transaction transaction = new Transaction();

        transaction.setPaymentMethod(paymentMethodInterface.getPaymentMethod(transactionDTO.getIdPaymentMethod()));
        Person person = personInterface.getPerson(transactionDTO.getIdPerson());
        transaction.setUser(person);
        transaction.setDate(LocalDate.now());

        List<TransactionDetail> transactionDetails = new ArrayList<>();

        float priceTotal=0;

        for (TransactionDetailDTO transactionDetailDTO:transactionDTO.getTransactionDetailDTOS()) {
            TransactionDetail transactionDetail = transactionDetailInterface.createTransactionDetail(transactionDetailDTO, transaction);
            transactionDetails.add(transactionDetail);
            priceTotal+=transactionDetail.getPrice();
        }

        transaction.setTransactionDetails(transactionDetails);
        transaction.setTotalPrice(priceTotal);
        transactionRepo.save(transaction);

        String infoDetails= "<p>" + person.getName() + " ha realizado su transaccion con un valor de $" + priceTotal + " con exito.</p>";
        infoDetails+="<h3>Detalles de la transacción:</h3>";

        for (TransactionDetail transactionDetail :transactionDetails) {
            infoDetails+="<p><b>Producto:</b> " + transactionDetail.getProduct().getTitle() + "  <b>unidades:</b> " + transactionDetail.getUnities() + "  <b>precio:</b> $" + transactionDetail.getPrice() + "</p>";
        }
        System.out.println(infoDetails);

        emailInterface.sendEmail(new EmailDTO("Transaccion realizada",infoDetails, person.getEmail()));

        return transaction.getId();
    }

    private String messageConfirmEmail(Transaction transaction) throws Exception {

        String infoDetails= "<p>" + transaction.getUser().getName() + " ha realizado su transaccion con exito.</p>";
        infoDetails+="<p>Con un valor de "+transaction.getTotalPrice() + "</p>";
        infoDetails+="<p>Detalles de la transacción:</p>";
        infoDetails+="";

        return infoDetails;
    }

    @Override
    public float calculateTotalPrice(List<TransactionDetail> transactionDetails) throws Exception {

        float totalPrice = 0;

        for (TransactionDetail transactionDetail:transactionDetails) {
            totalPrice+=transactionDetail.getPrice();
        }

        return totalPrice;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionGetDTO> listTransactionByPerson(String idPerson) {

        List<Transaction> transactionsByPerson = transactionRepo.listTransactionByPerson(idPerson);
        List<TransactionGetDTO> listTransactionsDTOByPerson = new ArrayList<>();

        for (Transaction transaction: transactionsByPerson) {
            listTransactionsDTOByPerson.add(convertToGetDTO(transaction));
        }

        return listTransactionsDTOByPerson;

    }

    private TransactionGetDTO convertToGetDTO(Transaction transaction) {

        TransactionGetDTO transactionGetDTO = new TransactionGetDTO(
                transaction.getUser().getId(),
                transaction.getId(),
                transaction.getDate(),
                transaction.getTotalPrice(),
                transaction.getPaymentMethod().getId(),
                transactionDetailInterface.listTransactionDetailByTransaction(transaction.getId())
        );

        return transactionGetDTO;
    }

    @Override
    public TransactionGetDTO getTransactionDTO(int idTransaction) throws Exception {

        Optional<Transaction> foundTransaction = transactionRepo.findById(idTransaction);

        if (foundTransaction.isEmpty()) {
            throw new Exception("No existe una transaccion con el id " + idTransaction);
        }

        Transaction transaction = foundTransaction.get();
        return convertToGetDTO(transaction);

    }

    @Override
    public Transaction getTransaction(int idTransaction) throws Exception {

        Optional<Transaction> foundTransaction = transactionRepo.findById(idTransaction);

        if (foundTransaction.isEmpty()) {
            throw new Exception("No existe una transaccion con el id " + idTransaction);
        }

        Transaction transaction = foundTransaction.get();
        return transaction;

    }
}
