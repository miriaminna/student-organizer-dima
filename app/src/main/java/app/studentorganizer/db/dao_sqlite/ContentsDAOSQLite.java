package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Content;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class ContentsDAOSQLite extends GenericDAOSQLite<Content> {
    private static String[] CONTENTS_TABLE_COLUMNS = {
            Database.CONTENT_ID,
            Database.CONTENT_SUBJECT_ID,
            Database.CONTENT_TASK_ID,
            Database.CONTENT_TEST_ID
    };

    @Override
    public String[] getTableColumns() {
        return CONTENTS_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.CONTENTS;
    }

    @Override
    public Content parseEntity(Cursor cursor) {
        Content content = new Content();

        content.setId(cursor.getInt(0));
        content.setSubjectId(cursor.getInt(1));
        content.setTaskId(cursor.getInt(2));
        content.setTestId(cursor.getInt(3));

        return content;
    }

    @Override
    public ContentValues setValues(Content content) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_SUBJECT_ID,
                (content.getSubject() == null ? 0 : content.getSubject().getId()));
        values.put(Database.CONTENT_TASK_ID,
                (content.getTask() == null ? 0 : content.getTask().getId()));
        values.put(Database.CONTENT_TEST_ID,
                (content.getTest() == null ? 0 : content.getTest().getId()));

        return values;
    }
}
