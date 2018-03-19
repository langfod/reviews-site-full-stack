package com.dihelix.langfod.reviewssitefullstack;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByName(String categoryName);

}
