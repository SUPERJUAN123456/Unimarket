package co.edu.uniquindio.unimarket.services.implementation;


import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.model.entities.Transaction;
import co.edu.uniquindio.unimarket.model.entities.TransactionDetail;
import co.edu.uniquindio.unimarket.repositories.TransactionDetailRepo;
import co.edu.uniquindio.unimarket.repositories.TransactionRepo;
import co.edu.uniquindio.unimarket.services.interfaces.EmailInterface;
import co.edu.uniquindio.unimarket.services.interfaces.ProductInterface;
import co.edu.uniquindio.unimarket.services.interfaces.TransactionDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionDetailImpl implements TransactionDetailInterface {
    @Autowired
    private ProductInterface productInterface;
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    private TransactionDetailRepo transactionDetailRepo;
    @Autowired
    EmailInterface emailInterface;

    @Override
    public TransactionDetail createTransactionDetail(TransactionDetailDTO transactionDetailDTO, Transaction transaction) throws Exception {

        TransactionDetail transactionDetail = new TransactionDetail();

        Product product = productInterface.getProduct(transactionDetailDTO.getIdProduct());
        int unities = transactionDetailDTO.getUnities();

        if(product.getUnities()<unities){
            throw new Exception("No existen las suficientes unidades del producto " + product.getTitle());
        }
        transactionDetail.setTransaction(transaction);
        transactionDetail.setProduct(product);
        transactionDetail.setUnities(unities);
        transactionDetail.setPrice(calculatePrice(product,unities));

        transactionDetailRepo.save(transactionDetail);
        String destinario=product.getUser().getEmail();
        emailInterface.sendEmail(new EmailDTO("Transaccion realizada","Ha vendido " + unities + " unidades de su producto " + product.getTitle() , destinario));

        productInterface.reduceUnities(product,unities);

        return transactionDetail;
    }

    private float calculatePrice(Product product, int unities) {

        float priceTotal = product.getPrice()*unities;

        return priceTotal;
    }
    @Override
    public List<TransactionDetailGetDTO> listTransactionDetailByTransaction(int idTransaction) {
        List<TransactionDetail> listTransactionDetailByTransaction = transactionDetailRepo.findByTransaction(idTransaction);
        List<TransactionDetailGetDTO> listTransactionDetailDTOByTransaction = new ArrayList<>();

        for (TransactionDetail transactionDetail: listTransactionDetailByTransaction) {
            listTransactionDetailDTOByTransaction.add(convertToGetDTO(transactionDetail));        }

        return listTransactionDetailDTOByTransaction;
    }

    @Override
    public TransactionDetailGetDTO getTransactionDetailDTO(int idTransactionDetail) throws Exception {
        Optional<TransactionDetail> foundTransactionDetail = transactionDetailRepo.findById(idTransactionDetail);

        if (foundTransactionDetail.isEmpty()) {
            throw new Exception("No existe un detalle transaccion con el id " + idTransactionDetail);
        }

        TransactionDetail transactionDetail = foundTransactionDetail.get();
        return convertToGetDTO(transactionDetail);
    }

    private TransactionDetailGetDTO convertToGetDTO(TransactionDetail transactionDetail) {

        TransactionDetailGetDTO transactionDetailGetDTO = new TransactionDetailGetDTO(
                transactionDetail.getProduct().getId(),
                transactionDetail.getId(),
                transactionDetail.getPrice(),
                transactionDetail.getUnities(),
                transactionDetail.getTransaction().getId()
        );

        return transactionDetailGetDTO;
    }

    @Override
    public TransactionDetail getTransactionDetail(int idTransactionDetail) throws Exception {
        Optional<TransactionDetail> foundTransactionDetail = transactionDetailRepo.findById(idTransactionDetail);

        if (foundTransactionDetail.isEmpty()) {
            throw new Exception("No existe un detalle transacción con el id " + idTransactionDetail);
        }

        TransactionDetail transactionDetail = foundTransactionDetail.get();
        return transactionDetail;
    }
}
