package com.dihelix.langfod.reviewssitefullstack;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewTest {

	private Review reviewUnderTest;
	private final LocalDate testBookPublishedDate = LocalDate.of(1995, Month.MAY, 23);
	private final String testContent = "Content";
	private final String testDescription = "Description";
	private final String testImageUrl = "Title";
	private final Tag[] testTags = {new Tag("tag1"), new Tag("tag2")};
	private final String testTitle = "Title";
	private final Category testCategory = new Category("category");


	@Before
	public void setUp() {
		reviewUnderTest = new Review(testTitle, testCategory, testContent, testBookPublishedDate, testDescription,
				testImageUrl, testTags);
	}

	@Test
	public void getBookPublishedDateShouldReturnTestValue() {
		assertThat(reviewUnderTest.getBookPublishedDate(), is(testBookPublishedDate));
	}

	@Test
	public void getCategoryShouldReturnTestValue() {
		assertThat(reviewUnderTest.getCategory(), is(testCategory));
	}

	@Test
	public void getContentShouldReturnTestValue() {
		assertThat(reviewUnderTest.getContent(), is(testContent));
	}

	@Test
	public void getDescriptionShouldReturnTestValue() {
		assertThat(reviewUnderTest.getDescription(), is(testDescription));
	}

	@Test
	public void getImageUrlShouldReturnTestValue() {
		assertThat(reviewUnderTest.getImageUrl(), is(testImageUrl));
	}

	@Test
	public void getTagsShouldReturnTestValue() {
		assertThat(reviewUnderTest.getTags(), containsInAnyOrder(testTags));
	}

	@Test
	public void getTitleShouldReturnTestValue() {
		assertThat(reviewUnderTest.getTitle(), is(testTitle));
	}

}
