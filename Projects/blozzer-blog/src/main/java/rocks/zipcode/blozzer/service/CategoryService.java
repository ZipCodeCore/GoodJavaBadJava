package rocks.zipcode.blozzer.service;

import java.util.List;

import rocks.zipcode.blozzer.payload.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer catId);
	
	void delterCategory(Integer categoryId);
	
	CategoryDTO getCategory(Integer categoryId);
	
	List<CategoryDTO> getAllCategory();
	
	
}
