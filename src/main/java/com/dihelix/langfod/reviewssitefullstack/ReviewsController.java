package com.dihelix.langfod.reviewssitefullstack;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;




@Controller
public class ReviewsController {
	private Logger log = LoggerFactory.getLogger(ReviewsPopulator.class);

	@Resource
	ReviewRepository reviewRepository;

	@Resource
	TagRepository tagRepository;

	@Resource
	CategoryRepository categoryRepository;
	
	@Resource
	CommentRepository commentRepository;

	@RequestMapping("/")
	public String redirect(Model model) {
		return "redirect:/reviews";
	}

	
	/* ----------------------------------- */
	/* 	         Rest Stuff 			   */
	/* ----------------------------------- */
	
	@ResponseBody
	@DeleteMapping(value = "/api/review/{id}/tag/{tagId}") 
	public  Review apiDeleteTagFromReview(@PathVariable("id") Long inputid, @PathVariable("tagId") Long tagId) {
		Review review = reviewRepository.findById(inputid).get();
		review = review.removeTag(tagRepository.findById(tagId).get());
		return reviewRepository.save(review);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/api/review/{id}/comment/{commentId}")
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Comment Not Found")  // 404
	@ExceptionHandler(IllegalArgumentException.class)
	public  Review apiDeleteCommentFromReview(@PathVariable("id") Long inputid, @PathVariable("commentId") Long commentId) {
		Review review = reviewRepository.findById(inputid).get();
		review = review.removeComment(commentRepository.findById(commentId).get());
		//return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comment ID "+commentId+" deleted.");
		return reviewRepository.save(review);
	}
	
	@ResponseBody
	@GetMapping(value = "/api/review/{id}") 
	public  Review apiReviewById(@PathVariable("id") Long inputid) {
		return reviewRepository.findById(inputid).get();
	}
	
	@ResponseBody
	@GetMapping(value = "/api/reviews/tag/{id}") 
	public  Iterable<Review> apiReviewsByTag(@PathVariable("id") Long tagId) {
		return   reviewRepository.findByTags(tagRepository.findById(tagId).get());
	}
	
	@ResponseBody
	@GetMapping(value = "/api/reviews") 
	public  Iterable<Review> apiReviews() {
		return reviewRepository.findAll();
	}
	
	@ResponseBody
	@GetMapping(value = "/api/tags") 
	public  Iterable<Tag> apiTags() {
		return tagRepository.findAll();
	}
	
	/* ----------------------------------- */
	/* 	        Not Rest Stuff			   */
	/* ----------------------------------- */

	
	@RequestMapping("/review")
	public String reviewByIdGet(@RequestParam(value = "id", required = true) Long inputid, Model model) {
		Review review = reviewRepository.findById(inputid).get();
		model.addAttribute("id", inputid);
		model.addAttribute("tagList", tagRepository.findAll());
		model.addAttribute("review", review);
		model.addAttribute("categoryList", categoryRepository.findAll());
		model.addAttribute("selectedCategory", review.getCategory());
		return "review";
	}

	@RequestMapping("/reviews")
	public String reviews(@RequestParam(value = "reviewtagId", required = false) Long reviewtagId,
			@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {

		model.addAttribute("tagList", tagRepository.findAll());
		model.addAttribute("categoryList", categoryRepository.findAll());

		if (reviewtagId != null) {
			Tag selectedTag = tagRepository.findById(reviewtagId).get();
			model.addAttribute("selectedTag", selectedTag);
			model.addAttribute("reviews", reviewRepository.findByTags(selectedTag));
		} else if (categoryId != null) {
			Category selectedCategory = categoryRepository.findById(categoryId).get();
			model.addAttribute("selectedCategory", selectedCategory);
			model.addAttribute("reviews", reviewRepository.findByCategory(selectedCategory));
		} else {

			model.addAttribute("reviews", reviewRepository.findAll());
		}
		return "reviews";
	}

	@RequestMapping("/categories")
	public String categories(Model model) {
		model.addAttribute("categoryList", categoryRepository.findAll());
		return "categories";
	}
}