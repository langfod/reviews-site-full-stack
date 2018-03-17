package com.dihelix.langfod.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByTags(Tag tag);
	Collection<Review> findByCategory(Category category);
}
