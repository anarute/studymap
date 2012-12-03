
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 03/12/2012 15:11:58
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyGroup.findAll", query = "SELECT s FROM StudyGroup s"),
    @NamedQuery(name = "StudyGroup.findByStudyGroupId", query = "SELECT s FROM StudyGroup s WHERE s.studyGroupId = :studyGroupId"),
    @NamedQuery(name = "StudyGroup.findByDescription", query = "SELECT s FROM StudyGroup s WHERE s.description = :description"),
    @NamedQuery(name = "StudyGroup.findByLongitude", query = "SELECT s FROM StudyGroup s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "StudyGroup.findByLatitude", query = "SELECT s FROM StudyGroup s WHERE s.latitude = :latitude")})
public class StudyGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_group_id", nullable = false)
    private Integer studyGroupId;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private float longitude;
    @Basic(optional = false)
    @Column(nullable = false)
    private float latitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroupId")
    private List<StudyGroupPost> studyGroupPostList;
    @JoinColumn(name = "study_subject_id", referencedColumnName = "study_subject_id", nullable = false)
    @ManyToOne(optional = false)
    private StudySubject studySubjectId;
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroupId")
    private List<StudyGroupSchedule> studyGroupScheduleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroupId")
    private List<StudyGroupMember> studyGroupMemberList;

    public StudyGroup() {
    }

    public StudyGroup(Integer studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public StudyGroup(Integer studyGroupId, String description, float longitude, float latitude) {
        this.studyGroupId = studyGroupId;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(Integer studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @XmlTransient
    public List<StudyGroupPost> getStudyGroupPostList() {
        return studyGroupPostList;
    }

    public void setStudyGroupPostList(List<StudyGroupPost> studyGroupPostList) {
        this.studyGroupPostList = studyGroupPostList;
    }

    public StudySubject getStudySubjectId() {
        return studySubjectId;
    }

    public void setStudySubjectId(StudySubject studySubjectId) {
        this.studySubjectId = studySubjectId;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @XmlTransient
    public List<StudyGroupSchedule> getStudyGroupScheduleList() {
        return studyGroupScheduleList;
    }

    public void setStudyGroupScheduleList(List<StudyGroupSchedule> studyGroupScheduleList) {
        this.studyGroupScheduleList = studyGroupScheduleList;
    }

    @XmlTransient
    public List<StudyGroupMember> getStudyGroupMemberList() {
        return studyGroupMemberList;
    }

    public void setStudyGroupMemberList(List<StudyGroupMember> studyGroupMemberList) {
        this.studyGroupMemberList = studyGroupMemberList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyGroupId != null ? studyGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyGroup)) {
            return false;
        }
        StudyGroup other = (StudyGroup) object;
        if ((this.studyGroupId == null && other.studyGroupId != null) || (this.studyGroupId != null && !this.studyGroupId.equals(other.studyGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyGroup[ studyGroupId=" + studyGroupId + " ]";
    }

}
