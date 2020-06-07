package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private QuestionRepository questionRepository;

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
                .name("Java")
                .build();
        categoryRepository.save(category1);
        assertThat(categoryRepository.getOne(category1.getId()).equals(category1));
    }

    @Test
    public void saveUniqueFieldTwiceInCategory() {
        Category category1 = Category.builder()
                .name("category test")
                .build();

        categoryRepository.save(category1);

        Category category2 = Category.builder()
                .name("category test")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            categoryRepository.saveAndFlush(category2);
        });
    }

    @Test
    public void persistQuestionWithCategory() {
        Category category = Category.builder()
                .name("category test")
                .build();

        Question question = Question.builder()
                .question("does it persist with category?")
                .category(category)
                .build();

        questionRepository.save(question);

        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(1);
    }
}