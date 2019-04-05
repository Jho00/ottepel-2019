package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Problem;
import org.springframework.data.repository.CrudRepository;

public interface ProblemInterface  extends CrudRepository<Problem, Integer> {
}
