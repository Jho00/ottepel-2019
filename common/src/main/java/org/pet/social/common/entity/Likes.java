package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Likes)) return false;
        Likes likes = (Likes) o;
        return likes.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, createdAt);
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                '}';
    }
}
