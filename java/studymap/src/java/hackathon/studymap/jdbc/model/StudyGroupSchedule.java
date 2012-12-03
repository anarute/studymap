package hackathon.studymap.jdbc.model;

import java.io.Serializable;

public class StudyGroupSchedule implements Serializable {

    private int studyGroupScheduleId;
    private int studyGroupId;
    private Integer dayOfWeek;
    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    private int hour;
    private int minute;

    
    public int getStudyGroupScheduleId() {
        return this.studyGroupScheduleId;
    }

    public void setStudyGroupScheduleId(int studyGroupScheduleId) {
        this.studyGroupScheduleId = studyGroupScheduleId;
    }

    
    public int getStudyGroupId() {
        return this.studyGroupId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    
    public Integer getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    
    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    
    public Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    
    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    
    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}