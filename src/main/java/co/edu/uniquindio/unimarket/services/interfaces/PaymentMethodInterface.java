package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.PaymentMethodDTO;
import co.edu.uniquindio.unimarket.dto.PaymentMethodGetDTO;
import co.edu.uniquindio.unimarket.model.entities.PaymentMethod;

import java.util.List;

public interface PaymentMethodInterface {

    int createPaymentMethod(PaymentMethodDTO paymentMethodDTO) throws Exception;
    int updatePaymentMethod(int paymentMethodId, PaymentMethodGetDTO paymentMethodGetDTO) throws Exception;
    int deletePaymentMethod(int paymentMethodId) throws Exception;
    List<PaymentMethodGetDTO> listPaymentMethodByPerson(String idPerson);
    PaymentMethodGetDTO getPaymentMethodDTO (int idPaymentMethod) throws Exception;
    PaymentMethod getPaymentMethod(int idPaymentMethod) throws Exception;

}
