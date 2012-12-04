package hackathon.studymap.jdbc.model;

import java.io.Serializable;
import java.util.Date;

public class StudyGroupPost implements Serializable {

    private Integer studyGroupPostId;
    private int studyGroupId;
    private int userId;
    private String title;
    private String content;
    private Date posted;
    private String login;
    private long fbUserId;
    private String fbUsername;
    private Date creation;

    
    public Integer getStudyGroupPostId() {
        return this.studyGroupPostId;
    }

    public void setStudyGroupPostId(Integer studyGroupPostId) {
        this.studyGroupPostId = studyGroupPostId;
    }

    
    public int getStudyGroupId() {
        return this.studyGroupId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public Date getPosted() {
        return this.posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(long fbUserId) {
        this.fbUserId = fbUserId;
    }

    public String getFbUsername() {
        return fbUsername;
    }

    public void setFbUsername(String fbUsername) {
        this.fbUsername = fbUsername;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
    
    
}