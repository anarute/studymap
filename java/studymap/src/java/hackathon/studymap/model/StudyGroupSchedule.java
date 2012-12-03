
package hackathon.studymap.model;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 03/12/2012 15:11:58
 * @author murilodemoraestuvani
 */
@Entity
@Table(name = "study_group_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyGroupSchedule.findAll", query = "SELECT s FROM StudyGroupSchedule s"),
    @NamedQuery(name = "StudyGroupSchedule.findByStudyGroupScheduleId", query = "SELECT s FROM StudyGroupSchedule s WHERE s.studyGroupScheduleId = :studyGroupScheduleId"),
    @NamedQuery(name = "StudyGroupSchedule.findByDayOfWeek", query = "SELECT s FROM StudyGroupSchedule s WHERE s.dayOfWeek = :dayOfWeek"),
    @NamedQuery(name = "StudyGroupSchedule.findByYear", query = "SELECT s FROM StudyGroupSchedule s WHERE s.year = :year"),
    @NamedQuery(name = "StudyGroupSchedule.findByMonth", query = "SELECT s FROM StudyGroupSchedule s WHERE s.month = :month"),
    @NamedQuery(name = "StudyGroupSchedule.findByDayOfMonth", query = "SELECT s FROM StudyGroupSchedule s WHERE s.dayOfMonth = :dayOfMonth"),
    @NamedQuery(name = "StudyGroupSchedule.findByHour", query = "SELECT s FROM StudyGroupSchedule s WHERE s.hour = :hour"),
    @NamedQuery(name = "StudyGroupSchedule.findByMinute", query = "SELECT s FROM StudyGroupSchedule s WHERE s.minute = :minute")})
public class StudyGroupSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "study_group_schedule_id", nullable = false)
    private Integer studyGroupScheduleId;
    @Column(name = "day_of_week")
    private Boolean dayOfWeek;
    private Integer year;
    private Short month;
    @Column(name = "day_of_month")
    private Short dayOfMonth;
    @Basic(optional = false)
    @Column(nullable = false)
    private short hour;
    @Basic(optional = false)
    @Column(nullable = false)
    private short minute;
    @JoinColumn(name = "study_group_id", referencedColumnName = "study_group_id", nullable = false)
    @ManyToOne(optional = false)
    private StudyGroup studyGroupId;

    public StudyGroupSchedule() {
    }

    public StudyGroupSchedule(Integer studyGroupScheduleId) {
        this.studyGroupScheduleId = studyGroupScheduleId;
    }

    public StudyGroupSchedule(Integer studyGroupScheduleId, short hour, short minute) {
        this.studyGroupScheduleId = studyGroupScheduleId;
        this.hour = hour;
        this.minute = minute;
    }

    public Integer getStudyGroupScheduleId() {
        return studyGroupScheduleId;
    }

    public void setStudyGroupScheduleId(Integer studyGroupScheduleId) {
        this.studyGroupScheduleId = studyGroupScheduleId;
    }

    public Boolean getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Boolean dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Short getMonth() {
        return month;
    }

    public void setMonth(Short month) {
        this.month = month;
    }

    public Short getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Short dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public short getMinute() {
        return minute;
    }

    public void setMinute(short minute) {
        this.minute = minute;
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
        hash += (studyGroupScheduleId != null ? studyGroupScheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyGroupSchedule)) {
            return false;
        }
        StudyGroupSchedule other = (StudyGroupSchedule) object;
        if ((this.studyGroupScheduleId == null && other.studyGroupScheduleId != null) || (this.studyGroupScheduleId != null && !this.studyGroupScheduleId.equals(other.studyGroupScheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hackathon.studymap.model.StudyGroupSchedule[ studyGroupScheduleId=" + studyGroupScheduleId + " ]";
    }

}
