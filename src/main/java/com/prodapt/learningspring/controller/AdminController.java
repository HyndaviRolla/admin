package com.prodapt.learningspring.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.prodapt.learningspring.entity.Comment;
import com.prodapt.learningspring.entity.Post;
import com.prodapt.learningspring.entity.ReportedComment;
import com.prodapt.learningspring.entity.ReportedPost;
import com.prodapt.learningspring.repository.CommentRepository;
import com.prodapt.learningspring.repository.LikeCRUDRepository;
import com.prodapt.learningspring.repository.PostRepository;
import com.prodapt.learningspring.repository.ReportedCommentRepository;
import com.prodapt.learningspring.repository.ReportedPostRepository;
import com.prodapt.learningspring.repository.UserRepository;
import com.prodapt.learningspring.service.DomainUserService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/Admin")

public class AdminController {

	 @Autowired
	  private UserRepository userRepository;  
	  @Autowired
	  private CommentRepository commentRepository;	  
	  @Autowired
	  private PostRepository postRepository;
	  @Autowired
	  private ReportedPostRepository reportedPostRepository;
	  
	  @Autowired
	  private DomainUserService domainUserService;
	  
	  
	  @Autowired
	  private ReportedCommentRepository reportedCommentRepository;
	 
	  @Autowired
	  private LikeCRUDRepository likeCRUDRepository;

	  @GetMapping("/reported-entries")
	    public String getReportedEntries(Model model) {
	        Iterable<ReportedComment> reportedComments = reportedCommentRepository.findAll();
	        Iterable<ReportedPost> reportedPosts = reportedPostRepository.findAll();

	        model.addAttribute("reportedComments", reportedComments);
	        model.addAttribute("reportedPosts", reportedPosts);

	        return "reportedEntries";
	    }
	  @PostMapping("/delete-comment")
	  public String deleteReportedComment(@RequestParam("commentId") Long commentId) {
	      Optional<ReportedComment> reportedComment = reportedCommentRepository.findById(commentId);

	      if (reportedComment.isPresent()) {
	          
	          reportedComment.get().setDeleted(true);
	          reportedCommentRepository.save(reportedComment.get());
	 
	          Long actualCommentId = reportedComment.get().getCommentId();
	          commentRepository.deleteById(actualCommentId);
	      }
	 
	      return "redirect:/Admin/reported-entries";
	  }
 
        
	  @Transactional
	  @PostMapping("/delete-post")
	  public String deleteReportedPost(@RequestParam("postId") int postId) {
	     
	      List<ReportedPost> reportedPosts = reportedPostRepository.findByPostId(postId);

	      if (!reportedPosts.isEmpty()) {
	         
	          for (ReportedPost reportedPost : reportedPosts) {
	              reportedPost.setDeleted(true);
	              reportedPostRepository.save(reportedPost);
	          }
	      }
 
	      Optional<Post> postOptional = postRepository.findById(postId);
	      if (postOptional.isPresent()) {
	          Post post = postOptional.get();
 
	          likeCRUDRepository.deleteByLikeIdPost(post);
 
	          commentRepository.deleteByPost(post);
 
	          postRepository.deleteById(postId);
	      }

	      return "redirect:/Admin/reported-entries";
	  }

}
