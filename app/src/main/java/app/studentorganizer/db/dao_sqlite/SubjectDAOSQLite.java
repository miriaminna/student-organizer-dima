package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.entities.Subject;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class SubjectDAOSQLite extends GenericDAOSQLite<Subject> {

    @Override
    public String[] getTableColumns() {
        return Database.SUBJECT_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.SUBJECTS;
    }

    @Override
    public Subject parseEntity(Cursor cursor) {
        Subject subject = new Subject();

        subject.setId(cursor.getLong(0));
        subject.setName(cursor.getString(1));
        subject.setTeacherId(cursor.getLong(2));
        subject.setType(SubjectType.valueOf(cursor.getString(3)));
        subject.setColorTag(ColorTag.valueOf(cursor.getString(4)));

        return subject;
    }

    @Override
    public ContentValues setValues(Subject subject) {
        ContentValues values = new ContentValues();

        values.put(Database.SUBJECT_NAME, subject.getName());
        values.put(Database.SUBJECT_TEACHER_ID, subject.getTeacherId());
        values.put(Database.SUBJECT_TYPE, subject.getType().name());
        values.put(Database.SUBJECT_COLOR, subject.getColorTag().name());

        return values;
    }
}
