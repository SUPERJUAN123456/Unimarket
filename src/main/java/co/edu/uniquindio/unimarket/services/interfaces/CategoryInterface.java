package co.edu.uniquindio.unimarket.services.interfaces;

import co.edu.uniquindio.unimarket.dto.CategoryDTO;
import co.edu.uniquindio.unimarket.dto.CategoryGetDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Category;
import co.edu.uniquindio.unimarket.model.entities.Product;

import java.util.List;

public interface CategoryInterface {

    CategoryGetDTO getCategoryDTO (int idCategory) throws Exception;
    Category getCategory(int idCategory) throws Exception;
    List<CategoryGetDTO> listAllCategory();

}
