package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalDate;


import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.db.dao.TaskDAO;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

public class TaskDAOSQLite
        extends GenericDAOSQLite<Task>
        implements TaskDAO {
    @Override
    public String[] getTableColumns() {
        return Database.SIMPLE_TASK_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.SIMPLE_TASKS;
    }

    @Override
    public Task parseEntity(Cursor cursor) {
        Task task = new Task();

        task.setId(cursor.getLong(0));
        task.setName(cursor.getString(1));
        task.setSubjectId(cursor.getLong(2));
        task.setMultiTaskId(cursor.getLong(3));
        task.setDeadline(new LocalDate(cursor.getString(4)));
        task.setPoints(cursor.getDouble(5));
        task.setProgress(cursor.getInt(6));
        task.setTarget(cursor.getInt(7));

        return task;
    }

    @Override
    public ContentValues setValues(Task task) {
        ContentValues values = new ContentValues();

        values.put(Database.SIMPLE_TASK_NAME, task.getName());
        values.put(Database.SIMPLE_TASK_SUBJECT_ID, task.getSubjectId());
        values.put(Database.SIMPLE_TASK_MULTI_TASK_ID, task.getMultiTaskId());
        values.put(Database.SIMPLE_TASK_DEADLINE, task.getDeadline().toString());
        values.put(Database.SIMPLE_TASK_POINTS, task.getPoints());
        values.put(Database.SIMPLE_TASK_PROGRESS, task.getProgress());
        values.put(Database.SIMPLE_TASK_TARGET, task.getTarget());

        return values;
    }

    @Override
    public List<Task> getBySubjectId(Long subjectId) {
        Cursor cursor = DatabaseManager.getDatabase().query(
                getTableName(),
                getTableColumns(),
                Database.SIMPLE_TASK_SUBJECT_ID + " = ? ",
                new String[]{subjectId.toString()},
                null, null, null
        );
        return getListFromCursor(cursor);
    }

    @Override
    public void deleteBySubjectId(Long subjectId) {
        DatabaseManager.getDatabase().delete(
                getTableName(),
                Database.SIMPLE_TASK_SUBJECT_ID + " = ?",
                new String[]{ subjectId.toString() });
    }
}
