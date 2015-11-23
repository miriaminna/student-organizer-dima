package app.studentorganizer.entities;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Teacher {
    protected String mName;
    protected List<Subject> mSubjects;
    protected List<Pair<String, String>> mContacts;

    public Teacher() {
        mSubjects = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<Subject> getSubjects() {
        return mSubjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.mSubjects = subjects;
    }

    public List<Pair<String, String>> getContacts() {
        return mContacts;
    }

    public void setContacts(List<Pair<String, String>> contacts) {
        this.mContacts = contacts;
    }

    public void addSubject(Subject subject) {
        mSubjects.add(subject);
    }
}
