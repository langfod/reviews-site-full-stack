package com.dihelix.langfod.reviewssitefullstack;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue Long id;
	@NaturalId
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private Set<Review> reviews;

	@JsonIgnore
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Category() {
	}

	public Category(String tagName) {
		this.name = tagName;
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


	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Category tag = (Category) o;
		return Objects.equals(name, tag.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
