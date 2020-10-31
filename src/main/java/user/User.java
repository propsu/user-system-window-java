package user;

import java.util.Date;

public class User {
    private final int id;
    private final String username;
    private String password;
    private Date createdAt, modifiedAt;

    public User(int id, String login, String password, Date createdAt, Date modifiedAt) {
        this.username = login;
        this.password = password;
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getModifiedAt() { return modifiedAt; }

    public void setModifiedAt(Date modifiedAt) { this.modifiedAt = modifiedAt; }
}
