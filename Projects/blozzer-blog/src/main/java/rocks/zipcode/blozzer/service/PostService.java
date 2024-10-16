package rocks.zipcode.blozzer.service;

import java.util.List;

import rocks.zipcode.blozzer.payload.PostDto;

public interface PostService {
		
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String Keyword);
}
