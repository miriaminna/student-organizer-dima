package app.studentorganizer.entities;

import org.joda.time.LocalDate;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Test {
    protected LocalDate mDate;
    protected Content mContent;
    protected Double mPoints;
    protected Subject mSubject;

    public Test() {

    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setDate(LocalDate date) {
        this.mDate = date;
    }

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        this.mContent = content;
    }

    public Double getPoints() {
        return mPoints;
    }

    public void setPoints(Double points) {
        this.mPoints = points;
    }

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject subject) {
        this.mSubject = subject;
    }
}
