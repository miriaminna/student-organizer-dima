package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.db.Database;
import app.studentorganizer.db.dao.MultiTaskDAO;
import app.studentorganizer.entities.MultiTask;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class MultiTaskDAOSQLite
        extends GenericDAOSQLite<MultiTask>
        implements MultiTaskDAO {

    @Override
    public String[] getTableColumns() {
        return Database.MULTI_TASK_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.MULTI_TASKS;
    }

    @Override
    public MultiTask parseEntity(Cursor cursor) {
        MultiTask task = new MultiTask();

        task.setId(cursor.getLong(0));
        task.setName(cursor.getString(1));
        task.setSubjectId(cursor.getLong(2));

        return task;
    }

    @Override
    public ContentValues setValues(MultiTask multiTask) {
        ContentValues values = new ContentValues();

        values.put(Database.MULTI_TASK_NAME, multiTask.getName());
        values.put(Database.MULTI_TASK_SUBJECT_ID, multiTask.getSubjectId());

        return values;
    }
}
