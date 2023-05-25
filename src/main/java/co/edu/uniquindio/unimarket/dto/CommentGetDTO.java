package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CommentGetDTO {

    private int id;
    private int puntuation;
    private String comment;
    private LocalDate dateComment;
    private int idProduct;
    private String idPerson;

}
