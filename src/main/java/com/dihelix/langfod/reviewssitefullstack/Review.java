package com.dihelix.langfod.reviewssitefullstack;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Category category;
	@Lob
	private String content;
	private LocalDate bookPublishedDate;
	@Lob
	private String description;
	private String imageUrl;
	@NaturalId
	private String title;
	
	@ManyToMany
	private Set<Tag> tags = new HashSet<>();
	
	@OneToMany(mappedBy="review",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	
	

	public Review(String title, Category category, String content, LocalDate bookPublishedDate, String description,
			String imageUrl, Tag... tags) {
		this.imageUrl = imageUrl;
		this.category = category;
		this.content = content;
		this.bookPublishedDate = bookPublishedDate;
		this.description = description;
		this.title = title;
		this.tags.addAll(Arrays.asList(tags));
	}

	public Review(String title, Category category, String content, LocalDate bookPublishedDate, String description,
			String imageUrl, Set<Tag> tags) {
		this(title, category, content, bookPublishedDate, description, imageUrl, tags.toArray(new Tag[tags.size()]));
	}
	

	public Review() {
	}
	
	public Review removeTag(Tag tag) {
		tags.remove(tag);
		return this;		
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBookPublishedDate(LocalDate bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public LocalDate getBookPublishedDate() {
		return bookPublishedDate;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	 public void addComment(Comment comment) {
	        comments.add(comment);
	        comment.setReview(this);
	    }
	 
	    public Review removeComment(Comment comment) {
	        comments.remove(comment);
	        comment.setReview(null);
	        return this;
	    }
	/*
	 * @Override public int hashCode() { return 31; }
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Review review = (Review) o;
		return Objects.equals(title, review.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

}
