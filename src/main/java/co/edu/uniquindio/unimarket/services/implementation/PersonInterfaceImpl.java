package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.*;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Rol;
import co.edu.uniquindio.unimarket.repositories.PersonRepo;
import co.edu.uniquindio.unimarket.services.interfaces.EmailInterface;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInterfaceImpl implements PersonInterface {
    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private EmailInterface emailInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerPerson(PersonDTO personDTO) throws Exception {

        Person flagID = personRepo.findPersonById(personDTO.getId());
        Person flagEmail = personRepo.findPersonByEmail(personDTO.getEmail());

        if(flagEmail != null){
            throw new Exception("El email " + personDTO.getEmail() + " ya existe");
        }

        if(flagID != null){
            throw new Exception("El id " + personDTO.getId() + " ya existe");
        }

        Person person = new Person();
        person.setAdress(personDTO.getAdress());
        person.setId(personDTO.getId());
        person.setCity(personDTO.getCity());
        person.setCountry(personDTO.getCountry());
        person.setCellphoneNumber(personDTO.getCellphoneNumber());
        person.setName(personDTO.getName());
        person.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        person.setRol(Rol.valueOf("USUARIO"));
        person.setEmail(personDTO.getEmail());

        personRepo.save(person);
        emailInterface.sendEmail(new EmailDTO("Se registró con exito", "Bienvenido " + person.getName() + ", se ha registrado con exito", person.getEmail()));

        return person.getId();

    }
    @Override
    public String registerAdmin(PersonDTO personDTO) throws Exception {

        Person flagID = personRepo.findPersonById(personDTO.getId());
        Person flagEmail = personRepo.findPersonByEmail(personDTO.getEmail());

        if(flagEmail != null){
            throw new Exception("El email " + personDTO.getEmail() + " ya existe");
        }

        if(flagID != null){
            throw new Exception("El id " + personDTO.getId() + " ya existe");
        }

        Person person = new Person();
        person.setAdress(personDTO.getAdress());
        person.setId(personDTO.getId());
        person.setCity(personDTO.getCity());
        person.setCountry(personDTO.getCountry());
        person.setCellphoneNumber(personDTO.getCellphoneNumber());
        person.setName(personDTO.getName());
        validatePassword(personDTO.getPassword(),personDTO.getConfirmedPassword());
        person.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        person.setRol(Rol.valueOf("MODERADOR"));
        person.setEmail(personDTO.getEmail());

        personRepo.save(person);
        emailInterface.sendEmail(new EmailDTO("Se registró con exito el admin", "Bienvenido " + person.getName() + ", se ha registrado con exito", person.getEmail()));

        return person.getId();

    }

    @Override
    public String updatePerson(String idPerson, PersonGetDTO personGetDTO) throws Exception {

        verifyPerson(idPerson);

        Person foundPerson = personRepo.findPersonById(idPerson);

        foundPerson.setAdress(personGetDTO.getAdress());
        foundPerson.setCity(personGetDTO.getCity());
        foundPerson.setCountry(personGetDTO.getCountry());
        foundPerson.setName(personGetDTO.getName());
        foundPerson.setCellphoneNumber(personGetDTO.getCellphoneNumber());

        personRepo.save(foundPerson);
        emailInterface.sendEmail(new EmailDTO("Se actualizó con exito", "Ha actualizado con exito la informacion de su cuenta", foundPerson.getEmail()));

        return foundPerson.getId();
    }

    @Override
    public Person getPerson(String idPerson) throws Exception {
        Person person = personRepo.findPersonById(idPerson);
        if (person == null) {
            throw new Exception("No existe una persona con el id " + idPerson);
        }
        return person;
    }

    private boolean validatePassword(String newPassword, String newPasswordRepeated) throws Exception {

        if(newPassword.equals(newPasswordRepeated) != true){
            throw new Exception("Las contraseñas no coinciden");
        }

        return true;
    }

    @Override
    public String changeOldPassword(String idPerson, PasswordDTO passwordDTO) throws Exception {
        Person foundPerson = personRepo.findPersonById(idPerson);

        if(foundPerson == null){
            throw new Exception("No se encontro una persona con el id " + idPerson);
        }
        String newPassword = passwordDTO.getPassword();
        String newPasswordRepeated = passwordDTO.getPasswordRepeated();

        validatePassword(newPassword,newPasswordRepeated);

        foundPerson.setPassword(passwordEncoder.encode(newPassword));

        personRepo.save(foundPerson);
        emailInterface.sendEmail(new EmailDTO("Se actualizó su contraseña", "Ha actualizado con exito la contraseña de su cuenta", foundPerson.getEmail()));

        return foundPerson.getId();
    }

    @Override
    public void recuperatePassword(EmailPersonDTO emailPersonDTO) throws Exception {
        emailInterface.sendEmail(new EmailDTO("Recuperar contraseña","Para recuperar su contraseña ingrese al siguiente enlace: https://www.unimarket.com/api/personas/recuperar_contraseña/" + emailPersonDTO.getEmail(),emailPersonDTO.getEmail()));


    }

    @Override
    public String changePasswordRecuperated(String emailPerson, PasswordDTO passwordDTO) throws Exception {
        Person foundPerson = personRepo.findPersonByEmail(emailPerson);

        if(foundPerson == null){
            throw new Exception("No se encontro una persona con el correo " + emailPerson);
        }

        String newPassword = passwordDTO.getPassword();
        String newPasswordRepeated = passwordDTO.getPasswordRepeated();

        validatePassword(newPassword,newPasswordRepeated);

        foundPerson.setPassword(passwordEncoder.encode(newPassword));

        personRepo.save(foundPerson);
        emailInterface.sendEmail(new EmailDTO("Se recuperó su contraseña", "Ha recuperado con exito la contraseña de su cuenta", foundPerson.getEmail()));

        return foundPerson.getId();
    }

    @Override
    public PersonGetDTO getPersonDTO(String idPerson) throws Exception {
        Person person = personRepo.findPersonById(idPerson);
        if (person == null) {
            throw new Exception("No existe una persona con el id " + idPerson);
        }
        return convertToGetDTO(person);
    }
       private void verifyPerson(String idPerson) throws Exception {

           try{
               long idPersonLong = Integer.parseInt(idPerson);
           }catch (Exception e){
               System.out.println(e);
           }

        boolean flagPerson = personRepo.existsById(idPerson);

        if(!flagPerson){
            throw new Exception("La person con el id " + idPerson + " no existe");
        }
    }

    private PersonGetDTO convertToGetDTO (Person person){
        PersonGetDTO personGetDTO = new PersonGetDTO(
                person.getId(),
                person.getName(),
                person.getEmail(),
                person.getCountry(),
                person.getCity(),
                person.getAdress(),
                person.getCellphoneNumber(),
                person.getPassword(),
                person.getRol()
        );

        return personGetDTO;
    }
}


