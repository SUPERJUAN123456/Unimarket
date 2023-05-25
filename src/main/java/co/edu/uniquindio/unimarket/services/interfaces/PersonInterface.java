package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.EmailPersonDTO;
import co.edu.uniquindio.unimarket.dto.PasswordDTO;
import co.edu.uniquindio.unimarket.dto.PersonDTO;
import co.edu.uniquindio.unimarket.dto.PersonGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Person;

import java.util.List;


public interface PersonInterface {

    String registerPerson(PersonDTO personDTO) throws Exception;
    String registerAdmin(PersonDTO personDTO) throws Exception;
    String updatePerson(String idPerson, PersonGetDTO personGetDTO) throws Exception;
    PersonGetDTO getPersonDTO(String idPerson) throws Exception;
    Person getPerson(String idPerson) throws Exception;
    String changeOldPassword(String idPerson, PasswordDTO passwordDTO ) throws Exception;
    void recuperatePassword(EmailPersonDTO emailPersonDTO) throws Exception;
    String changePasswordRecuperated(String emailPerson, PasswordDTO passwordDTO) throws Exception;
}
