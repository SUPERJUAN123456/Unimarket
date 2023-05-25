package co.edu.uniquindio.unimarket.controllers;


import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.ProductDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import co.edu.uniquindio.unimarket.services.interfaces.ProductInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductInterface productInterface;

    @PostMapping("/crear")
    public ResponseEntity<MessageDTO> createProduct(@RequestBody ProductDTO productDTO) throws Exception{
        productInterface.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MessageDTO(HttpStatus.OK, false,"Producto creado correctamente"));

    }
    @PutMapping("/actualizar/{idProduct}")
    public ResponseEntity<MessageDTO> updateProduct(@PathVariable int idProduct, @RequestBody ProductGetDTO productGetDTO) throws Exception{
        productInterface.updateProduct(idProduct,productGetDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Producto actualizado correctamente"));

    }
    @PutMapping("/eliminar/{idProduct}")
    public ResponseEntity<MessageDTO> deleteProduct(@PathVariable int idProduct) throws Exception{
        productInterface.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Producto eliminado correctamente"));

    }
    @GetMapping("/obtener/{idProduct}")
    public ResponseEntity<MessageDTO> getProductDTO (@PathVariable int idProduct) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,productInterface.getProductDTO(idProduct)));
    }

    @GetMapping("/obtener_productos_categoria/{idCategory}")
    public List<ProductGetDTO> listProductByCategory(@PathVariable int idCategory){
        return productInterface.listProductByCategory(idCategory);
    }

    @GetMapping("/obtener_productos_person/{idPerson}")
    public List<ProductGetDTO> listProductByPerson(@PathVariable String idPerson){
        return productInterface.listPersonProduct(idPerson);
    }

    @GetMapping("/obtener_favoritos_persona/{idPerson}")
    public ResponseEntity<MessageDTO> listFavoriteProduct(@PathVariable String idPerson){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,productInterface.listFavoriteProduct(idPerson)));
    }
    @GetMapping("/obtener_productos_precio/{minPrice}/{maxPrice}")
    public ResponseEntity<MessageDTO> listProductByPrice(@PathVariable float minPrice,@PathVariable float maxPrice){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, productInterface.listProductByPrice(minPrice,maxPrice)));
    }
    @GetMapping("/obtener_productos_titulo/{title}")
    public ResponseEntity<MessageDTO> listProductByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, productInterface.listProductByTitle(title)));
    }
    @PostMapping("/agregar_producto_favorito/{idPerson}/{idProduct}")
    public ResponseEntity<MessageDTO> addFavoriteProduct(@PathVariable String idPerson, @PathVariable int idProduct) throws Exception{
        productInterface.addFavoriteProduct(idPerson,idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Producto agregado correctamente a favoritos"));    }
    @DeleteMapping("/quitar_producto_favorito/{idPerson}/{idProduct}")
    public ResponseEntity<MessageDTO> removeFavoriteProduct(@PathVariable String idPerson,@PathVariable int idProduct) throws Exception{
        productInterface.removeFavoriteProduct(idPerson,idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Producto eliminado correctamente de favoritos"));
    }
    @GetMapping("/obtener_productos")
    public ResponseEntity<MessageDTO> listAllProducts() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,productInterface.listAllProducts()));
    }

}
