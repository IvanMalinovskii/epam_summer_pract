package by.gstu.edu.theatre.entities;

import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private Role role;

    public User(long id, String login, String password, String email, String phone, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User(String login, String password, String email, String phone, Role role) {
        this(-1, login, email, password, phone, role);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, phone, role);
    }
}
