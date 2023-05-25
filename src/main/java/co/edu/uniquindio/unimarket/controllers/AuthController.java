package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.PersonDTO;
import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import co.edu.uniquindio.unimarket.services.interfaces.SesionInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final PersonInterface personInterface;
    private final SesionInterface sesionInterface;
    @PostMapping("/login")
    public ResponseEntity<MessageDTO> login(@Valid @RequestBody SesionDTO loginPerson) {
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, sesionInterface.login(loginPerson)) );
    }
    @PostMapping("/registro")
    public ResponseEntity<MessageDTO> registerPerson(@Valid @RequestBody PersonDTO personDTO) throws Exception {
        personInterface.registerPerson(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,false, "Persona registrada correctamente"));
    }

    @PostMapping("/registroAdmin")
    public ResponseEntity<MessageDTO> registerAdmin(@Valid @RequestBody PersonDTO personDTO) throws Exception {
        personInterface.registerAdmin(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,false, "Admin registrado correctamente"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<MessageDTO> refreshToken(@Valid @RequestBody TokenDTO tokenDTO) throws Exception {
        sesionInterface.refreshToken(tokenDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(HttpStatus.OK,false, "Token refrescado"));
    }
}
