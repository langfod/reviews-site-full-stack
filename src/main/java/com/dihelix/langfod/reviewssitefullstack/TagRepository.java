package com.dihelix.langfod.reviewssitefullstack;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	public Optional<Tag> findByName(String name);

	public Set<Tag> findAllByNameIn(String[] tagNames);

}
