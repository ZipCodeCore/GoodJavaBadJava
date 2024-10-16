package rocks.zipcode.blozzer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.blozzer.entity.Category;
import rocks.zipcode.blozzer.entity.Post;
import rocks.zipcode.blozzer.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
