package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.services.interfaces.CloudinaryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@SuppressWarnings("unchecked")
public class CloudinaryTest {

    @Autowired
    private CloudinaryInterface cloudinaryInterface;

    @Test
    public void uploadImage() throws Exception {
        File image = new File("C:/Users/user/Documents/unimarket/ws/unimarket/src/test/resources/camisa.webp");

        Map<String, String> map = cloudinaryInterface.uploadImage(image, "unimarketImage");
        Map<String, String> mapResponse = new HashMap<>();

        mapResponse.put(map.get("public_id"), map.get("url"));

        for(Map.Entry mp: mapResponse.entrySet()){
            System.out.println(mp.getKey()+ ":" + mp.getValue());
        }

    }
    @Test
    public void eliminarImagen() throws Exception {
        cloudinaryInterface.deleteImage("unimarketImage/obisit65hooddgj38lyx");
    }

}
