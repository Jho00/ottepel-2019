package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface  extends CrudRepository<Category, Integer> {
}
