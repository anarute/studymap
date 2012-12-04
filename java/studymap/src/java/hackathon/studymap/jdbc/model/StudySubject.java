package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudySubject implements Serializable {

    private Integer studySubjectId;
    private int studyMainSubjectId;
    private String description;

    
    public Integer getStudySubjectId() {
        return this.studySubjectId;
    }

    public void setStudySubjectId(Integer studySubjectId) {
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