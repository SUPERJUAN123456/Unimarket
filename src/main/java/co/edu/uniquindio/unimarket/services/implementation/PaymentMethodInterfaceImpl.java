package co.edu.uniquindio.unimarket.services.implementation;
import co.edu.uniquindio.unimarket.dto.PaymentMethodDTO;
import co.edu.uniquindio.unimarket.dto.PaymentMethodGetDTO;
import co.edu.uniquindio.unimarket.model.entities.PaymentMethod;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import co.edu.uniquindio.unimarket.repositories.PaymentMethodRepo;
import co.edu.uniquindio.unimarket.services.interfaces.PaymentMethodInterface;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodInterfaceImpl implements PaymentMethodInterface {

    @Autowired
    PaymentMethodRepo paymentMethodRepo;
    @Autowired
    PersonInterface personInterface;

    @Override
    public int createPaymentMethod(PaymentMethodDTO paymentMethodDTO) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findByCardNumber(paymentMethodDTO.getCardNumber());

        if(foundPaymentMethod.isPresent()){
            throw new Exception("Ya existe un metodo de pago con el numero " + paymentMethodDTO.getCardNumber());
        }

        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setBankingEntity(paymentMethodDTO.getBankingEntity());
        paymentMethod.setCvv(paymentMethodDTO.getCvv());
        paymentMethod.setCardNumber(paymentMethodDTO .getCardNumber());
        paymentMethod.setExpirationDate(paymentMethodDTO.getExpirationDate());
        paymentMethod.setTitularName(paymentMethodDTO.getTitularName());
        paymentMethod.setUser(personInterface.getPerson(paymentMethodDTO.getIdPerson()));
        paymentMethod.setState(true);

        return paymentMethodRepo.save(paymentMethod).getId();
    }

    @Override
    public int updatePaymentMethod(int paymentMethodId, PaymentMethodGetDTO paymentMethodGetDTO) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(paymentMethodId);

        if(foundPaymentMethod.isEmpty()){
            throw new Exception("El metodo de pago con el id "+ paymentMethodId + " no existe");
        }

        PaymentMethod paymentMethodUpdate = foundPaymentMethod.get();

        paymentMethodUpdate.setBankingEntity(paymentMethodGetDTO.getBankingEntity());
        paymentMethodUpdate.setCvv(paymentMethodGetDTO.getCvv());
        paymentMethodUpdate.setCardNumber(paymentMethodGetDTO.getCardNumber());
        paymentMethodUpdate.setExpirationDate(paymentMethodGetDTO.getExpirationDate());
        paymentMethodUpdate.setTitularName(paymentMethodGetDTO.getTitularName());

        return paymentMethodRepo.save(paymentMethodUpdate).getId();
    }

    @Override
    public int deletePaymentMethod(int idPaymentMethod) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idPaymentMethod);

        if(foundPaymentMethod.isEmpty()){
            throw new Exception("El metodo de pago con el id " + idPaymentMethod + " No existe");
        }

        PaymentMethod paymentMethod = foundPaymentMethod.get();

        paymentMethod.setState(false);

        paymentMethodRepo.save(paymentMethod);

        return idPaymentMethod;
    }

    @Override
    public List<PaymentMethodGetDTO> listPaymentMethodByPerson(String idPerson) {

        List<PaymentMethod> listPaymentMethodByPerson = paymentMethodRepo.findByPerson(idPerson);
        List<PaymentMethodGetDTO> listPaymentMethodDTOByPerson = new ArrayList<>();

        for (PaymentMethod paymentMethod: listPaymentMethodByPerson) {
            listPaymentMethodDTOByPerson.add(convertToGetDTO(paymentMethod));
        }

        return listPaymentMethodDTOByPerson;
    }

    private PaymentMethodGetDTO convertToGetDTO(PaymentMethod paymentMethod) {

        PaymentMethodGetDTO paymentMethodGetDTO = new PaymentMethodGetDTO(
                paymentMethod.getId(),
                paymentMethod.getBankingEntity(),
                paymentMethod.getTitularName(),
                paymentMethod.getCardNumber(),
                paymentMethod.getExpirationDate(),
                paymentMethod.getCvv(),
                paymentMethod.isState(),
                paymentMethod.getUser().getId()
        );

        return paymentMethodGetDTO;
    }

    @Override
    public PaymentMethodGetDTO getPaymentMethodDTO(int idPaymentMethod) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idPaymentMethod);

        if (foundPaymentMethod.isEmpty()) {
            throw new Exception("No existe un metodo de pago con el id " + idPaymentMethod);
        }

        PaymentMethod paymentMethod = foundPaymentMethod.get();
        return convertToGetDTO(paymentMethod);

    }

    @Override
    public PaymentMethod getPaymentMethod(int idProduct) throws Exception {
        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idProduct);

        if (foundPaymentMethod.isEmpty()) {
            throw new Exception("No existe un metodo de pago con el id " + idProduct);
        }

        PaymentMethod paymentMethod = foundPaymentMethod.get();
        return paymentMethod;
    }
}
