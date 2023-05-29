package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.StateProduct;
import co.edu.uniquindio.unimarket.services.interfaces.ProductInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/moderador")
public class ModeratorController {

    private final ProductInterface productInterface;

    @GetMapping("/obtener_productos_estado/{stateProduct}")
    public ResponseEntity<MessageDTO> listProductByState(@PathVariable StateProduct stateProduct){
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,productInterface.listProductByState(stateProduct)));
    }

    @PutMapping("/revisar_producto/{idProduct}")
    public ResponseEntity<MessageDTO> reviewProduct(@PathVariable int idProduct, @RequestBody ProductGetDTO productGetDTO) throws Exception {
        productInterface.reviewProduct(idProduct,productGetDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,"Producto revisado correctamente"));
    }
    @GetMapping("/obtener_productos")
    public ResponseEntity<MessageDTO> listAllProducts() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,productInterface.listAllProducts()));
    }

}
