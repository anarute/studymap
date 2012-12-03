package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyGroup implements Serializable {

    private int studyGroupId;
    private int ownerId;
    private int studySubjectId;
    private String description;
    private double longitude;
    private double latitude;

    public int getStudyGroupId() {
        return this.studyGroupId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    
    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    
    public int getStudySubjectId() {
        return this.studySubjectId;
    }

    public void setStudySubjectId(int studySubjectId) {
        this.studySubjectId = studySubjectId;
    }

    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}