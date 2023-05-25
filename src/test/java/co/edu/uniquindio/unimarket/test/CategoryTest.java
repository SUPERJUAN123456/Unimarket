package co.edu.uniquindio.unimarket.test;

import co.edu.uniquindio.unimarket.dto.CategoryGetDTO;
import co.edu.uniquindio.unimarket.model.entities.Category;
import co.edu.uniquindio.unimarket.services.interfaces.CategoryInterface;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@AllArgsConstructor
public class CategoryTest {

    @Autowired
    CategoryInterface categoryInterface;

    @Test
    @Sql("classpath:dataset.sql")
    public void listAllCategory() {

        List<CategoryGetDTO> listCategories = categoryInterface.listAllCategory();
        Assertions.assertFalse(listCategories.isEmpty());

    }

}
