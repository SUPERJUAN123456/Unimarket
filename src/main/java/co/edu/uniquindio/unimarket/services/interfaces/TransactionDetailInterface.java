package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.TransactionDetailDTO;
import co.edu.uniquindio.unimarket.dto.TransactionDetailGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Transaction;
import co.edu.uniquindio.unimarket.model.entities.TransactionDetail;

import java.util.List;

public interface TransactionDetailInterface {

    TransactionDetail createTransactionDetail(TransactionDetailDTO transactionDetailDTO, Transaction transaction) throws Exception;
    List<TransactionDetailGetDTO> listTransactionDetailByTransaction(int idTransaction);
    TransactionDetailGetDTO getTransactionDetailDTO(int idTransactionDetail) throws Exception;
    TransactionDetail getTransactionDetail(int idTransactionDetail) throws Exception;
}
