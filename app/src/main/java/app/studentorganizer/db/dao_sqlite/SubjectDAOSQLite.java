package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.db.dao.SubjectDAO;
import app.studentorganizer.entities.Subject;

public class SubjectDAOSQLite
        extends GenericDAOSQLite<Subject>
        implements SubjectDAO {

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
        subject.setStartDate(new LocalDate(cursor.getString(5)));
        subject.setEndDate(new LocalDate(cursor.getString(6)));

        return subject;
    }

    @Override
    public ContentValues setValues(Subject subject) {
        ContentValues values = new ContentValues();

        values.put(Database.SUBJECT_NAME, subject.getName());
        values.put(Database.SUBJECT_TEACHER_ID, subject.getTeacherId());
        values.put(Database.SUBJECT_TYPE, subject.getType().name());
        values.put(Database.SUBJECT_COLOR, subject.getColorTag().name());
        values.put(Database.SUBJECT_START_DATE, subject.getStartDate().toString());
        values.put(Database.SUBJECT_END_DATE, subject.getEndDate().toString());

        return values;
    }

    @Override
    public List<Subject> getByTeacherId(Long teacherId) {
        Cursor cursor = DatabaseManager.getDatabase().query(
                getTableName(),
                getTableColumns(),
                Database.SUBJECT_TEACHER_ID + " = ? ",
                new String[] { teacherId.toString() },
                null, null, null);

        return getListFromCursor(cursor);
    }

    @Override
    public void deleteByTeacherId(Long teacherId) {
        DatabaseManager.getDatabase().delete(
                getTableName(),
                Database.SUBJECT_TEACHER_ID + " = ?",
                new String[]{ teacherId.toString() }
        );
    }
}
