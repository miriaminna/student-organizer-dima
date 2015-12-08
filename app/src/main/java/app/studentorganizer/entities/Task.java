package app.studentorganizer.entities;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.Objects;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Task extends IDable {
    protected Long mContentId;
    protected Long mSubjectId;
    protected String mName;
    protected Long mMultiTaskId;
    protected Integer mProgress;
    protected Integer mTarget;
    protected Double mPoints;
    protected LocalDate mDeadline;

    public Task() {
    }

    public Task(Long subjectId) {
        mSubjectId = subjectId;
    }

    public Long getMultiTaskId() {
        return mMultiTaskId;
    }

    public void setMultiTaskId(Long multiTaskId) {
        mMultiTaskId = multiTaskId;
    }

    public Long getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(Long subjectId) {
        mSubjectId = subjectId;
    }

    public Long getContentId() {
        return mContentId;
    }

    public void setContentId(Long contentId) {
        this.mContentId = contentId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Integer getProgress() {
        return mProgress;
    }

    public void setProgress(Integer progress) {
        this.mProgress = progress;
    }

    public Integer getTarget() {
        return mTarget;
    }

    public void setTarget(Integer target) {
        this.mTarget = target;
    }

    public Double getPoints() {
        return mPoints;
    }

    public void setPoints(Double points) {
        this.mPoints = points;
    }

    public Period getDue() {
        return new Period(new LocalDate(), mDeadline);
    }

    public void setDeadline(LocalDate deadline) {
        this.mDeadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.mDeadline;
    }

    String getProgressString() {
        return String.format("%s / %s", this.mProgress, this.mTarget);
    }

    Boolean isCompleted() {
        return mProgress.equals(mTarget);
    }

    @Override
    public String toString() {
        return getName();
    }
}
