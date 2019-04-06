package org.pet.social.BLL.contracts.entity;

public interface ProblemUserApproveServiceInterface {
    boolean Approve(Integer problemId, Integer userId);
    boolean Resolve(Integer problemId, Integer userId);
    boolean Moderate(Integer problemId, Integer userId);
}
