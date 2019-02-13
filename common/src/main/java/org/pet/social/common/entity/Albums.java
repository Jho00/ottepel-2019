package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="albums")
public class Albums {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    @NotNull(message = "id пользователя - это обязательный параметр")
    @NotEmpty(message = "id пользователя  должно присутстовать")
    private int userId;

    @Column(name="main_photo_id")
    private int mainPhotoId;

    @Column(name="title")
    @NotNull(message = "Заголовок- это обязательный параметр")
    @NotEmpty(message = "Заголовок должен присутстовать")
    private String title;

    @Column(name="is_private", columnDefinition = "bool default false")
    private boolean isPrivate;

    @Column(name="created_at")
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

    public int getMainPhotoId() {
        return mainPhotoId;
    }

    public void setMainPhotoId(int mainPhotoId) {
        this.mainPhotoId = mainPhotoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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
        if (!(o instanceof Albums)) return false;
        Albums albums = (Albums) o;
        return this.getId() == albums.getId();
    }

    @Override
    public String toString() {
        return "Albums{" +
                "id=" + id +
                ", userId=" + userId +
                ", mainPhotoId=" + mainPhotoId +
                ", title='" + title + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return id * 228;
    }
}
