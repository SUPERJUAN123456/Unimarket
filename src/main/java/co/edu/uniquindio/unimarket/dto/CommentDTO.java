package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {

    @NotNull(message = "La puntuación no puede ser nulo")
    @NotBlank(message = "La puntuación no puede ser vacío")
    private int puntuation;
    @NotNull(message = "El comentario no puede ser nulo")
    @NotBlank(message = "El el comentario no puede ser vacío")
    private String comment;

    @NotNull(message = "El id del producto no puede ser nulo")
    @NotBlank(message = "El id del producto no puede ser vacío")
    private int idProduct;
    @NotNull(message = "El id del usuario no puede ser nulo")
    @NotBlank(message = "El id del usuario no puede ser vacío")
    private String idPerson;

}
