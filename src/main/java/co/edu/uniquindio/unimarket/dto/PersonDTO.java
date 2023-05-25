package co.edu.uniquindio.unimarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {

    @NotNull(message = "El id no puede ser nulo")
    @NotBlank(message = "El id no puede ser vacio")
    private String id;
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede ser vacio")
    private String name;
    @NotNull(message = "El email no puede ser nulo")
    @NotBlank(message = "El email no puede ser vacio")
    @Email
    private String email;
    @NotNull(message = "El pais no puede ser nulo")
    @NotBlank(message = "El pais no puede ser vacio")
    private String country;
    @NotNull(message = "La ciudad no puede ser nulo")
    @NotBlank(message = "La ciudad no puede ser vacio")
    private String city;
    @NotNull(message = "La direccion no puede ser nulo")
    @NotBlank(message = "La direccion no puede ser vacio")
    private String adress;
    @NotNull(message = "El telefono no puede ser nulo")
    @NotBlank(message = "El telefono no puede ser vacio")
    @Length(max = 15, min = 10, message = "El numero debe ser de maximo 15 digitos o de minimo 9")
    private String cellphoneNumber;
    @NotNull(message = "La contraseña no puede ser nulo")
    @NotBlank(message = "La contraseña no puede ser vacio")
    private String password;

    @NotNull(message = "La contraseña confirmada no puede ser nulo")
    @NotBlank(message = "La contraseña confirmada no puede ser vacio")
    private String confirmedPassword;

    public PersonDTO() {

    }
}
