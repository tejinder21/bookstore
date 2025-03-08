package com.example.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(path = "categories") 
public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
