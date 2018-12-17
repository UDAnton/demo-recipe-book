package com.github.udanton.demorecipebook.repositories;

import com.github.udanton.demorecipebook.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
