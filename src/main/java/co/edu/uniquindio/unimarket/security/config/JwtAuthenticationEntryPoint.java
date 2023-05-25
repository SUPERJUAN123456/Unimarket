package co.edu.uniquindio.unimarket.security.config;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        MessageDTO<String> messageDTO = new MessageDTO<>(HttpStatus.UNAUTHORIZED, true, "Token no encontrado o inválido");
        response.setContentType("application/json");
        response.setStatus(messageDTO.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(messageDTO));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
