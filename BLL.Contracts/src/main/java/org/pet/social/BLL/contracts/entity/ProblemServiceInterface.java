package org.pet.social.BLL.contracts.entity;

import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.enums.Resolvers;
import org.pet.social.common.exceptions.*;
import org.pet.social.common.viewmodels.AddProblemViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProblemServiceInterface {
     Page<Problem> getLimited(ProblemStatus status, Pageable pageable);
     Optional<Problem> get(Integer id);

     boolean add(User user, AddProblemViewModel model);

     boolean resolve(Integer id, Integer userId) throws ProblemNotApprovedException, ObjectNotFoundException;
     boolean approve(Integer id, Integer userId) throws ProblemShouldNotApprove, ObjectNotFoundException;

     void setResolver(Integer id, Resolvers resolver) throws ObjectNotFoundException;

     boolean moderate(Integer id, User moderator) throws NotModeratorException, ObjectNotFoundException, ShouldNotModerateException;

     Problem getProblem();
}
