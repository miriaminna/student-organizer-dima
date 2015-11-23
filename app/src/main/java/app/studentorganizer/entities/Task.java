package app.studentorganizer.entities;

import org.joda.time.LocalDate;
import org.joda.time.Period;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Task {
    private Content mContent;
//    private Subject mSubject;
    private String mName;
    private Integer mProgress;
    private Integer mTarget;
    private Double mPoints;
    private LocalDate mDeadline;

    public Task() {

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

    String getProgressString() {
        return String.format("%s / %s", this.mProgress, this.mTarget);
    }
}
