package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;

public interface SesionInterface {

    TokenDTO login(SesionDTO sesionDTO);
    void logout(int id_person);

    TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception;
}
