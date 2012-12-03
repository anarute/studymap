
package hackathon.studymap.jpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 03/12/2012 15:11:58
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_group_member", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"study_group_id", "user_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyGroupMember.findAll", query = "SELECT s FROM StudyGroupMember s"),
    @NamedQuery(name = "StudyGroupMember.findByStudyGroupMemeberId", query = "SELECT s FROM StudyGroupMember s WHERE s.studyGroupMemeberId = :studyGroupMemeberId")})
public class StudyGroupMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_group_memeber_id", nullable = false)
    private Integer studyGroupMemeberId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "study_group_id", referencedColumnName = "study_group_id", nullable = false)
    @ManyToOne(optional = false)
    private StudyGroup studyGroupId;

    public StudyGroupMember() {
    }

    public StudyGroupMember(Integer studyGroupMemeberId) {
        this.studyGroupMemeberId = studyGroupMemeberId;
    }

    public Integer getStudyGroupMemeberId() {
        return studyGroupMemeberId;
    }

    public void setStudyGroupMemeberId(Integer studyGroupMemeberId) {
        this.studyGroupMemeberId = studyGroupMemeberId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public StudyGroup getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(StudyGroup studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyGroupMemeberId != null ? studyGroupMemeberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyGroupMember)) {
            return false;
        }
        StudyGroupMember other = (StudyGroupMember) object;
        if ((this.studyGroupMemeberId == null && other.studyGroupMemeberId != null) || (this.studyGroupMemeberId != null && !this.studyGroupMemeberId.equals(other.studyGroupMemeberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyGroupMember[ studyGroupMemeberId=" + studyGroupMemeberId + " ]";
    }

}
