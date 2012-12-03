package hackathon.studymap.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 03/12/2012 15:11:58
 *
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_area", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"description"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyArea.findAll", query = "SELECT s FROM StudyArea s"),
    @NamedQuery(name = "StudyArea.findByStudyAreaId", query = "SELECT s FROM StudyArea s WHERE s.studyAreaId = :studyAreaId"),
    @NamedQuery(name = "StudyArea.findByDescription", query = "SELECT s FROM StudyArea s WHERE s.description = :description")})
public class StudyArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_area_id", nullable = false)
    private Integer studyAreaId;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyAreaId")
    private List<StudyMainSubject> studyMainSubjectList;

    public StudyArea() {
    }

    public StudyArea(Integer studyAreaId) {
        this.studyAreaId = studyAreaId;
    }

    public StudyArea(Integer studyAreaId, String description) {
        this.studyAreaId = studyAreaId;
        this.description = description;
    }

    public Integer getStudyAreaId() {
        return studyAreaId;
    }

    public void setStudyAreaId(Integer studyAreaId) {
        this.studyAreaId = studyAreaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<StudyMainSubject> getStudyMainSubjectList() {
        return studyMainSubjectList;
    }

    public void setStudyMainSubjectList(List<StudyMainSubject> studyMainSubjectList) {
        this.studyMainSubjectList = studyMainSubjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyAreaId != null ? studyAreaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyArea)) {
            return false;
        }
        StudyArea other = (StudyArea) object;
        if ((this.studyAreaId == null && other.studyAreaId != null) || (this.studyAreaId != null && !this.studyAreaId.equals(other.studyAreaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyArea[ studyAreaId=" + studyAreaId + "," + description + " ]";
    }
}
