package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="Photo")
public class Photo {
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

    @Column(name="image")
    @NotNull
    private String image; // ?? Base64

    @Column(name="description")
    private String description;

    @Column(name="added_at")
    @NotNull
    private Timestamp addedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(int albumId) {
        this.problemId = albumId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return photo.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, problemId, image, description, addedAt);
    }
}
