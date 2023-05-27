package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Category;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NotNull(message = "El titulo no puede ser nulo")
    @NotBlank(message = "El titulo no puede ser vacio")
    @Length(max = 100,message = "El titulo debe ser de maximo 100 caracteres")
    private String title;
    @NotNull(message = "La descripcion no puede ser nulo")
    @NotBlank(message = "La descripcion no puede ser vacio")
    private String description;
    @NotNull(message = "Las unidades no puede ser nulo")
    @NotBlank(message = "Las unidades no puede ser vacio")
    private int unities;
    @NotNull(message = "El precio no puede ser nulo")
    @NotBlank(message = "El precio no puede ser vacio")
    private float realPrice;

    @NotNull(message = "El id del vendedor no puede ser nulo")
    @NotBlank(message = "El id del vendedor no puede ser vacio")
    private String idPerson;
    @NotNull(message = "La categoría no puede ser nulo")
    @NotBlank(message = "La categoría no puede ser vacio")
    private int idCategory;
    @Length(max = 5,message = "El descuento es de maximo 5 digitos")
    private int discount;
    @NotNull(message = "Las imagenes no pueden ser null")
    @NotBlank(message = "Las imagenes no pueden ser vacio")
    private List<ImageDTO> images;



}
