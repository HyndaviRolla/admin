package com.prodapt.learningspring.repository;
 
import com.prodapt.learningspring.entity.Comment;
import com.prodapt.learningspring.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	void deleteByPost(Post post);
	
     
}
