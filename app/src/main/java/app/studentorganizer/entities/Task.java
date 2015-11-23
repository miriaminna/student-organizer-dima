package app.studentorganizer.entities;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.Objects;

import javax.security.auth.Subject;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Task {
    protected Content mContent;
    protected Subject mSubject;
    protected String mName;
    protected Integer mProgress;
    protected Integer mTarget;
    protected Double mPoints;
    protected LocalDate mDeadline;

    public Task(Subject subject) {
        mContent = new Content();
        mSubject = subject;
    }

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        this.mContent = content;
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

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject subject) {
        this.mSubject = subject;
    }

    String getProgressString() {
        return String.format("%s / %s", this.mProgress, this.mTarget);
    }

    Boolean isCompleted() {
        return mProgress.equals(mTarget);
    }
}
