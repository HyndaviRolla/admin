package com.prodapt.learningspring.entity;
  
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class ReportedPost {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long reportid;
	    private int postId;
	    private String postContent;
	    private boolean isDeleted;
 
	    public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public ReportedPost() {
	      
	    }

	    public ReportedPost(int postId, String postContent) {
	        this.postId = postId;
	        this.postContent = postContent;
	    }

		public Long getReportid() {
			return reportid;
		}

		public void setReportid(Long reportid) {
			this.reportid = reportid;
		}

		public int getPostId() {
			return postId;
		}

		public void setPostId(int postId) {
			this.postId = postId;
		}

		public String getPostContent() {
			return postContent;
		}

		public void setPostContent(String postContent) {
			this.postContent = postContent;
		}

		 

	   
	    
	    
	    
	}







