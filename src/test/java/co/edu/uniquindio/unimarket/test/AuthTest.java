package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import co.edu.uniquindio.unimarket.services.interfaces.SesionInterface;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import java.beans.Encoder;

@SpringBootTest
@Transactional
@AllArgsConstructor
public class AuthTest {
    @Autowired
    private SesionInterface sesionInterface;
    @Autowired
    private PersonInterface personInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    @Sql("classpath:dataset.sql")
    public void login() throws Exception {

        SesionDTO sesionDTO = new SesionDTO("pipecar366@gmail.com","1234");

        TokenDTO tokenDTO = sesionInterface.login(sesionDTO);

        System.out.println(tokenDTO);

        Assertions.assertNotNull(tokenDTO);

    }

}
