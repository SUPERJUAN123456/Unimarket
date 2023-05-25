package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.services.interfaces.EmailInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {
    @Autowired
    EmailInterface emailInterface;

    @Test
    public void sedEmail() {
        try {
            emailInterface.sendEmail(new EmailDTO("Cloudinary 2", "hola!", "pipecar366@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
