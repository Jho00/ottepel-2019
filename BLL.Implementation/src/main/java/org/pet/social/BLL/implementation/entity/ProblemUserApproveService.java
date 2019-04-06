package org.pet.social.BLL.implementation.entity;


import org.pet.social.BLL.contracts.entity.ProblemUserApproveServiceInterface;
import org.pet.social.DAL.contracts.ProblemUserApproveInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.ProblemUserApprove;
import org.pet.social.common.entity.User;
import org.pet.social.common.enums.ProblemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProblemUserApproveService implements ProblemUserApproveServiceInterface {

    @Autowired
    private UserInterface users;
    @Autowired
    private ProblemUserApproveInterface probUsers;

    public boolean Approve(Integer problemId, Integer userId){
        return CreateStatus(problemId,userId,ProblemStatus.CONFIRMED);
    }
    public boolean Resolve(Integer problemId, Integer userId){
        return CreateStatus(problemId,userId,ProblemStatus.RESOLVED);
    }

    public boolean Moderate(Integer problemId, Integer userId){
        return CreateStatus(problemId,userId,ProblemStatus.MODERATION);
    }

    public List<ProblemUserApprove> GetApprovesByIdAndStatus(Integer problemId, ProblemStatus forStatus){
        return probUsers.findByProblemIdAndStatus(problemId, forStatus);
    }

    boolean CreateStatus(Integer problemId, Integer userId, ProblemStatus status){
        User user = users.findById(userId).orElse(null);
        if(user == null){
            return false;
        }
        List<ProblemUserApprove> approves = probUsers.findByProblemIdAndUserId(problemId, userId);
        if(approves.size() > 0){
            return false;
        }
        ProblemUserApprove pua = new ProblemUserApprove();
        pua.setApprovedAt(new Timestamp(System.currentTimeMillis()));
        pua.setProblemId(problemId);
        pua.setUserId(userId);
        pua.setStatus(status);
        return true;
    }

}
