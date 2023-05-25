package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.CommentDTO;
import co.edu.uniquindio.unimarket.dto.CommentGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Comment;


import java.util.List;

public interface CommentInterface {

    int createComment(CommentDTO commentDTO) throws Exception;
    List<CommentGetDTO>listCommentsByProduct(int idProduct);
    CommentGetDTO getCommentDTO(int idComment) throws Exception;
    Comment getComment(int idComment) throws Exception;

}
