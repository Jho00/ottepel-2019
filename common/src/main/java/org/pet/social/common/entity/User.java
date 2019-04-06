package org.pet.social.common.entity;

import org.pet.social.common.enums.Roles;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="first_name")
    @NotNull(message = "Имя - это обязательный параметр")
    @NotEmpty(message = "Имя должна присутстовать")
    private String firstName;

    @Column(name="last_name")
    @NotNull(message = "Фамилия - это обязательный параметр")
    @NotEmpty(message = "Фамилия должна присутстовать")
    private String lastName;

    @Column(name="email")
    @NotNull(message = "Email - это обязательный параметр")
    @NotEmpty(message = "Email должен присутстовать")
    @Email(message="Данные должны быть в формате почты")
    private String email;


    @Column(name="password_hash")
    @NotNull(message = "Password - это обязательный параметр")
    @NotEmpty(message = "Password должен присутстовать")
    private String passwordHash;

    @NotNull(message = "Salt - это обязательный параметр")
    @NotEmpty(message = "Salt должен присутстовать")
    @Column(name="salt")
    private String salt;

    @Column(name="register_at")
    private Timestamp registerAt;

    @Column(name="role")
    private Roles role;

    @Column(name="auth_token")
    private String token;

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Timestamp getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Timestamp registerAt) {
        this.registerAt = registerAt;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return user.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return this.getId() * 42;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", registerAt=" + registerAt + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public boolean canModerate() {
        return this.role == Roles.ADMIN;
    }
}
