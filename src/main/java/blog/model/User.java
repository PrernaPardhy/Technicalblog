package blog.model;

public class User {
    private String userName;
    private String passwordHash;
    private String fullName;

    public User(){}

    public User(String userName, String passwordHash, String  fullName) {
        this.userName = userName;
        this.passwordHash=passwordHash;
        this.fullName = fullName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
