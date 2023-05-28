package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.TransactionDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.TransactionInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class TransactionTest {

    @Autowired
    private TransactionInterface transactionInterface;

    @Test
    @Sql("classpath:dataset.sql")
    public void createTransaction() throws Exception {

        List<TransactionDetailDTO> listTransactionDetails = new ArrayList<>();

        TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO(
                1,
                2
        );

        listTransactionDetails.add(transactionDetailDTO);

        TransactionDTO transactionDTO = new TransactionDTO(
                "1005088944",
                1,
                 listTransactionDetails
        );

        int idTransaction = transactionInterface.createTransaction(transactionDTO);

        Assertions.assertNotEquals(0, idTransaction);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listTransaction() {
        List<TransactionGetDTO> listTransaction = transactionInterface.listTransactionByPerson("1005088944");
        System.out.println(listTransaction);
        Assertions.assertFalse(listTransaction.isEmpty());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void getTransaction() throws Exception {
        TransactionGetDTO transactionGetDTO = transactionInterface.getTransactionDTO(1);
        Assertions.assertNotNull(transactionGetDTO);
    }

}
