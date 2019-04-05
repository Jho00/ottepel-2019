package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.UserProblem;
import org.springframework.data.repository.CrudRepository;

public interface UserProblemInterface  extends CrudRepository<UserProblem, Integer> {
}
