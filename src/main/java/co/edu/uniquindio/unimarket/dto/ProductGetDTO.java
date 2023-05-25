package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductGetDTO {

    private int id;
    private LocalDate deadline;
    private String title;
    private float puntuation;
    private String description;
    private int unities;
    private float realPrice;
    private float price;
    private String idPerson;
    private int idCategory;
    private int discount;
    private StateProduct stateProduct;
    private LocalDate creationDate;
    private List<ImageDTO> images;

}
