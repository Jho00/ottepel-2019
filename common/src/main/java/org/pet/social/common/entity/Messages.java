package org.pet.social.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name =  "from_id")
    @NotNull
    private int fromId;

    @Column(name = "to_id")
    @NotNull
    private int toId;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
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

    @Column(name = "status", columnDefinition = "int default 0")
    @NotNull
    private int status; // TODO: enum

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Messages)) return false;
        Messages messages = (Messages) o;
        return messages.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromId, toId, text, createdAt, status);
    }
}
