package org.pet.social.common.entity;

import org.pet.social.common.enums.ProblemStatus;
import org.pet.social.common.enums.Resolvers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Problem")
public class Problem {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "title")
    @NotNull
    @NotEmpty
    private String title;

    @Column(name = "text")
    @NotNull
    @NotEmpty
    private String text;

    @Column(name = "views", columnDefinition = "int default 0")
    private Integer views;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "int default 0")
    private ProblemStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "resolver", columnDefinition = "int default 0")
    private Resolvers resolver;


    @Column(name = "category_id")
    @NotNull
    private int categoryId;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "approve_count", columnDefinition = "int default 0")
    private int approveCount;

    @Column(name = "resolve_count", columnDefinition = "int default 0")
    private int resolveCount;

    @Column(name = "likes_count", columnDefinition = "int default 0")
    private int likesCount;

    @Column(name = "dislikes_count", columnDefinition = "int default 0")
    private int dislikesCount;

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }


    public Resolvers getResolver() {
        return resolver;
    }

    public void setResolver(Resolvers resolver) {
        this.resolver = resolver;
    }


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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getApproveCount() {
        return approveCount;
    }

    public void setApproveCount(int approveCount) {
        this.approveCount = approveCount;
    }

    public int getResolveCount() {
        return resolveCount;
    }

    public void setResolveCount(int resolveCount) {
        this.resolveCount = resolveCount;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", views=" + views +
                ", createdAt=" + createdAt +
                ", categoryId=" + categoryId+
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Problem)) return false;
        Problem problem = (Problem) o;
        return problem.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, text, views, createdAt, status);
    }
}
