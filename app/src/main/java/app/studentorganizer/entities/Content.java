package app.studentorganizer.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Content {

    protected Integer mId;
    protected List<File> mFiles;
    protected List<String> mLiterature;

    protected Subject mSubject;
    protected Integer mSubjectId;
    protected Task mTask;
    protected Integer mTaskId;
    protected Test mTest;
    protected Integer mTestId;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getTestId() {
        return mTestId;
    }

    public void setTestId(Integer testId) {
        mTestId = testId;
    }

    public Integer getTaskId() {
        return mTaskId;
    }

    public void setTaskId(Integer taskId) {
        mTaskId = taskId;
    }

    public Integer getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(Integer subjectId) {
        mSubjectId = subjectId;
    }

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject subject) {
        mSubject = subject;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public Test getTest() {
        return mTest;
    }

    public void setTest(Test test) {
        mTest = test;
    }

    public Content() {
        mFiles = new ArrayList<>();
        mLiterature = new ArrayList<>();
    }

    public List<File> getFiles() {
        return mFiles;
    }

    public void setFiles(List<File> mFiles) {
        this.mFiles = mFiles;
    }

    public List<String> getLiterature() {
        return mLiterature;
    }

    public void setLiterature(List<String> mLiterature) {
        this.mLiterature = mLiterature;
    }

    public void addFile(File file) {
        mFiles.add(file);
    }

    public void addLiterature(String lit) {
        mLiterature.add(lit);
    }
}
