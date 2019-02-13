package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="photos")
public class Photos {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    @NotNull
    private int userId;

    @Column(name="album_id")
    @NotNull
    private int albumId;

    @Column(name="image")
    @NotNull
    private String image; // ?? type

    @Column(name="description")
    private String description;

    @Column(name="added_at")
    @NotNull
    private Timestamp addedAt;


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

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
        return "Photos{" +
                "id=" + id +
                ", userId=" + userId +
                ", albumId=" + albumId +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photos)) return false;
        Photos photos = (Photos) o;
        return photos.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, albumId, image, description, addedAt);
    }
}
