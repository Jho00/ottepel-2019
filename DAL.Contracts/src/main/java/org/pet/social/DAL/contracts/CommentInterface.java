package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentInterface  extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findAllByPostId(Integer id);
}
