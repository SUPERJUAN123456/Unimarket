package co.edu.uniquindio.unimarket.services.interfaces;


import co.edu.uniquindio.unimarket.dto.TransactionDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Transaction;
import co.edu.uniquindio.unimarket.model.entities.TransactionDetail;

import java.util.List;

public interface TransactionInterface {

    int createTransaction(TransactionDTO transactionDTO) throws Exception;
    List<TransactionGetDTO> listTransactionByPerson(String idPerson);
    TransactionGetDTO getTransactionDTO(int idTransaction) throws Exception;
    Transaction getTransaction (int idTransaction) throws Exception;
    float calculateTotalPrice(List<TransactionDetail> transactionDetails) throws Exception;
}
