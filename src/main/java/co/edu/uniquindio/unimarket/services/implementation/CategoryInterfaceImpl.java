package co.edu.uniquindio.unimarket.services.implementation;

import co.edu.uniquindio.unimarket.dto.CategoryGetDTO;
import co.edu.uniquindio.unimarket.dto.ProductGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Category;
import co.edu.uniquindio.unimarket.model.entities.Product;
import co.edu.uniquindio.unimarket.repositories.CategoryRepo;
import co.edu.uniquindio.unimarket.services.interfaces.CategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryInterfaceImpl implements CategoryInterface {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryGetDTO getCategoryDTO(int idCategory) throws Exception {

        Optional<Category> foundCategory = categoryRepo.findById(idCategory);

        if (foundCategory.isEmpty()) {
            throw new Exception("No existe una categoria con el id " + idCategory);
        }

        Category category = foundCategory.get();
        return convertToGetDTO(category);
    }

    private CategoryGetDTO convertToGetDTO(Category category) {

        CategoryGetDTO categoryGetDTO = new CategoryGetDTO(
                category.getId(),
                category.getName(),
                category.getUrl_image()
        );

        return categoryGetDTO;

    }

    @Override
    public Category getCategory(int idCategory) throws Exception {

        Optional<Category> foundCategory = categoryRepo.findById(idCategory);

        if (foundCategory.isEmpty()) {
            throw new Exception("No existe una categoria con el id " + idCategory);
        }

        Category category = foundCategory.get();
        return category;

    }

    @Override
    public List<CategoryGetDTO> listAllCategory() {

        List<Category> listCategories = categoryRepo.findAll();
        List<CategoryGetDTO> listCategoryGetDTO = new ArrayList<>();

        for (Category category: listCategories) {
            listCategoryGetDTO.add(convertToGetDTO(category));
        }

        return listCategoryGetDTO;

    }
}
