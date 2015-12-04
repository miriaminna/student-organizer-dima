package app.studentorganizer.entities;

import org.joda.time.LocalTime;

import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 04.12.15.
 */
public class StudentScheduleEntry {
    protected int mId;
    protected int mDay;
    protected int mLessonNumber;
    protected Subject mSubject;
    protected LocalTime mStart;
    protected LocalTime mEnd;

    public StudentScheduleEntry() {
    }

    public StudentScheduleEntry(int id, int day, int lessonNumber, Subject subject,
                                LocalTime start, LocalTime end) {

        this.mId = id;
        this.mDay = day;
        this.mLessonNumber = lessonNumber;
        this.mSubject = subject;
        this.mStart = start;
        this.mEnd = end;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        this.mDay = day;
    }

    public int getLessonNumber() {
        return mLessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.mLessonNumber = lessonNumber;
    }

    public LocalTime getStart() {
        return mStart;
    }

    public void setStart(LocalTime start) {
        this.mStart = start;
    }

    public LocalTime getEnd() {
        return mEnd;
    }

    public void setEnd(LocalTime snd) {
        this.mEnd = snd;
    }

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject mSubject) {
        this.mSubject = mSubject;
    }
}
