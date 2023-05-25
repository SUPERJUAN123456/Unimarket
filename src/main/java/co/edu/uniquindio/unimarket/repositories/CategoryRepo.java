package co.edu.uniquindio.unimarket.repositories;

import co.edu.uniquindio.unimarket.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
