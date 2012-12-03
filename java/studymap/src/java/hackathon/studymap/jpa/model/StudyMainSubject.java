
package hackathon.studymap.jpa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 03/12/2012 15:11:58
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_main_subject", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"description"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyMainSubject.findAll", query = "SELECT s FROM StudyMainSubject s"),
    @NamedQuery(name = "StudyMainSubject.findByStudyMainSubjectId", query = "SELECT s FROM StudyMainSubject s WHERE s.studyMainSubjectId = :studyMainSubjectId"),
    @NamedQuery(name = "StudyMainSubject.findByDescription", query = "SELECT s FROM StudyMainSubject s WHERE s.description = :description")})
public class StudyMainSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_main_subject_id", nullable = false)
    private Integer studyMainSubjectId;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String description;
    @JoinColumn(name = "study_area_id", referencedColumnName = "study_area_id", nullable = false)
    @ManyToOne(optional = false)
    private StudyArea studyAreaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyMainSubjectId")
    private List<StudySubject> studySubjectList;

    public StudyMainSubject() {
    }

    public StudyMainSubject(Integer studyMainSubjectId) {
        this.studyMainSubjectId = studyMainSubjectId;
    }

    public StudyMainSubject(Integer studyMainSubjectId, String description) {
        this.studyMainSubjectId = studyMainSubjectId;
        this.description = description;
    }

    public Integer getStudyMainSubjectId() {
        return studyMainSubjectId;
    }

    public void setStudyMainSubjectId(Integer studyMainSubjectId) {
        this.studyMainSubjectId = studyMainSubjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudyArea getStudyAreaId() {
        return studyAreaId;
    }

    public void setStudyAreaId(StudyArea studyAreaId) {
        this.studyAreaId = studyAreaId;
    }

    @XmlTransient
    public List<StudySubject> getStudySubjectList() {
        return studySubjectList;
    }

    public void setStudySubjectList(List<StudySubject> studySubjectList) {
        this.studySubjectList = studySubjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyMainSubjectId != null ? studyMainSubjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyMainSubject)) {
            return false;
        }
        StudyMainSubject other = (StudyMainSubject) object;
        if ((this.studyMainSubjectId == null && other.studyMainSubjectId != null) || (this.studyMainSubjectId != null && !this.studyMainSubjectId.equals(other.studyMainSubjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyMainSubject[ studyMainSubjectId=" + studyMainSubjectId + " ]";
    }

}
