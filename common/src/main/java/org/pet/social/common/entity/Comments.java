package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Column(name = "status", columnDefinition = "int default 0")
    @NotNull
    private int status; // TODO: change to enum

    @Column(name = "post_id")
    @NotNull
    private int postId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", postId=" + postId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comments)) return false;
        Comments comments = (Comments) o;
        return comments.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, createdAt, status, postId);
    }
}
