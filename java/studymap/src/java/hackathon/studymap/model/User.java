
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
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"login"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String login;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<StudyGroupPost> studyGroupPostList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private List<StudyGroup> studyGroupList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<StudyGroupMember> studyGroupMemberList;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String login, String email) {
        this.userId = userId;
        this.login = login;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<StudyGroupPost> getStudyGroupPostList() {
        return studyGroupPostList;
    }

    public void setStudyGroupPostList(List<StudyGroupPost> studyGroupPostList) {
        this.studyGroupPostList = studyGroupPostList;
    }

    @XmlTransient
    public List<StudyGroup> getStudyGroupList() {
        return studyGroupList;
    }

    public void setStudyGroupList(List<StudyGroup> studyGroupList) {
        this.studyGroupList = studyGroupList;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.User[ userId=" + userId + " ]";
    }

}
