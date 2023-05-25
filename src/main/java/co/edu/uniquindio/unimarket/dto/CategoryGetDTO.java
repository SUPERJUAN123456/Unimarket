package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CategoryGetDTO {

    private int id;
    private String name;
    private String url_image;

}
