package rocks.zipcode.blozzer.service;

import rocks.zipcode.blozzer.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
}
