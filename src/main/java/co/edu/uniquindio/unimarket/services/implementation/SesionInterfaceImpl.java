package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.security.model.PersonDetailsImpl;
import co.edu.uniquindio.unimarket.security.services.JwtService;
import co.edu.uniquindio.unimarket.services.interfaces.SesionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SesionInterfaceImpl implements SesionInterface {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private   UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenDTO login(SesionDTO sesionDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sesionDTO.getEmail(), sesionDTO.getPassword()));
        UserDetails user = (PersonDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new TokenDTO(jwtToken, refreshToken);
    }

    @Override
    public TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception {
        String email = jwtService.extractUsername(tokenDTO.getRefreshToken());
        PersonDetailsImpl user = (PersonDetailsImpl) userDetailsService.loadUserByUsername(email);
        if (jwtService.isTokenValid(tokenDTO.getRefreshToken(), user)) {
            String token = jwtService.generateToken(user);
            return new TokenDTO(token, tokenDTO.getRefreshToken());
        }
        throw new Exception("Error construyendo el token");
    }

    @Override
    public void logout(int id_person) {

    }

}
