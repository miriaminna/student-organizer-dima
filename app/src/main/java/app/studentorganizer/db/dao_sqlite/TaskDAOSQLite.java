package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalDate;


import app.studentorganizer.com.ColorTag;
import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TaskDAOSQLite extends GenericDAOSQLite<Task> {
    private static String[] SIMPLE_TASK_TABLE_COLUMNS = {
            Database.SIMPLE_TASK_ID,
            Database.SIMPLE_TASK_NAME,
            Database.SIMPLE_TASK_SUBJECT_ID,
            Database.SIMPLE_TASK_MULTI_TASK_ID,
            Database.SIMPLE_TASK_DEADLINE,
            Database.SIMPLE_TASK_POINTS,
            Database.SIMPLE_TASK_PROGRESS,
            Database.SIMPLE_TASK_TARGET
    };

    @Override
    public String[] getTableColumns() {
        return SIMPLE_TASK_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.SIMPLE_TASKS;
    }

    @Override
    public Task parseEntity(Cursor cursor) {
        Task task = new Task();

        task.setId(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setSubjectId(cursor.getInt(2));
        task.setMultiTaskId(cursor.getInt(3));
        task.setDeadline(new LocalDate(cursor.getString(4)));
        task.setPoints(cursor.getDouble(5));
        task.setProgress(cursor.getInt(6));
        task.setTarget(cursor.getInt(7));

        // TODO: maybe here have to find subject and parse object
        task.setSubject(new Subject());
        task.getSubject().setColorTag(ColorTag.BLUE);

        return task;
    }

    @Override
    public ContentValues setValues(Task task) {
        ContentValues values = new ContentValues();

        values.put(Database.SIMPLE_TASK_NAME, task.getName());
        values.put(Database.SIMPLE_TASK_SUBJECT_ID, task.getSubjectId());
        values.put(Database.SIMPLE_TASK_MULTI_TASK_ID,
                (task.getMultiTask() == null ? 0 : task.getMultiTaskId()));
        values.put(Database.SIMPLE_TASK_DEADLINE, task.getDeadline().toString());
        values.put(Database.SIMPLE_TASK_POINTS, task.getPoints());
        values.put(Database.SIMPLE_TASK_PROGRESS, task.getProgress());
        values.put(Database.SIMPLE_TASK_TARGET, task.getTarget());

        return values;
    }
}
