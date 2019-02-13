package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="friends")
public class Friends {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="friend_id")
    @NotNull(message = "id друга - это обязательный параметр")
    @NotEmpty(message = "id друга  должно присутстовать")
    private Integer freindId;

    @Column(name="user_id")
    @NotNull(message = "id пользователя - это обязательный параметр")
    @NotEmpty(message = "id пользователя должно присутстовать")
    private Integer userId;

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", freindId=" + freindId +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFreindId() {
        return freindId;
    }

    public void setFreindId(Integer freindId) {
        this.freindId = freindId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friends)) return false;
        Friends friends = (Friends) o;
        return this.id == friends.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, freindId, userId);
    }
}
