package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.*;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/personas")
public class PersonController {

    private final PersonInterface personInterface;

   @PostMapping("/registrar")
   public ResponseEntity<MessageDTO> registerPerson(@RequestBody PersonDTO personDTO) throws Exception {
        personInterface.registerPerson(personDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body( new MessageDTO(HttpStatus.CREATED, false,"Persona registrada correctamente"));
    };

   @PutMapping("/actualizar/{idPerson}")
   public ResponseEntity<MessageDTO> updatePerson(@PathVariable String idPerson, @RequestBody PersonGetDTO personGetDTO) throws Exception {
       personInterface.updatePerson(idPerson, personGetDTO);
       return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Persona actualizada correctamente"));
   };

   @PutMapping("/recuperar_contraseña/{emailPerson}")
   public ResponseEntity<MessageDTO> changePasswordRecuperated(@PathVariable String emailPerson, @RequestBody PasswordDTO passwordDTO) throws Exception {
       personInterface.changePasswordRecuperated(emailPerson, passwordDTO);
       return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Contraseña cambiada con exito"));
   }
   @PutMapping("/cambiar_contraseña/{idPerson}")
   public ResponseEntity<MessageDTO> changeOldPassword(@PathVariable String idPerson, @RequestBody PasswordDTO passwordDTO ) throws Exception{
       personInterface.changeOldPassword(idPerson, passwordDTO);
       return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Contraseña cambiada con exito"));
   }
   @PostMapping("/recuperar_contraseña")
   public ResponseEntity<MessageDTO> recuperatePassword(@RequestBody EmailPersonDTO emailPersonDTO) throws Exception{
       personInterface.recuperatePassword(emailPersonDTO);
       return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Correo de recuperacion de contraseña enviado con exito"));
   }

}
