package org.pet.social.BLL.contracts.entity;

import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.Resolvers;
import org.pet.social.common.exceptions.*;
import org.pet.social.common.servicesClasses.GeoPoint;
import org.pet.social.common.viewmodels.AddProblemViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProblemServiceInterface {
     List<Problem> getLimited(Integer limit, Integer offset);
     Optional<Problem> get(Integer id);

     boolean add(User user, AddProblemViewModel model);

     boolean resolve(Integer id) throws ProblemNotApprovedException, ObjectNotFoundException;
     boolean approve(Integer id) throws ProblemShouldNotApprove, ObjectNotFoundException;

     void setResolver(Integer id, Resolvers resolver) throws ObjectNotFoundException;

     boolean moderate(Integer id, User moderator) throws NotModeratorException, ObjectNotFoundException, ShouldNotModerateException;
}
