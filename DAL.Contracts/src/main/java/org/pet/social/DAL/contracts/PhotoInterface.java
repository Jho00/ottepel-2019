package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoInterface  extends CrudRepository<Photo, Integer> {
}
