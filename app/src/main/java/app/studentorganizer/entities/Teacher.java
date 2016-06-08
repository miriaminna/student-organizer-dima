package app.studentorganizer.entities;

import app.studentorganizer.util.Pair;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.TeacherType;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Teacher extends IDable {
    protected String mName;
    protected TeacherType mType;
    protected List<Long> mSubjectsIds;
    protected List<Pair<String, String>> mContacts;

    public Teacher() {
        mSubjectsIds = new ArrayList<>();
        mContacts = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public TeacherType getType() {
        return mType;
    }

    public void setType(TeacherType type) {
        mType = type;
    }

    public List<Long> getSubjectsIds() {
        return mSubjectsIds;
    }

    public void setSubjectsIds(List<Long> subjectsIds) {
        this.mSubjectsIds = subjectsIds;
    }

    public List<Pair<String, String>> getContacts() {
        return mContacts;
    }

    public void setContacts(String s) {
        mContacts = new ArrayList<>();
        for (String c : s.split("\n")) {
            String[] cur = c.split(":");
            if (cur.length == 2) {
                mContacts.add(new Pair<>(cur[0], cur[1]));
            }
        }
    }

    public void setContacts(List<Pair<String, String>> contacts) {
        this.mContacts = contacts;
    }


    public void addSubjectId(Long subjectId) {
        mSubjectsIds.add(subjectId);
    }

    public String getContactsAsString() {
        StringBuilder string = new StringBuilder();
        for (Pair<String, String> pair : mContacts) {
            string.append(pair.first);
            string.append(':');
            string.append(pair.second);
            string.append('\n');
        }
        if (string.length() > 0) {
            string.deleteCharAt(string.length() - 1);
        }
        return string.toString();
    }

    public void addContact(Pair<String, String> contact) {
        mContacts.add(contact);
    }

    public void removeContact(Pair<String, String> contact) {
        List<Pair<String, String>> toRemove = new ArrayList<>();
        for (Pair<String, String> c: mContacts) {
            if ((contact.first.equals("") || contact.first.equals(c.first)) &&
                    (contact.second.equals("") || contact.second.equals(c.second))) {
                toRemove.add(c);
            }
        }
        for (Pair<String, String> c: toRemove) {
            mContacts.remove(c);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
