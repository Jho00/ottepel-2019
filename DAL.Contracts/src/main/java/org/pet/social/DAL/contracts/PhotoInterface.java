package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoInterface  extends CrudRepository<Photo, Integer> {
    List<Photo> findByProblemId(Integer problemId);
    List<Photo> findTop1ByProblemId(Integer problemId);
}
