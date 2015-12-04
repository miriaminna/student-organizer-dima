package app.studentorganizer.entities;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TestType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Test {
    protected Integer mId;
    protected LocalDate mDate;
    protected Content mContent;
    protected Double mPoints;
    protected Subject mSubject;
    protected Integer mSubjectId;
    protected TestType mTestType;

    public Test() {

    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public TestType getTestType() {
        return mTestType;
    }

    public void setTestType(TestType testType) {
        mTestType = testType;
    }

    public Integer getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(Integer subjectId) {
        mSubjectId = subjectId;
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
