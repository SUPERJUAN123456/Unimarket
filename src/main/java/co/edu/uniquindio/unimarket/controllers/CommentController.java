package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.CommentDTO;
import co.edu.uniquindio.unimarket.dto.CommentGetDTO;
import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.services.interfaces.CommentInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comentarios")
public class CommentController {

    private final CommentInterface commentInterface;

    @PostMapping("/comentar")
    public ResponseEntity<MessageDTO> createComment(@RequestBody CommentDTO commentDTO) throws Exception{
        commentInterface.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,false, "Comentario creado correctamente"));
    }
    @GetMapping("/obtener_comentarios_producto/{idProduct}")
    public ResponseEntity<MessageDTO> listCommentsByProduct(@PathVariable int idProduct){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,commentInterface.listCommentsByProduct(idProduct)));
    }

}
