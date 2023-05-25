package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.EmailDTO;

public interface EmailInterface {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
