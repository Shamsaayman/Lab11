package org.example.lab11.Repository;

import org.example.lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryByID(Integer id);
}
