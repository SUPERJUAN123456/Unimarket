package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NotNull(message = "El nombre de la categoria no puede ser nulo")
    @NotBlank(message = "El nombre de la categoria no puede ser vacio")
    private String name;

    @NotNull(message = "El url de la imagen de la categoria no puede ser nulo")
    @NotBlank(message = "El url de la imagen de la categoria no puede ser vacio")
    private String url_image;

}
