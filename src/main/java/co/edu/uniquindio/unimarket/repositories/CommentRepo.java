package co.edu.uniquindio.unimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unimarket.model.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

    @Query("select c from Comment c where c.product.id = :idProduct")
    List<Comment> findByProduct(int idProduct);
}
