package app.studentorganizer.entities;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TestType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Test extends IDable {
    protected LocalDate mDate;
    protected Long mContentId;
    protected Double mPoints;
    protected Long mSubjectId;
    protected TestType mTestType;

    public Test() {
    }

    public TestType getTestType() {
        return mTestType;
    }

    public void setTestType(TestType testType) {
        mTestType = testType;
    }

    public Long getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(Long subjectId) {
        mSubjectId = subjectId;
    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setDate(LocalDate date) {
        this.mDate = date;
    }

    public Long getContentId() {
        return mContentId;
    }

    public void setContentId(Long contentId) {
        this.mContentId = contentId;
    }

    public Double getPoints() {
        return mPoints;
    }

    public void setPoints(Double points) {
        this.mPoints = points;
    }

    @Override
    public String toString() {
        return getTestType().toString();
    }
}
