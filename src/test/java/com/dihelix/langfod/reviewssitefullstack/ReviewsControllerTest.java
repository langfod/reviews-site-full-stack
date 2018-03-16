package com.dihelix.langfod.reviewssitefullstack;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsController.class)
public class ReviewsControllerTest {

	@Resource
	private MockMvc mvc;

	@InjectMocks
	private ReviewsController underTest;

	@MockBean
	private ReviewRepository reviewRepository;
	@MockBean
	private TagRepository tagRepository;
	@MockBean
	private CategoryRepository categoryRepository;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;
	@Mock
	private Tag tag;

	@Mock
	private Tag anotherTag;

	@Mock
	private Long id;

	@InjectMocks
	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	Collection<Review> allReviews = asList(review, anotherReview);
	Collection<Tag> allTags = asList(tag, anotherTag);
	Collection<Category> allCategories = asList(category, anotherCategory);

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		when(tagRepository.findAll()).thenReturn(allTags);
		when(categoryRepository.findAll()).thenReturn(allCategories);
		when(review.getCategory()).thenReturn(category);
		when(reviewRepository.findById(42L).get()).thenReturn(review);
		when(category.getId()).thenReturn(1L);

		mvc.perform(get("/review?id=42")).andExpect(view().name(is("review")));

	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=42")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepository.findById(42L).get()).thenReturn(review);

		mvc.perform(get("/review?id=42")).andExpect(model().attribute("review", is(review)));
	}

	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/review")).andExpect(view().name(is("reviews")));
	}

	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {

		when(reviewRepository.findAll()).thenReturn(allReviews);

		mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
}
