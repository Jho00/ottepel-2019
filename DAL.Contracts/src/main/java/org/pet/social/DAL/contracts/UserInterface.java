package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInterface  extends CrudRepository<User, Integer> {
    public List<User> findByEmail(String email);
    public User findByToken(String token);
}
