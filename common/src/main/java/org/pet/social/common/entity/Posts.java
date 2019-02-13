package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "views", columnDefinition = "int default 0")
    private Integer views;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Column(name = "status", columnDefinition = "int default 0")
    private Integer status; // TODO: change to enum


    public int getId() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", views=" + views +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posts)) return false;
        Posts posts = (Posts) o;
        return posts.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, text, views, createdAt, status);
    }
}
