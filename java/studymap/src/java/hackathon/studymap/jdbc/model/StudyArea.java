package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyArea implements Serializable {

    private int studyAreaId;
    private String description;

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