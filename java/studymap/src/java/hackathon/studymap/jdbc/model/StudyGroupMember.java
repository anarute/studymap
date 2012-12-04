package hackathon.studymap.jdbc.model;

import java.io.Serializable;
import java.util.Date;

public class StudyGroupMember implements Serializable {

    private Integer studyGroupMemeberId;
    private int studyGroupId;
    private int userId;
    private String login;
    private long fbUserId;
    private String fbUsername;
    private Date creation;

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

    public Integer getStudyGroupMemeberId() {
        return this.studyGroupMemeberId;
    }

    public void setStudyGroupMemeberId(Integer studyGroupMemeberId) {
        this.studyGroupMemeberId = studyGroupMemeberId;
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
}