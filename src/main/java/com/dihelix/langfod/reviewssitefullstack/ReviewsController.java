package com.dihelix.langfod.reviewssitefullstack;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewsController {

	@Resource
	ReviewRepository reviewRepository;

	@Resource
	TagRepository tagRepository;

	@Resource
	CategoryRepository categoryRepository;

	@RequestMapping("/")
	public String redirect(Model model) {
		return "redirect:/reviews";
	}

	@RequestMapping("/review")
	public String review(@RequestParam(value = "id", required = true) Long inputid, Model model) {
		Review review = reviewRepository.findOne(inputid);
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
			Tag selectedTag = tagRepository.findOne(reviewtagId);
			model.addAttribute("selectedTag", selectedTag);
			model.addAttribute("reviews", reviewRepository.findByTags(selectedTag));
		} else if (categoryId != null) {
			Category selectedCategory = categoryRepository.findOne(categoryId);
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