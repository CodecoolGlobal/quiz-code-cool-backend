package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testAddNewCategory() {
        Category category = Category.builder()
                .name("Java")
                .build();
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(1);
    }

    @Test
    public void findAllCategories() {
        Category category1 = Category.builder()
                .name("Java")
                .build();
        Category category2 = Category.builder()
                .name("Python")
                .build();
        Category category3 = Category.builder()
                .name("CSS")
                .build();
        categoryRepository.saveAll(Lists.newArrayList(category1, category2, category3));
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).containsExactlyInAnyOrder(category1, category2, category3);
    }

    @Test
    public void getOneCategoryById() {
        Category category1 = Category.builder()
                .id(1)
                .name("Java")
                .build();
        categoryRepository.save(category1);
        assertThat(categoryRepository.getOne(1).equals(category1));
    }
}