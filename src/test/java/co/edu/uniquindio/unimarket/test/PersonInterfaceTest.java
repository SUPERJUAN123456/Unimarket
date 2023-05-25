package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.EmailPersonDTO;
import co.edu.uniquindio.unimarket.dto.PasswordDTO;
import co.edu.uniquindio.unimarket.dto.PersonDTO;
import co.edu.uniquindio.unimarket.dto.PersonGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.repositories.PersonRepo;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PersonInterfaceTest {

    @Autowired
    private PersonInterface personInterface;

    @Autowired
    private PersonRepo personRepo;

    @Test
    public void registerPerson(){

        PersonDTO personDTO = new PersonDTO();
        personDTO.setAdress("Villa Andrea");
        personDTO.setEmail("caloasd@hotmail.com");
        personDTO.setCity("Armenia");
        personDTO.setCountry("Colombia");
        personDTO.setName("Andres Felipe");
        personDTO.setPassword("1234");
        personDTO.setId("41923616");
        personDTO.setCellphoneNumber("3233609162");

        try {
            //Guardamos el registro
            String idNewPerson = personInterface.registerPerson(personDTO);
            //Comprobamos que si haya quedado guardado
            Assertions.assertEquals("41923616", idNewPerson);
            System.out.println("Se Creo con exito");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void updatePerson(){
        try {
            //Obtenemos un registro de la base de datos y le cambiamos por ejemplo el nombre
            PersonGetDTO personGetDTO = personInterface.getPersonDTO("1004684293");
            personGetDTO.setName("Mario");
            //Actualizamos la persona
            personInterface.updatePerson("1004684293", personGetDTO);

            //Obtenemos la persona con el id dado y verificamos que si haya sido modificado
            PersonGetDTO consulta = personInterface.getPersonDTO("1004684293");
            Assertions.assertEquals("Mario", consulta.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void getPerson() throws Exception {
        PersonGetDTO personGetDTO = personInterface.getPersonDTO("1004684293");
        Assertions.assertNotNull(personGetDTO);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void changeOldPassword() throws Exception {

        PasswordDTO passwordDTO = new PasswordDTO("10055","10055");

        Person personOld = personInterface.getPerson("1004684293");

        String oldPassword = personOld.getPassword();


        personInterface.changeOldPassword(personOld.getId(),passwordDTO);

        String newPassword = personRepo.findPersonByEmail("pipecar366@gmail.com").getPassword();


        Assertions.assertNotEquals(oldPassword,newPassword);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void changePasswordRecuperated() throws Exception {

        PasswordDTO passwordDTO = new PasswordDTO("1230302","1230302");

        String oldPassword = personRepo.findPersonByEmail("pipecar366@gmail.com").getPassword();


        personInterface.changePasswordRecuperated("pipecar366@gmail.com",passwordDTO);

        String newPassword = personRepo.findPersonByEmail("pipecar366@gmail.com").getPassword();

        Assertions.assertNotEquals(oldPassword,newPassword);
    }
}
