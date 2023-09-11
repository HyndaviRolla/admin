package com.prodapt.learningspring.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.prodapt.learningspring.entity.ReportedComment;
import com.prodapt.learningspring.entity.ReportedPost;

public interface ReportedPostRepository extends CrudRepository<ReportedPost, Long> {
 
	
	List<ReportedPost> findByPostId(int postId);
	
}
