package co.edu.uniquindio.unimarket.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class PaymentMethodDTO {

    @NotNull(message = "El nombre del titular no puede ser nulo")
    @NotBlank(message = "El nombre del titular no puede ser vacío")
    @Length(max = 100)
    private String titularName;
    @NotNull(message = "La entidad bancaria no puede ser nulo")
    @NotBlank(message = "La entidad bancaria no puede ser vacío")
    @Length(max = 50)
    private String bankingEntity;
    @NotNull(message = "El numero de tarjeta no puede ser nulo")
    @NotBlank(message = "El numero de tarjeta no puede ser vacío")
    @Length(max = 16)
    private String cardNumber;
    @NotNull(message = "La fecha de expiración puede ser nulo")
    @NotBlank(message = "La fecha de expiración no puede ser vacío")
    private LocalDate expirationDate;
    @NotNull(message = "El cvv no puede ser nulo")
    @NotBlank(message = "El cvv no puede ser vacío")
    @Length(max = 3)
    private int cvv;
    @NotNull(message = "El id de la persona no puede ser nulo")
    @NotBlank(message = "El id de la persona no puede ser vacío")
    private String idPerson;

}
