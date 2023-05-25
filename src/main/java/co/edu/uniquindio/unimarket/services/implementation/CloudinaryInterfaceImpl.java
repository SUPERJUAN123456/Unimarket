package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.services.interfaces.CloudinaryInterface;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryInterfaceImpl implements CloudinaryInterface {

    private final Cloudinary cloudinary;

    public CloudinaryInterfaceImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dxmalxjle");
        config.put("api_key", "344671726779568");
        config.put("api_secret", "7uEjxJ8qBO39GMGtBaFYodAYCZU");
        cloudinary = new Cloudinary(config);
    }


    @Override
    public Map uploadImage(File file, String directory) throws Exception {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", directory));
    }

    @Override
    public Map deleteImage(String idImage) throws Exception {
        return cloudinary.uploader().destroy(idImage, ObjectUtils.emptyMap());
    }

    @Override
    public File convert(MultipartFile image) throws IOException {
        File file = File.createTempFile(image.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(image.getBytes());
        fos.close();
        return file;
    }
}
