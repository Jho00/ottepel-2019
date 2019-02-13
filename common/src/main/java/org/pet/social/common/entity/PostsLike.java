package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "posts_like")
public class PostsLike {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "like_id")
    @NotNull
    private int likeId;

    @Column(name = "posts_id")
    @NotNull
    private int postsId;

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

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    @Override
    public String toString() {
        return "PostsLike{" +
                "id=" + id +
                ", likeId=" + likeId +
                ", postsId=" + postsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostsLike)) return false;
        PostsLike postsLike = (PostsLike) o;
        return postsLike.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likeId, postsId);
    }
}
