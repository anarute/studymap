package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyMainSubject implements Serializable {

    private int studyMainSubjectId;
    private int studyAreaId;
    private String description;

    
    public int getStudyMainSubjectId() {
        return this.studyMainSubjectId;
    }

    public void setStudyMainSubjectId(int studyMainSubjectId) {
        this.studyMainSubjectId = studyMainSubjectId;
    }

    
    public int getStudyAreaId() {
        return this.studyAreaId;
    }

    public void setStudyAreaId(int studyAreaId) {
        this.studyAreaId = studyAreaId;
    }

    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}