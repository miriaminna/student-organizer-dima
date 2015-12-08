package app.studentorganizer.entities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Subject extends IDable {
    protected List<Long> mTasksIds;
    protected String mName;
    protected Long mTeacherId;
    protected SubjectType mType;
    protected Long mContentId;
    protected List<Long> mTestsIds;
    protected ColorTag mColorTag;

    public Subject() {
        mTasksIds = new ArrayList<>();
        mTestsIds = new ArrayList<>();
    }

    public ColorTag getColorTag() {
        return mColorTag;
    }

    public void setColorTag(ColorTag colorTag) {
        mColorTag = colorTag;
    }

    public Long getTeacherId() {
        return mTeacherId;
    }

    public void setTeacherId(Long teacherId) {
        mTeacherId = teacherId;
    }

    public List<Long> getTasksIds() {
        return mTasksIds;
    }

    public void setTasksIds(List<Long> tasksIds) {
        this.mTasksIds = tasksIds;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public SubjectType getType() {
        return mType;
    }

    public void setType(SubjectType type) {
        this.mType = type;
    }

    public Long getContent() {
        return mContentId;
    }

    public void setContent(Long content) {
        this.mContentId = content;
    }

    public List<Long> getTestsIds() {
        return mTestsIds;
    }

    public void setTestsIds(List<Long> testsIds) {
        this.mTestsIds = testsIds;
    }

    public void addTestId(Long testId) {
        mTestsIds.add(testId);
    }

    public void addTaskId(Long taskId) {
        mTasksIds.add(taskId);
    }

    @Override
    public String toString() {
        return getName();
    }
}
