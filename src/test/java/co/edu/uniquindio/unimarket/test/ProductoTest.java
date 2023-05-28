package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.ProductDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Person;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import co.edu.uniquindio.unimarket.repositories.ProductRepo;
import co.edu.uniquindio.unimarket.services.interfaces.PersonInterface;
import co.edu.uniquindio.unimarket.services.interfaces.ProductInterface;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PersonInterface personInterface;

    @Test
    @Sql("classpath:dataset.sql")
    public void createProduct() throws Exception {

        Person person = personInterface.getPerson("1005088944");

        //Se crea la colección de imágenes para el producto.
        Map<String, String> images = new HashMap<>();
        images.put("1", "http://www.google.com/images/image1.jpg");
        images.put("2", "http://www.google.com/images/image2.jpg");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductDTO productoDTO = new ProductDTO(
                "Computador Gamer",
                "El pc esta en perfecto funcionamiento",
                1,
                1500000,
                "1005088944",
                1,
                0,
                images
        );

        //Se llama el servicio para crear el producto
        int idProduct = productInterface.createProduct(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, idProduct);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void updateProduct() throws Exception {

        Map<String, String> images = new HashMap<>();
        images.put("1", "http://www.google.com/images/imageNew1");
        images.put("1", "http://www.google.com/images/imageNew2");

        Product updateProduct = productInterface.getProduct(1);

        ProductGetDTO productGetDTO = new ProductGetDTO(
                1,
                LocalDate.now().plusDays(30),
                "Nintendo Switch",
                15000,
                "Nintendo switch nueva version",
                2,
                1500000,
                1500000,
                "1005088944",
                1,
                0,
                StateProduct.PENDIENTE_REVISION,
                LocalDate.now(),
                images
        );

        productInterface.updateProduct(updateProduct.getId(), productGetDTO);

        Product productTest = productInterface.getProduct(updateProduct.getId());

        Assertions.assertEquals("Play 3", productTest.getTitle());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void reviewProduct() throws Exception {

        Optional<Product> foundProduct = productRepo.findById(1);

        if(foundProduct.isEmpty()){
            throw new Exception("El producto con el id " + 1 + " no existe");
        }

        Product productReview = foundProduct.get();

        Map<String, String> images = new HashMap<>();
        images.put("1", "http://www.google.com/images/imageNew1");
        images.put("1", "http://www.google.com/images/imageNew2");

        ProductGetDTO productGetDTO = new ProductGetDTO(
                1,
                LocalDate.now().plusDays(30),
                "Nintendo Switch",
                15000,
                "Nintendo switch nueva version",
                2,
                1500000,
                1500000,
                "1005088944",
                1,
                0,
                StateProduct.APROBADO,
                LocalDate.now(),
                images
        );

        int idProductReviewStateUpdated = productInterface.reviewProduct(1, productGetDTO);

        Product productReviewStateUpdated = productInterface.getProduct(idProductReviewStateUpdated);

        Assertions.assertEquals(StateProduct.APROBADO, productReviewStateUpdated.getState());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void deleteProduct() throws Exception {
        productInterface.deleteProduct(1);

        Product product = productInterface.getProduct(1);
        Assertions.assertEquals(StateProduct.DENEGADO,product.getState());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void getProduct() throws Exception {
        ProductGetDTO productoGetDTO = productInterface.getProductDTO(1);
        Assertions.assertNotNull(productoGetDTO);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listProductByPerson() {
        List<ProductGetDTO> listProduct = productInterface.listPersonProduct("1005088944");
        Assertions.assertFalse(listProduct.isEmpty());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listProductByTitle() {
        List<ProductGetDTO> listProduct = productInterface.listProductByTitle("Computador");
        Assertions.assertFalse(listProduct.isEmpty());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listProductByCategory() {
        List<ProductGetDTO> listProduct = productInterface.listProductByCategory(1);
        Assertions.assertFalse(listProduct.isEmpty());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listProductByPrice() {
        List<ProductGetDTO> listProduct = productInterface.listProductByPrice(0,4000000);
        Assertions.assertFalse(listProduct.isEmpty());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listProductFavorite() {
        List<ProductGetDTO> listProduct = productInterface.listFavoriteProduct("1005088944");
        Assertions.assertFalse(listProduct.isEmpty());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listProductByState() {
        List<ProductGetDTO> listProduct = productInterface.listProductByState(StateProduct.APROBADO);
        Assertions.assertFalse(listProduct.isEmpty());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void addFavoriteProduct() throws Exception{

        boolean flagFavorite = false;

        String idPerson = "1005088944";
        int idProduct = 1;

        Product product = productInterface.getProduct(idProduct);

        productInterface.addFavoriteProduct(idPerson,idProduct);

        List<ProductGetDTO> favoriteProducts = productInterface.listFavoriteProduct(idPerson);

        for (ProductGetDTO productGetDTO:favoriteProducts) {
            if (productGetDTO.getId() == product.getId()){
                flagFavorite = true;
            }
        }

        Assertions.assertTrue(flagFavorite);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void removeFavoriteProduct() throws Exception{

        boolean flagFavorite = false;

        String idPerson = "1005088944";
        int idProduct = 4;

        Product product = productInterface.getProduct(idProduct);

        productInterface.removeFavoriteProduct(idPerson,idProduct);

        List<ProductGetDTO> favoriteProducts = productInterface.listFavoriteProduct(idPerson);

        Assertions.assertFalse(favoriteProducts.contains(product));

    }

}