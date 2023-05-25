package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.dto.ImageDTO;
import co.edu.uniquindio.unimarket.dto.ProductDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.*;
import co.edu.uniquindio.unimarket.repositories.PersonRepo;
import co.edu.uniquindio.unimarket.repositories.ProductRepo;
import co.edu.uniquindio.unimarket.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class ProductInterfaceImpl implements ProductInterface {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PersonInterface personInterface;
    @Autowired
    EmailInterface emailInterface;
    @Autowired
    private CategoryInterface categoryInterface;

    @Override
    public int createProduct(ProductDTO productDTO) throws Exception{

        Product product = new Product();
        product.setCategory(categoryInterface.getCategory(productDTO.getIdCategory()));
        product.setTitle(productDTO.getTitle());
        int discount = productDTO.getDiscount();
        product.setDiscount(discount);
        product.setDescription(productDTO.getDescription());
        float realPrice = productDTO.getRealPrice();
        product.setRealPrice(realPrice);
        product.setPrice(calculatePrice(realPrice,discount));
        product.setUnities(productDTO.getUnities());
        product.setState(StateProduct.PENDIENTE_REVISION);
        product.setCreationDate(LocalDate.now());
        product.setDeadline(LocalDate.now().plusDays(30));
        product.setPuntuation(0);

        Person person = personInterface.getPerson((productDTO.getIdPerson()));
        product.setUser(person);
        product.setImages(convert(productDTO.getImages()));

        productRepo.save(product);
        emailInterface.sendEmail(new EmailDTO("Su productó entro a revisión", "Ha subido con exito su producto, espere a que un moderador lo apruebe", person.getEmail()));

        return product.getId();
    }

    private Map<String, String> convert(List<ImageDTO> imageDTOS){
        Map<String, String> mapa = new HashMap<>();
        imageDTOS.forEach(obj -> mapa.put(obj.getId(), obj.getUrl()));
        return mapa;
    }

    private List<ImageDTO> convert(Map<String, String> map){

        List<ImageDTO> lista = new ArrayList<>();

        for(String key : map.keySet()){
            lista.add(new ImageDTO(key, map.get(key)));
        }

        return lista;
    }

    private float calculatePrice(float realPrice, int discount) {
        System.out.println(realPrice);
        System.out.println(discount);
        float mountDiscount = (realPrice*discount)/100;
        System.out.println(mountDiscount);
        float price = realPrice-mountDiscount;
        System.out.println(price);
        return price;
    }

    @Override
    public int updateProduct(int idProduct, ProductGetDTO productGetDTO) throws Exception {

     Optional<Product> foundProduct = productRepo.findById(idProduct);

     if(foundProduct.isEmpty()){
         throw new Exception("El producto con el id " + idProduct + " no existe");
     }

     Product productUpdated = foundProduct.get();

        productUpdated.setTitle(productGetDTO.getTitle());
        productUpdated.setCategory(categoryInterface.getCategory(productGetDTO.getIdCategory()));
        productUpdated.setDescription(productGetDTO.getDescription());
        int discount = productGetDTO.getDiscount();
        productUpdated.setDiscount(discount);
        float realPrice = productGetDTO.getRealPrice();
        productUpdated.setRealPrice(realPrice);
        productUpdated.setPrice(calculatePrice(realPrice,discount));
        productUpdated.setImages(convert(productGetDTO.getImages()));
        productUpdated.setUnities(productGetDTO.getUnities());

        productRepo.save(productUpdated);
        emailInterface.sendEmail(new EmailDTO("Se actualizó su producto", "Ha actualizado con exito su producto", productUpdated.getUser().getEmail()));

        return productUpdated.getId();
    }

    @Override
    public int deleteProduct(int idProduct) throws Exception {

        Optional<Product> foundProduct = productRepo.findById(idProduct);

        if(foundProduct.isEmpty()){
            throw new Exception("El producto con el id " + idProduct + " No existe");
        }

        Product product = foundProduct.get();

        product.setState(StateProduct.DENEGADO);
        emailInterface.sendEmail(new EmailDTO("Se denegó su producto", "Su producto sido denegado por un moderador", product.getUser().getEmail()));
        productRepo.save(product);
        return idProduct;
    }

    @Override
    public ProductGetDTO getProductDTO(int idProduct) throws Exception {

        Optional<Product> foundProduct = productRepo.findById(idProduct);

        if (foundProduct.isEmpty()) {
            throw new Exception("No existe un producto con el id " + idProduct);
        }

        Product product = foundProduct.get();
        return convertToGetDTO(product);

    }

    @Override
    public Product getProduct(int idProduct) throws Exception {

        Optional<Product> foundProduct = productRepo.findById(idProduct);

        if (foundProduct.isEmpty()) {
            throw new Exception("No existe un producto con el id " + idProduct);
        }
        return foundProduct.get();
    }

    @Override
    public List<ProductGetDTO> listAllProducts(){
        List<Product> products = productRepo.listAllProduct();

        List<Product>productsActive = validateDeadline(products);

        List<ProductGetDTO> productsActiveGetDTO = new ArrayList<>();

        for (Product product: productsActive) {
            productsActiveGetDTO.add(convertToGetDTO(product));
        }

        return productsActiveGetDTO;

    }

    @Override
    public List<ProductGetDTO> listProductByCategory(int idCategory) {

        List<Product> productsByCategory = productRepo.listProductByCategory(idCategory);

        List<Product>productsByCategoryActives = validateDeadline(productsByCategory);

        List<ProductGetDTO> listProductDTOByCategory = new ArrayList<>();

        for (Product product: productsByCategoryActives) {
            listProductDTOByCategory.add(convertToGetDTO(product));
        }

        return listProductDTOByCategory;
    }

    private List<Product> validateDeadline(List<Product> products) {

        List<Product> productsActives = new ArrayList<>();

        for (Product product:products) {
            if(product.getDeadline().isAfter(LocalDate.now())){
                productsActives.add(product);
            }
        }
        return productsActives;
    }

    @Override
    public List<ProductGetDTO> listProductByState(StateProduct stateProduct) {

        List<Product> productsByState = productRepo.listProductByState(stateProduct);
        List<ProductGetDTO> listProductDTOByState = new ArrayList<>();

        for (Product product: productsByState) {
            listProductDTOByState.add(convertToGetDTO(product));
        }

        return listProductDTOByState;

    }

    @Override
    public List<ProductGetDTO> listPersonProduct(String idPerson) {
        List<Product> productByPerson = productRepo.listProductByPerson(idPerson);
        List<ProductGetDTO> listProductDTOByPerson = new ArrayList<>();

        for (Product product: productByPerson) {
            listProductDTOByPerson.add(convertToGetDTO(product));
        }

        return listProductDTOByPerson;
    }

    @Override
    public List<ProductGetDTO> listFavoriteProduct(String idPerson) {

        List<Product> favoriteProducts = productRepo.listFavoriteProduct(idPerson);
        List<ProductGetDTO> listFavoriteProductDTO = new ArrayList<>();

        List<Product>favoriteProductActives = validateDeadline(favoriteProducts);

        for (Product product: favoriteProductActives) {
            listFavoriteProductDTO.add(convertToGetDTO(product));
        }

        return listFavoriteProductDTO;
    }

    @Override
    public List<ProductGetDTO> listProductByPrice(float minPrice, float maxPrice) {

        List<Product> productsByPrice = productRepo.listProductByPrice(minPrice,maxPrice);
        List<ProductGetDTO> listProductDTOByPrice = new ArrayList<>();

        List<Product>productByPriceActives = validateDeadline(productsByPrice);


        for (Product product: productByPriceActives) {
            listProductDTOByPrice.add(convertToGetDTO(product));
        }

        return listProductDTOByPrice;

    }

    @Override
    public List<ProductGetDTO> listProductByTitle(String title) {

        List<Product> productsByTitle = productRepo.listProductByTitle(title);
        List<ProductGetDTO> listProductDTOByTitle = new ArrayList<>();

        List<Product>productByTitleActives = validateDeadline(productsByTitle);


        for (Product product: productByTitleActives) {
            listProductDTOByTitle.add(convertToGetDTO(product));
            System.out.println(product.getTitle());
        }

        return listProductDTOByTitle;
    }

    @Override
    public int calculatePuntuation(int idProduct) throws Exception {

        Optional<Product> foundProduct = productRepo.findById(idProduct);
        float puntuation = 0;
        int cont = 0;

        if (foundProduct.isEmpty()) {
            throw new Exception("No existe un producto con el id " + idProduct);
        }

        List<Comment> comments = foundProduct.get().getComments();

        for (Comment comment:comments) {
            puntuation+=comment.getPuntuation();
            cont++;
            System.out.println("puntuacion: "+comment.getPuntuation());
        }
        System.out.println("puntuacion: "+ puntuation);
        System.out.println("cont: "+ cont);
        System.out.println("puntuacion producto: "+ (puntuation/cont));

        float puntuationTotal = (puntuation/cont);

        Product product = foundProduct.get();

        product.setPuntuation(puntuationTotal);
        productRepo.save(product);

        return product.getId();
    }

    @Override
    public int reviewProduct(int idProduct, ProductGetDTO productGetDTO) throws Exception {

        Optional<Product> foundProduct = productRepo.findById(idProduct);

        if(foundProduct.isEmpty()){
            throw new Exception("El producto con el id " + idProduct + " no existe");
        }

        Product productReview = foundProduct.get();

        productReview.setState(productGetDTO.getStateProduct());

        productRepo.save(productReview);

        emailInterface.sendEmail(new EmailDTO("Su producto a sido " + productReview.getState(), "Su producto sido " + productReview.getState() + " por un moderador", productReview.getUser().getEmail()));


        return productReview.getId();

    }

    @Override
    public void reduceUnities(Product product, int unities){

        int oldUnitiesProduct = product.getUnities();
        int subtractionUnities = oldUnitiesProduct-unities;

        product.setUnities(subtractionUnities);

        productRepo.save(product);

    }
    @Override
    public void addFavoriteProduct(String idPerson, int idProduct) throws Exception {
        Person foundPerson = personInterface.getPerson(idPerson);

        if(foundPerson == null){
            throw new Exception("No se encontro una persona con el id " + idPerson);
        }

        Product foundProduct = getProduct(idProduct);

        if(foundProduct == null){
            throw new Exception("No se encontro un producto con el id " + idProduct);
        }

        foundPerson.getFavoriteProducts().add(foundProduct);
        personRepo.save(foundPerson);
    }

    @Override
    public void removeFavoriteProduct(String idPerson, int idProduct) throws Exception {
        Person foundPerson = personInterface.getPerson(idPerson);

        if(foundPerson == null){
            throw new Exception("No se encontro una persona con el id " + idPerson);
        }

        Product foundProduct = getProduct(idProduct);

        if(foundProduct == null){
            throw new Exception("No se encontro un producto con el id " + idProduct);
        }

        System.out.println(foundPerson.getId());
        System.out.println(foundProduct.getId());

        foundPerson.getFavoriteProducts().remove(foundProduct);
        personRepo.save(foundPerson);
    }

    private ProductGetDTO convertToGetDTO(Product product){

        ProductGetDTO productGetDTO = new ProductGetDTO(
                product.getId(),
                product.getDeadline(),
                product.getTitle(),
                product.getPuntuation(),
                product.getDescription(),
                product.getUnities(),
                product.getRealPrice(),
                product.getPrice(),
                product.getUser().getId(),
                product.getCategory().getId(),
                product.getDiscount(),
                product.getState(),
                product.getCreationDate(),
                convert(product.getImages())
                );

        return productGetDTO;
    }
}
