package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Category;
import org.pet.social.common.entity.ProblemUserApprove;
import org.pet.social.common.enums.ProblemStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemUserApproveInterface extends CrudRepository<Category, Integer> {
    List<ProblemUserApprove> findByProblemIdAndUserId(Integer problemId, Integer userId);
    List<ProblemUserApprove> findByProblemIdAndStatus(Integer problemId, ProblemStatus status);
}
