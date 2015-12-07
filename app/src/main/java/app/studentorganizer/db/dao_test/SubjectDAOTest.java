package app.studentorganizer.db.dao_test;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

/**
 * Created by henko on 07.12.15.
 */
public class SubjectDAOTest extends BaseDAOTest<Subject> {
    private static final List<Subject> mSubjects = new ArrayList<>();

    static {
        Subject subject = new Subject();
        subject.setName("Object-oriented programming");
        subject.setType(SubjectType.EXAM);
        subject.setColorTag(ColorTag.BLUE);

        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("Programming - Labs");
        task1.setDeadline(new LocalDate().plusDays(1));
        task1.setPoints(30.0);
        task1.setProgress(10);
        task1.setTarget(40);
        task1.setSubject(new Subject());
        task1.getSubject().setColorTag(ColorTag.GREEN);
        tasks.add(task1);
        subject.setTasks(tasks);

        subject.setId((int) getNewId());

        mSubjects.add(subject);

        subject = new Subject();
        subject.setName("Mathematical Analysis");
        subject.setType(SubjectType.CREDIT);
        subject.setColorTag(ColorTag.GREEN);
        subject.setTasks(DBFactory.getFactory().getTaskDAO().getAllEntities());
        subject.setId((int) getNewId());
        mSubjects.add(subject);

        subject = new Subject();
        subject.setName("Probability Theory");
        subject.setType(SubjectType.EXAM);
        subject.setColorTag(ColorTag.ORANGE);
        subject.setId((int) getNewId());
        mSubjects.add(subject);
    }

    @Override
    public List<Subject> getAllEntities() {
        return mSubjects;
    }

    @Override
    public Subject getByID(long id) {
        for (Subject s : mSubjects) {
            if (s.getId() == id) {
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
    public boolean deleteEntity(long id) {
        for (Subject s : mSubjects) {
            if (s.getId() == id) {
                mSubjects.remove(s);
                return true;
            }
        }
        return false;
    }

    @Override
    public long addEntity(Subject subject) {
        subject.setId((int) getNewId());
        mSubjects.add(subject);
        return subject.getId();
    }
}
