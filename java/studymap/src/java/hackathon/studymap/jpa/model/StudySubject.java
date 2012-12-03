
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
@Table(name = "study_subject", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"description"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudySubject.findAll", query = "SELECT s FROM StudySubject s"),
    @NamedQuery(name = "StudySubject.findByStudySubjectId", query = "SELECT s FROM StudySubject s WHERE s.studySubjectId = :studySubjectId"),
    @NamedQuery(name = "StudySubject.findByDescription", query = "SELECT s FROM StudySubject s WHERE s.description = :description")})
public class StudySubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_subject_id", nullable = false)
    private Integer studySubjectId;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studySubjectId")
    private List<StudyGroup> studyGroupList;
    @JoinColumn(name = "study_main_subject_id", referencedColumnName = "study_main_subject_id", nullable = false)
    @ManyToOne(optional = false)
    private StudyMainSubject studyMainSubjectId;

    public StudySubject() {
    }

    public StudySubject(Integer studySubjectId) {
        this.studySubjectId = studySubjectId;
    }

    public StudySubject(Integer studySubjectId, String description) {
        this.studySubjectId = studySubjectId;
        this.description = description;
    }

    public Integer getStudySubjectId() {
        return studySubjectId;
    }

    public void setStudySubjectId(Integer studySubjectId) {
        this.studySubjectId = studySubjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<StudyGroup> getStudyGroupList() {
        return studyGroupList;
    }

    public void setStudyGroupList(List<StudyGroup> studyGroupList) {
        this.studyGroupList = studyGroupList;
    }

    public StudyMainSubject getStudyMainSubjectId() {
        return studyMainSubjectId;
    }

    public void setStudyMainSubjectId(StudyMainSubject studyMainSubjectId) {
        this.studyMainSubjectId = studyMainSubjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studySubjectId != null ? studySubjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudySubject)) {
            return false;
        }
        StudySubject other = (StudySubject) object;
        if ((this.studySubjectId == null && other.studySubjectId != null) || (this.studySubjectId != null && !this.studySubjectId.equals(other.studySubjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudySubject[ studySubjectId=" + studySubjectId + " ]";
    }

}
