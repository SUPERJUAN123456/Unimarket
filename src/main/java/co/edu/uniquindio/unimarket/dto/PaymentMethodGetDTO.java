package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Transaction;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PaymentMethodGetDTO {

    private int id;
    private String bankingEntity;
    private String titularName;
    private String cardNumber;
    private LocalDate expirationDate;
    private int cvv;
    private boolean state;
    private String idPerson;

}
