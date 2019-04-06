package org.pet.social.BLL.contracts.entity;

import org.pet.social.common.entity.ProblemUserApprove;
import org.pet.social.common.enums.ProblemStatus;

import java.util.List;

public interface ProblemUserApproveServiceInterface {
    boolean Approve(Integer problemId, Integer userId);
    boolean Resolve(Integer problemId, Integer userId);
    boolean Moderate(Integer problemId, Integer userId);
    List<ProblemUserApprove> GetApprovesByIdAndStatus(Integer problemId, ProblemStatus forStatus);
}
