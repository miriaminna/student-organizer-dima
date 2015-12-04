package app.studentorganizer.entities;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Teacher {
    protected Integer mId;
    protected String mName;
    protected List<Subject> mSubjects;
    protected List<Pair<String, String>> mContacts;

    public Teacher() {
        mSubjects = new ArrayList<>();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
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

    public void setContacts(String s) {
        // TODO for vitalij
    }

    public void setContacts(List<Pair<String, String>> contacts) {
        this.mContacts = contacts;
    }


    public void addSubject(Subject subject) {
        mSubjects.add(subject);
    }

    public String getContactsAsString() {
        StringBuilder string = new StringBuilder();
        for (Pair<String,String> pair : mContacts) {
            string.append(pair.first);
            string.append(':');
            string.append(pair.second);
            string.append(' ');
        }
        return string.toString();
    }
}