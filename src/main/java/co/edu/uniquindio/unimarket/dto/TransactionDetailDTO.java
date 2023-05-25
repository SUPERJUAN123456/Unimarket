package co.edu.uniquindio.unimarket.dto;


import co.edu.uniquindio.unimarket.model.entities.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDetailDTO {

    @NotNull(message = "El id del producto no puede ser nulo")
    @NotBlank(message = "El id del producto no puede ser vacío")
    private int idProduct;

    @NotNull(message = "Las unidades no puede ser nulo")
    @NotBlank(message = "Las unidades no puede ser vacío")
    private int unities;

}
