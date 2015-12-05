package app.studentorganizer.entities;

import org.joda.time.LocalTime;

import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 04.12.15.
 */
public class StudentScheduleEntry {
    protected Integer mId;
    protected UnivScheduleEntry mUnivScheduleEntry;
    protected Integer mUnivScheduleEntryId;
    protected Subject mSubject;
    protected int mSubjectId;
    protected int mClassroom;

    public StudentScheduleEntry() {
    }

    public StudentScheduleEntry(
            UnivScheduleEntry univScheduleEntry,
            Subject subject,
            int classroom) {
        mUnivScheduleEntry = univScheduleEntry;
        mSubject = subject;
        mSubjectId = (mSubject == null) ? 0 : mSubject.getId();
        mClassroom = classroom;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getUnivScheduleEntryId() {
        return mUnivScheduleEntryId;
    }

    public void setUnivScheduleEntryId(Integer univScheduleEntryId) {
        mUnivScheduleEntryId = univScheduleEntryId;
    }

    public UnivScheduleEntry getUnivScheduleEntry() {
        return mUnivScheduleEntry;
    }

    public void setUnivScheduleEntry(UnivScheduleEntry univScheduleEntry) {
        mUnivScheduleEntry = univScheduleEntry;
    }

    public int getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(int subjectId) {
        mSubjectId = subjectId;
    }

    public int getClassroom() {
        return mClassroom;
    }

    public void setClassroom(int classroom) {
        mClassroom = classroom;
    }

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject mSubject) {
        this.mSubject = mSubject;
    }
}
