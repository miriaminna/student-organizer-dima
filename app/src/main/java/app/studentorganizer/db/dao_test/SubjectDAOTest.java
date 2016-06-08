package app.studentorganizer.db.dao_test;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.dao.SubjectDAO;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

/**
 * Created by henko on 07.12.15.
 */
public class SubjectDAOTest
        extends BaseDAOTest<Subject>
        implements SubjectDAO {

    private static final List<Subject> mSubjects = new ArrayList<>();

    @Override
    public List<Subject> getAllEntities() {
        return mSubjects;
    }

    @Override
    public Subject getByID(Long id) {
        for (Subject s : mSubjects) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean updateEntity(Subject subject) {
        for (Subject s : mSubjects) {
            if (s.getId().equals(subject.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Long id) {
        for (Subject s : mSubjects) {
            if (s.getId().equals(id)) {
                mSubjects.remove(s);
                return true;
            }
        }
        return false;
    }

    @Override
    public Long addEntity(Subject subject) {
        subject.setId(getNewId());
        mSubjects.add(subject);
        return subject.getId();
    }

    @Override
    public boolean clear() {
        mSubjects.clear();
        return true;
    }

    @Override
    public List<Subject> getByTeacherId(Long teacherId) {
        List<Subject> subjects = new ArrayList<>();

        for (Subject s: mSubjects) {
            if (s.getTeacherId().equals(teacherId)) {
                subjects.add(s);
            }
        }
        return subjects;
    }
}
