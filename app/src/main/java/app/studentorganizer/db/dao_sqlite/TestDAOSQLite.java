package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TestType;
import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Test;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TestDAOSQLite extends GenericDAOSQLite<Test> {
    private static String[] TEST_TABLE_COLUMNS = {
            Database.TEST_ID,
            Database.TEST_TYPE,
            Database.TEST_SUBJECT_ID,
            Database.TEST_DATE
    };

    @Override
    public String[] getTableColumns() {
        return TEST_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.TESTS;
    }

    @Override
    public Test parseEntity(Cursor cursor) {
        Test test = new Test();

        test.setId(cursor.getInt(0));
        test.setTestType(TestType.valueOf(cursor.getString(1)));
        test.setSubjectId(cursor.getInt(2));
        test.setDate(new LocalDate(cursor.getString(3)));

        return test;
    }

    @Override
    public ContentValues setValues(Test test) {
        ContentValues values = new ContentValues();

        values.put(Database.TEST_TYPE, test.getTestType().name());
        values.put(Database.TEST_SUBJECT_ID, test.getSubject().getId());
        values.put(Database.TEST_DATE, test.getDate().toString());

        return values;
    }
}
