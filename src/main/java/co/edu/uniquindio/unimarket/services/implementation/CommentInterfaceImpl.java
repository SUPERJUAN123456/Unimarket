package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.CommentDTO;
import co.edu.uniquindio.unimarket.dto.CommentGetDTO;
import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.model.entities.Comment;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.repositories.CommentRepo;
import co.edu.uniquindio.unimarket.repositories.ProductRepo;
import co.edu.uniquindio.unimarket.services.interfaces.CommentInterface;
import co.edu.uniquindio.unimarket.services.interfaces.EmailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentInterfaceImpl implements CommentInterface {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PersonInterfaceImpl personInterface;
    @Autowired

    private ProductInterfaceImpl productInterface;
    @Autowired
    EmailInterface emailInterface;

    @Override
    public int createComment(CommentDTO commentDTO) throws Exception {

        Comment comment = new Comment();

        Product product = productInterface.getProduct(commentDTO.getIdProduct());

        comment.setPuntuation(commentDTO.getPuntuation());
        comment.setComment(commentDTO.getComment());
        comment.setDateComment(LocalDate.now());
        Person person = personInterface.getPerson(commentDTO.getIdPerson());
        comment.setUser(person);
        comment.setProduct(product);

        commentRepo.save(comment);

        productInterface.calculatePuntuation(product.getId());

        emailInterface.sendEmail(new EmailDTO(person.getName() + " han realizado un comentario en su producto " + product.getTitle(), "Puntuacion: " + comment.getPuntuation() + " comentario: " + comment.getComment(), product.getUser().getEmail()));

        return comment.getId();

    }

    @Override
    public List<CommentGetDTO> listCommentsByProduct(int idProduct) {

        List<Comment> listCommentByProduct = commentRepo.findByProduct(idProduct);
        List<CommentGetDTO> listCommentDTOByProduct = new ArrayList<>();

        for (Comment comment: listCommentByProduct) {
            listCommentDTOByProduct.add(convertToGetDTO(comment));
        }

        return listCommentDTOByProduct;
    }

    private CommentGetDTO convertToGetDTO(Comment comment) {

        CommentGetDTO commentGetDTO= new CommentGetDTO(
                comment.getId(),
                comment.getPuntuation(),
                comment.getComment(),
                comment.getDateComment(),
                comment.getProduct().getId(),
                comment.getUser().getId()
        );

        return commentGetDTO;
    }

    @Override
    public Comment getComment(int idComment) throws Exception {
        Optional<Comment> comment = commentRepo.findById(idComment);
        if (comment.isEmpty()) {
            throw new Exception("No existe un comentario con el id " + idComment);
        }
        return comment.get();
    }

    @Override
    public CommentGetDTO getCommentDTO(int idComment) throws Exception {
        Optional<Comment> comment = commentRepo.findById(idComment);
        if (comment.isEmpty()) {
            throw new Exception("No existe un comentario con el id " + idComment);
        }
        return convertToGetDTO(comment.get());
    }
}
