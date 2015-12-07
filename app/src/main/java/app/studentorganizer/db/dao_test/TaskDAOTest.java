package app.studentorganizer.db.dao_test;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TaskDAOTest extends BaseDAOTest<Task> {
    private static final List<Task> mTasks = new ArrayList<>();

    static {
        Task task1 = new Task();
        task1.setId((int) getNewId());
        task1.setName("Programming - Labs");
        task1.setDeadline(new LocalDate().plusDays(1));
        task1.setPoints(30.0);
        task1.setProgress(10);
        task1.setTarget(40);
        task1.setSubject(new Subject());
        task1.getSubject().setColorTag(ColorTag.GREEN);

        Task task2 = new Task();
        task2.setId((int) getNewId());
        task2.setName("Algebra - Smile :)");
        task2.setDeadline(new LocalDate().plusDays(1));
        task2.setPoints(30.0);
        task2.setProgress(75);
        task2.setTarget(100);
        task2.setSubject(new Subject());
        task2.getSubject().setColorTag(ColorTag.BLUE);

        mTasks.add(task1);
        mTasks.add(task2);
    }

    @Override
    public List<Task> getAllEntities() {
        return mTasks;
    }

    @Override
    public Task getByID(long id) {
        for (Task t: mTasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean updateEntity(Task task) {
        for (Task t: mTasks) {
            if (t.getId() == id) {
                t.setProgress(task.getProgress());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEntity(long id) {
        for (Task t: mTasks) {
            if (t.getId() == id) {
                mTasks.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public long addEntity(Task task) {
        task.setId((int)getNewId());
        mTasks.add(task);
        return task.getId();
    }
}
