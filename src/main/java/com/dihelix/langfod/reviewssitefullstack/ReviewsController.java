package com.dihelix.langfod.reviewssitefullstack;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	/* Resty Stuff */
	/* ----------------------------------- */

	@ResponseBody
	@GetMapping(value = "/api/review/{id}")
	public Review apiReviewById(@PathVariable("id") Long inputid) {
		return reviewRepository.findById(inputid).get();
	}

	@ResponseBody
	@GetMapping(value = "/api/review/{id}/comments")
	public List<Comment> apiCommentsByReviewId(@PathVariable("id") Long inputid) {
		return commentRepository.findByReviewIdOrderByCommentDate(inputid);
	}

	@ResponseBody
	@GetMapping(value = "/api/reviews/tag/{id}")
	public Iterable<Review> apiReviewsByTag(@PathVariable("id") Long tagId) {
		return reviewRepository.findByTags_Id(tagId);
	}

	@ResponseBody
	@GetMapping(value = "/api/reviews")
	public Iterable<Review> apiReviews() {
		return reviewRepository.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/api/tags")
	public Iterable<Tag> apiTags() {
		return tagRepository.findAll();
	}

	@ResponseBody
	@PutMapping(value = "/api/review/{id}/tag", consumes = "application/json")
	public ResponseEntity<String> apiReviewAddTags(@PathVariable("id") Long inputId,
			@RequestBody List<String> tagNames) {
		Review review = reviewRepository.findById(inputId).get();
		tagNames.forEach(tagName -> reviewRepository.save(review
				.addTag(tagRepository.findByName(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName))))));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ResponseBody
	@DeleteMapping(value = "/api/review/{id}/tag/{tagId}")
	public ResponseEntity<String> apiDeleteTagFromReview(@PathVariable("id") Long inputid,
			@PathVariable("tagId") Long tagId) {
		Review review = reviewRepository.findById(inputid).get();
		review = reviewRepository.save(review.removeTag(tagRepository.findById(tagId).get()));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ResponseBody
	@DeleteMapping(value = "/api/review/{id}/comment/{commentId}")
	public ResponseEntity<String> apiDeleteCommentFromReview(@PathVariable("id") Long inputid,
			@PathVariable("commentId") Long commentId) {
		Review review = reviewRepository.findById(inputid).get();
		review = reviewRepository.save(review.removeComment(commentRepository.findById(commentId).get()));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/* ----------------------------------- */
	/* Not Resty Stuff */
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
			model.addAttribute("selectedTag", tagRepository.findById(reviewtagId).get());
			model.addAttribute("reviews", reviewRepository.findByTags_Id(reviewtagId));
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