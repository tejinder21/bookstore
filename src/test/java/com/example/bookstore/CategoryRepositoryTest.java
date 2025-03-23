package com.example.bookstore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreateAndFindCategory() {
        Category category = new Category("Science Fiction");
        categoryRepository.save(category);

        Category found = categoryRepository.findById("Science Fiction");
        assertThat(found).isNotNull();
    }

    @Test
    void testDeleteCategory() {
        Category category = new Category("To Be Deleted");
        categoryRepository.save(category);
        Long categoryId = category.getId();

        categoryRepository.deleteById(categoryId);

        assertThat(categoryRepository.findById(categoryId)).isEmpty();
    }
}