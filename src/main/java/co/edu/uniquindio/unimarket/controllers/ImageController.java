package co.edu.uniquindio.unimarket.controllers;

import co.edu.uniquindio.unimarket.dto.MessageDTO;
import co.edu.uniquindio.unimarket.services.interfaces.CloudinaryInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class ImageController {

    private final CloudinaryInterface cloudinaryInterface;
    @PostMapping("/subir")
    public ResponseEntity<MessageDTO> uploadImage(@RequestParam("file") MultipartFile file)
            throws Exception{
        File image = cloudinaryInterface.convert(file);
        Map response = cloudinaryInterface.uploadImage(image, "unimarketImage");
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, response ) );
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MessageDTO> deleteImage(@PathVariable String id) throws Exception{
        Map response = cloudinaryInterface.deleteImage(id);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, response ) );
    }

}
