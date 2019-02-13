package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "comments_like")
public class CommentsLike {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "like_id")
    @NotNull
    private int likeId;

    @Column(name = "comment_id")
    @NotNull
    private int commentId;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentsLike{" +
                "id=" + id +
                ", likeId=" + likeId +
                ", commentId=" + commentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentsLike)) return false;
        CommentsLike ob = (CommentsLike) o;
        return this.getId() == ob.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likeId, commentId);
    }
}
