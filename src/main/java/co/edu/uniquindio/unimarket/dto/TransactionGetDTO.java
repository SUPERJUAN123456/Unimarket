package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.PaymentMethod;
import co.edu.uniquindio.unimarket.model.entities.TransactionDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TransactionGetDTO {


    private String idPerson;
    private int id;
    private LocalDate date;
    private float totalPrice;
    private int  idPaymentMethod;
    private List<TransactionDetailGetDTO> transactionDetailGetDTOS;


}
