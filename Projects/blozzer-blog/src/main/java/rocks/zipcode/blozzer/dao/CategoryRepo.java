package rocks.zipcode.blozzer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.blozzer.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
