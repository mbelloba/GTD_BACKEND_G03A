package com.capgemini.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Category;

/**
 * Category repository that extends of JpaRepository
 * Implements custom queries over datastore
 * @author GTD-G03A
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
