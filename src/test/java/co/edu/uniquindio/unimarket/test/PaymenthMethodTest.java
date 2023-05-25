package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.PaymentMethodDTO;
import co.edu.uniquindio.unimarket.dto.PaymentMethodGetDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.PaymentMethodInterface;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class PaymenthMethodTest {

    @Autowired
    PaymentMethodInterface paymentMethodInterface;

    @Test
    @Sql("classpath:dataset.sql")
    public void createPaymentMethod() throws Exception {

        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(
                "Andres Felipe Castro Cardona",
                "BBVA",
                "12030123012",
                LocalDate.now().plusYears(2),
                787,
                "1004684293"
        );

       int idPaymentMethod = paymentMethodInterface.createPaymentMethod(paymentMethodDTO);

       Assertions.assertEquals("12030123012",paymentMethodInterface.getPaymentMethod(idPaymentMethod).getCardNumber());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void updatePaymentMethod() throws Exception {

        int idPaymentMethod = 1;

        PaymentMethodGetDTO paymentMethodGetDTO = new PaymentMethodGetDTO(
                1,
                "BBVA",
                "Andres Felipe Castro Cardona",
                "673812399",
                LocalDate.now().plusYears(2),
                879,
                true,
                "1004684293"
        );

        paymentMethodInterface.updatePaymentMethod(idPaymentMethod,paymentMethodGetDTO);

        PaymentMethodGetDTO paymentMethodGetDTONew = paymentMethodInterface.getPaymentMethodDTO(idPaymentMethod);

        Assertions.assertEquals("673812399",paymentMethodGetDTONew.getCardNumber());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void deletePaymentMethod() throws Exception {

        paymentMethodInterface.deletePaymentMethod(1);

        PaymentMethodGetDTO paymentMethodGetDTO = paymentMethodInterface.getPaymentMethodDTO(1);

        Assertions.assertEquals(false,paymentMethodGetDTO.isState());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listPaymentMethodByPerson() {

        List<PaymentMethodGetDTO> listPaymentMethods = paymentMethodInterface.listPaymentMethodByPerson("1004684293");
        Assertions.assertFalse(listPaymentMethods.isEmpty());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void getPaymentMethod() throws Exception {
        PaymentMethodGetDTO paymentMethodGetDTO = paymentMethodInterface.getPaymentMethodDTO(1);
        Assertions.assertNotNull(paymentMethodGetDTO);
    }


}
