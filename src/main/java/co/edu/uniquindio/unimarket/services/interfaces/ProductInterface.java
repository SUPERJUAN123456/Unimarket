package co.edu.uniquindio.unimarket.services.interfaces;


import co.edu.uniquindio.unimarket.dto.ProductDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;

import java.util.List;

public interface ProductInterface {

    int createProduct(ProductDTO product_DTO) throws Exception;
    int updateProduct(int idProduct, ProductGetDTO productGetDTO) throws Exception;
    int deleteProduct(int idProduct) throws Exception;
    ProductGetDTO getProductDTO (int idProduct) throws Exception;
    Product getProduct(int idProduct) throws Exception;
    public List<ProductGetDTO> listProductByCategory(int idCategory);
    public List<ProductGetDTO> listProductByState(StateProduct stateProduct);
    List<ProductGetDTO> listPersonProduct(String idPerson);
    List<ProductGetDTO> listFavoriteProduct(String idPerson);
    List<ProductGetDTO> listProductByPrice(float minPrice, float maxPrice);
    List<ProductGetDTO> listProductByTitle(String title);
    List<ProductGetDTO> listAllProducts();
    int calculatePuntuation(int idProduct) throws Exception;
    int reviewProduct(int idProduct, ProductGetDTO productGetDTO) throws Exception;
    void reduceUnities(Product product,int unities);
    void addFavoriteProduct(String idPerson, int idProduct) throws Exception;
    void removeFavoriteProduct(String idPerson, int idProduct) throws Exception;

}
