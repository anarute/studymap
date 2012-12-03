package hackathon.studymap.jdbc.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int userId;
    private String login;
    private String password;
    private String name;
    private Date birthday;
    private Integer posts;
    private Double gold;

    
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    
    public Integer getPosts() {
        return this.posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    
    public Double getGold() {
        return this.gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }
}