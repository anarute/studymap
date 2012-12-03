package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudySubject implements Serializable {

    private int studySubjectId;
    private int studyMainSubjectId;
    private String description;

    
    public int getStudySubjectId() {
        return this.studySubjectId;
    }

    public void setStudySubjectId(int studySubjectId) {
        this.studySubjectId = studySubjectId;
    }

    
    public int getStudyMainSubjectId() {
        return this.studyMainSubjectId;
    }

    public void setStudyMainSubjectId(int studyMainSubjectId) {
        this.studyMainSubjectId = studyMainSubjectId;
    }

    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}