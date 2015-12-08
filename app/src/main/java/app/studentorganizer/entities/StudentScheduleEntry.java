package app.studentorganizer.entities;

import android.content.Intent;

import org.joda.time.LocalTime;

import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 04.12.15.
 */
public class StudentScheduleEntry extends IDable {
    protected Long mUnivScheduleEntryId;
    protected Long mSubjectId;
    protected Integer mClassroom;

    public StudentScheduleEntry() {
    }

    public StudentScheduleEntry(
            Long univScheduleEntryId,
            Long subjectId,
            int classroom) {
        mUnivScheduleEntryId = univScheduleEntryId;
        mSubjectId = subjectId;
        mClassroom = classroom;
    }

    public Long getUnivScheduleEntryId() {
        return mUnivScheduleEntryId;
    }

    public void setUnivScheduleEntryId(Long univScheduleEntryId) {
        mUnivScheduleEntryId = univScheduleEntryId;
    }

    public Long getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(Long subjectId) {
        mSubjectId = subjectId;
    }

    public int getClassroom() {
        return mClassroom;
    }

    public void setClassroom(int classroom) {
        mClassroom = classroom;
    }
}
