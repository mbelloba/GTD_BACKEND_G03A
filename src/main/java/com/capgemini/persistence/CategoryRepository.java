package com.capgemini.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Category;

/**
 * Category repository that extends of JpaRepository
 * @author gtd-g03
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
