package rocks.zipcode.blozzer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.zipcode.blozzer.dao.CommentRepo;
import rocks.zipcode.blozzer.dao.PostRepo;
import rocks.zipcode.blozzer.entity.Comment;
import rocks.zipcode.blozzer.entity.Post;
import rocks.zipcode.blozzer.exceptions.ResourceNotFoundException;
import rocks.zipcode.blozzer.payload.CommentDto;
import rocks.zipcode.blozzer.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}


	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "comment Id", commentId));
		this.commentRepo.delete(comment);
	}

}
