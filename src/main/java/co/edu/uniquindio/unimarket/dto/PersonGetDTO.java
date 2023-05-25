package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.model.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PersonGetDTO {

    private String id;
    private String name;
    private String email;
    private String country;
    private String city;
    private String adress;
    private String cellphoneNumber;
    private String password;
    private Rol rol;

}
