package app.studentorganizer.db.dao_test;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.db.dao.TaskDAO;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

public class TaskDAOTest
        extends BaseDAOTest<Task>
        implements TaskDAO {

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
            if (t.getId().equals(task.getId())) {
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

    @Override
    public boolean clear() {
        mTasks.clear();
        return true;
    }

    @Override
    public List<Task> getBySubjectId(Long subjectId) {
        List<Task> tasks = new ArrayList<>();
        for (Task t : mTasks) {
            if (t.getSubjectId().equals(subjectId)) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    @Override
    public void deleteBySubjectId(Long subjectId) {

    }
}
