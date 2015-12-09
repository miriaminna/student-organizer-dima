package app.studentorganizer.entities;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TestType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Test extends IDable {
    protected LocalDate mDate;
    protected Long mContentId;
    protected Integer mResult;
    protected Integer mPoints;
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

    public Integer getPoints() {
        return mPoints;
    }

    public void setPoints(Integer points) {
        this.mPoints = points;
    }

    public Integer getResult() {
        return mResult;
    }

    public void setResult(Integer result) {
        mResult = result;
    }

    @Override
    public String toString() {
        return getTestType().toString();
    }
}
