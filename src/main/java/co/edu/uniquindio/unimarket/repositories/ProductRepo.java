package co.edu.uniquindio.unimarket.repositories;

import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Category;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.category.id = :idCategory and p.state = 0 ")
    List<Product> listProductByCategory(int idCategory);

    @Query("select p from Product p where p.state = :stateProduct")
    List<Product> listProductByState(StateProduct stateProduct);

    @Query("select p from Product p join p.userFavorite f where f.id = :idPerson and p.state = 0")
    List<Product> listFavoriteProduct(String idPerson);
    @Query("select p from Product p where p.state = 0")
    List<Product>listAllProduct();
    @Query("select p from Product p where p.price between :minPrice and :maxPrice and p.state = 0 ")
    List<Product> listProductByPrice(float minPrice, float maxPrice);

    @Query("select p from Product p where p.title like concat('%', :title, '%') and p.state = 0 ")
    List<Product> listProductByTitle(String title);

    @Query("select p from Product p where p.user.id = :idPerson and p.state = 0")
    List<Product> listProductByPerson(String idPerson);
}
