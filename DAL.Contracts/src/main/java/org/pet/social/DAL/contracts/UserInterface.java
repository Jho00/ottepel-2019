package org.pet.social.DAL.contracts;

import social.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInterface  extends CrudRepository<User, Integer> {

}
