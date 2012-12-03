
package hackathon.studymap.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 03/12/2012 15:11:58
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_group_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyGroupPost.findAll", query = "SELECT s FROM StudyGroupPost s"),
    @NamedQuery(name = "StudyGroupPost.findByStudyGroupPostId", query = "SELECT s FROM StudyGroupPost s WHERE s.studyGroupPostId = :studyGroupPostId"),
    @NamedQuery(name = "StudyGroupPost.findByTitle", query = "SELECT s FROM StudyGroupPost s WHERE s.title = :title"),
    @NamedQuery(name = "StudyGroupPost.findByPosted", query = "SELECT s FROM StudyGroupPost s WHERE s.posted = :posted")})
public class StudyGroupPost implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_group_post_id", nullable = false)
    private Integer studyGroupPostId;
    @Basic(optional = false)
    @Column(nullable = false, length = 144)
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 16777215)
    private String content;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date posted;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "study_group_id", referencedColumnName = "study_group_id", nullable = false)
    @ManyToOne(optional = false)
    private StudyGroup studyGroupId;

    public StudyGroupPost() {
    }

    public StudyGroupPost(Integer studyGroupPostId) {
        this.studyGroupPostId = studyGroupPostId;
    }

    public StudyGroupPost(Integer studyGroupPostId, String title, String content, Date posted) {
        this.studyGroupPostId = studyGroupPostId;
        this.title = title;
        this.content = content;
        this.posted = posted;
    }

    public Integer getStudyGroupPostId() {
        return studyGroupPostId;
    }

    public void setStudyGroupPostId(Integer studyGroupPostId) {
        this.studyGroupPostId = studyGroupPostId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
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
        hash += (studyGroupPostId != null ? studyGroupPostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyGroupPost)) {
            return false;
        }
        StudyGroupPost other = (StudyGroupPost) object;
        if ((this.studyGroupPostId == null && other.studyGroupPostId != null) || (this.studyGroupPostId != null && !this.studyGroupPostId.equals(other.studyGroupPostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyGroupPost[ studyGroupPostId=" + studyGroupPostId + " ]";
    }

}
