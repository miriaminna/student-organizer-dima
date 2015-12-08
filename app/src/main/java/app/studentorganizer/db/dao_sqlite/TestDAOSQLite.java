package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TestType;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.dao.TestDAO;
import app.studentorganizer.entities.Test;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TestDAOSQLite
        extends GenericDAOSQLite<Test>
        implements TestDAO {

    @Override
    public String[] getTableColumns() {
        return Database.TEST_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.TESTS;
    }

    @Override
    public Test parseEntity(Cursor cursor) {
        Test test = new Test();

        test.setId(cursor.getLong(0));
        test.setTestType(TestType.valueOf(cursor.getString(1)));
        test.setSubjectId(cursor.getLong(2));
        test.setDate(new LocalDate(cursor.getString(3)));

        return test;
    }

    @Override
    public ContentValues setValues(Test test) {
        ContentValues values = new ContentValues();

        values.put(Database.TEST_TYPE, test.getTestType().name());
        values.put(Database.TEST_SUBJECT_ID, test.getSubjectId());
        values.put(Database.TEST_DATE, test.getDate().toString());

        return values;
    }
}
