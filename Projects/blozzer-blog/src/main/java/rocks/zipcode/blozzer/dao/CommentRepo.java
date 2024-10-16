package rocks.zipcode.blozzer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.blozzer.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
