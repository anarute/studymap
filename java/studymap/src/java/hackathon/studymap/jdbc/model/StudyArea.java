package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyArea implements Serializable {

    private Integer studyAreaId;
    private String description;

    public Integer getStudyAreaId() {
        return this.studyAreaId;
    }

    public void setStudyAreaId(Integer studyAreaId) {
        this.studyAreaId = studyAreaId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}