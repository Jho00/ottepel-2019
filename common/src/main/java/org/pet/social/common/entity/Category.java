package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    @NotNull(message = "id пользователя - это обязательный параметр")
    @NotEmpty(message = "id пользователя  должно присутстовать")
    private int userId;

    @Column(name="title")
    @NotNull(message = "Заголовок- это обязательный параметр")
    @NotEmpty(message = "Заголовок должен присутстовать")
    private String title;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return this.getId() == category.getId();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return id * 228;
    }
}
