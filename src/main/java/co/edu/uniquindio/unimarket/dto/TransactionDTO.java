package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.PaymentMethod;
import co.edu.uniquindio.unimarket.model.entities.Person;
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
public class TransactionDTO {

    @NotNull(message = "El id del usuario no puede ser nulo")
    @NotBlank(message = "El id del usuario no puede ser vacío")
    private String idPerson;

    @NotNull(message = "El metodo de pago ser nulo")
    @NotBlank(message = "El metodo de pago no puede ser vacío")
    private int idPaymentMethod;

    @NotNull(message = "La lista de detalles transaccion no puede ser nulo")
    @NotBlank(message = "La lista de detalles transaccion no puede ser vacío")
    private List<TransactionDetailDTO> transactionDetailDTOS;

}
