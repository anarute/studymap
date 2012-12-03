package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyGroupMember implements Serializable {

    private int studyGroupMemeberId;
    private int studyGroupId;
    private int userId;

    
    public int getStudyGroupMemeberId() {
        return this.studyGroupMemeberId;
    }

    public void setStudyGroupMemeberId(int studyGroupMemeberId) {
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