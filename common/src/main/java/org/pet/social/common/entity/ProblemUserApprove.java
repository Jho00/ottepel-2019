package org.pet.social.common.entity;

import org.pet.social.common.enums.ProblemStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="ProblemUserApprove")
public class ProblemUserApprove {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    @NotNull
    private Integer userId;

    @Column(name="problem_id")
    @NotNull
    private Integer problemId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    @NotNull
    private ProblemStatus status;


    @Column(name="approved_at")
    @NotNull
    private Timestamp approvedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Timestamp getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Timestamp approvedAt) {
        this.approvedAt = approvedAt;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
    }
}
