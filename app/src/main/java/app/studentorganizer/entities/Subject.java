package app.studentorganizer.entities;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.SubjectType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Subject {
    protected List<Task> mTasks;
    protected String mName;
    protected Teacher mTeacher;
    protected SubjectType mType;
    protected Content mContent;
    protected List<Test> mTests;

    public Subject() {
        mTasks = new ArrayList<>();
        mTests = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        this.mTasks = tasks;
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

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        this.mContent = content;
    }

    public List<Test> getTests() {
        return mTests;
    }

    public void setTests(List<Test> tests) {
        this.mTests = tests;
    }

    public Teacher getTeacher() {
        return mTeacher;
    }

    public void setTeacher(Teacher teacher) {
        this.mTeacher = teacher;
    }

    public void addTest(Test test) {
        mTests.add(test);
    }

    public void addTask(Task task) {
        mTasks.add(task);
    }
}
