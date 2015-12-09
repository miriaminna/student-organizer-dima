package app.studentorganizer.entities;

import org.joda.time.LocalTime;

import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 04.12.15.
 */
public class UnivScheduleEntry extends IDable {
    protected int mLessonNumber;
    protected LocalTime mStart;
    protected LocalTime mEnd;

    public UnivScheduleEntry() {
        mStart = new LocalTime();
        mEnd = new LocalTime();
    }

    public UnivScheduleEntry(Long id, int lessonNumber,
                             LocalTime start, LocalTime end) {

        setId(id);
        this.mLessonNumber = lessonNumber;
        this.mStart = start;
        this.mEnd = end;
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

    @Override
    public String toString() {
        return String.format("Pair %d: %s-%s",
                getLessonNumber(),
                getStart().toString("HH:mm"),
                getEnd().toString("HH:mm"));
    }
}
