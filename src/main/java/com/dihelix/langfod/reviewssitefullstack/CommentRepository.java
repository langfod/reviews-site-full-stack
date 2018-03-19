package com.dihelix.langfod.reviewssitefullstack;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findByReview(Review review);
	List<Comment> findByReviewOrderByCommentDate(Review review);
	List<Comment> findByReviewIdOrderByCommentDateAsc(Long reviewId);
	List<Comment> findByReviewIdOrderByCommentDateDesc(Long reviewId);


}
