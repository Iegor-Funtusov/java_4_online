package ua.com.alevel.entity;

import java.io.Serializable;

public class Student extends BaseEntity implements Serializable {

    private String name;
    private String email;
    private String gitHubAcc;
    private String telegramAcc;
    private int friendsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitHubAcc() {
        return gitHubAcc;
    }

    public void setGitHubAcc(String gitHubAcc) {
        this.gitHubAcc = gitHubAcc;
    }

    public String getTelegramAcc() {
        return telegramAcc;
    }

    public void setTelegramAcc(String telegramAcc) {
        this.telegramAcc = telegramAcc;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gitHubAcc='" + gitHubAcc + '\'' +
                ", telegramAcc='" + telegramAcc + '\'' +
                '}';
    }
}
