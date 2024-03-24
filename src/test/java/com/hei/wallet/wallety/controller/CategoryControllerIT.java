package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.CategoryController;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Category;
import com.hei.wallet.wallety.model.CategoryType;
import com.hei.wallet.wallety.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class CategoryControllerIT {
    private static final int MOCK_INSERT=5;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryController categoryController;

    public static Category generateCategory(String id){
        return Category
                .builder()
                .id(id)
                .name("name" + id)
                .type(CategoryType.DEBIT)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @BeforeEach
    public void cleanAndAddCategories() throws SQLException {
        categoryRepository.deleteAll();
        for (int i = 0; i < MOCK_INSERT; i++) {
            categoryRepository.saveOrUpdate(generateCategory(String.valueOf(i)));
        }
    }

    @AfterEach
    public void cleanCategories() throws SQLException {
        categoryRepository.deleteAll();
    }

    @Test
    public void getAll(){
        Assertions.assertDoesNotThrow(() ->categoryController.getAll());
        List<Category> categories = categoryController.getAll();
        Assertions.assertEquals(categories.size(), MOCK_INSERT);
        Assertions.assertTrue(categories.stream().anyMatch(category -> category.getId().equals("2")));
    }

    @Test
    public void getById(){
        Assertions.assertThrows(NotFoundException.class, () -> categoryController.getById("NOT_FOUND_ID"));
        Category categories = categoryController.getById("1");
        Assertions.assertEquals(categories.getId(), "1");
    }

    @Test
    public void crupdateCategory(){
        Category toSave = generateCategory("NEW_CATEGORY");

        List<Category> createLists = categoryController.saveOrUpdateAll(
            List.of(toSave)
        );

        Assertions.assertEquals(createLists.size(), 1);
        Assertions.assertEquals(categoryController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(createLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(createLists.get(0).getType(), toSave.getType());
        Assertions.assertEquals(createLists.get(0).getName(), toSave.getName());

        toSave.setType(CategoryType.CREDIT);
        List<Category> updatedLists = categoryController.saveOrUpdateAll(
            List.of(toSave)
        );

        Assertions.assertEquals(updatedLists.size(), 1);
        Assertions.assertEquals(categoryController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(updatedLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(updatedLists.get(0).getType(), toSave.getType());
        Assertions.assertEquals(updatedLists.get(0).getName(), toSave.getName());
    }
}