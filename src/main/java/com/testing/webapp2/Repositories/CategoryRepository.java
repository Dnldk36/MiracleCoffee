package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Categories, Long> {
}
