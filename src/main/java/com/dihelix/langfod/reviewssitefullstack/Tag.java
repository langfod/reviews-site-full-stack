package com.dihelix.langfod.reviewssitefullstack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private Long id;
	@NaturalId
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags",cascade=CascadeType.ALL)
	private Set<Review> reviews = new HashSet<>();

	public Tag() {
	}

	public Tag(String tagName) {
		this.name = tagName;
	}
	
	public Tag(String tagName, Review review) {
		this.name = tagName;
		this.reviews.add(review);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return name;
	}

	public void removeReview(Review review) {
		reviews.remove(review);
	}

}
