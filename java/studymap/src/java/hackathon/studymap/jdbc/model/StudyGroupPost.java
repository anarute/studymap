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
}