package org.pet.social.DAL.contracts;

import org.pet.social.common.entity.Problem;
import org.pet.social.common.enums.ProblemStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemInterface  extends CrudRepository<Problem, Integer> {
    List<Problem> findTopByStatusNotOrderById(ProblemStatus status, Pageable pageable);

}
