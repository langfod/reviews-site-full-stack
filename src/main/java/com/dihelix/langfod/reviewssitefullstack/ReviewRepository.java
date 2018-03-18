package com.dihelix.langfod.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;


public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByTags(Tag tag);
	Collection<Review> findByTags_Id(Long tagId);
	Collection<Review> findByCategory(Category category);
	
}
