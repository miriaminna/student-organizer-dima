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

    @Override
    public List<Task> getAllEntities() {
        return mTasks;
    }

    @Override
    public Task getByID(Long id) {
        for (Task t: mTasks) {
            if (t.getId().equals(id)) {
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
    public boolean deleteEntity(Long id) {
        for (Task t: mTasks) {
            if (t.getId().equals(id)) {
                mTasks.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public Long addEntity(Task task) {
        task.setId(getNewId());
        mTasks.add(task);
        return task.getId();
    }
}
