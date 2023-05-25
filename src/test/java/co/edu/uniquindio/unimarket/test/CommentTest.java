package co.edu.uniquindio.unimarket.test;


import co.edu.uniquindio.unimarket.dto.CommentDTO;
import co.edu.uniquindio.unimarket.dto.CommentGetDTO;
import co.edu.uniquindio.unimarket.services.interfaces.CommentInterface;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class CommentTest {

    @Autowired
    private CommentInterface commentInterface;

    @Test
    @Sql("classpath:dataset.sql")
    public void createComment() throws Exception {
        CommentDTO commentDTO = new CommentDTO(
          5,
          "Muy buen producto",
          1,
          "1004684293"
        );

        int codigoComentario = commentInterface.createComment(commentDTO);

        Assertions.assertNotEquals(0, codigoComentario);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listComments() {
        List<CommentGetDTO> comments = commentInterface.listCommentsByProduct(1);
        Assertions.assertFalse(comments.isEmpty());
    }

}
