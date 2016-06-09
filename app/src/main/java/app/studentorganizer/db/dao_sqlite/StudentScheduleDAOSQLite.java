package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.db.dao.StudentScheduleDAO;
import app.studentorganizer.entities.StudentScheduleEntry;

public class StudentScheduleDAOSQLite
        extends GenericDAOSQLite<StudentScheduleEntry>
        implements StudentScheduleDAO {

    @Override
    public String[] getTableColumns() {
        return Database.STUDENT_SCHEDULE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.STUDENT_SCHEDULE;
    }

    @Override
    public StudentScheduleEntry parseEntity(Cursor cursor) {
        StudentScheduleEntry studentScheduleEntry = new StudentScheduleEntry();

        studentScheduleEntry.setId(cursor.getLong(0));
        studentScheduleEntry.setUnivScheduleEntryId(cursor.getLong(1));
        studentScheduleEntry.setSubjectId(cursor.getLong(2));
        studentScheduleEntry.setDay(cursor.getInt(3));
        studentScheduleEntry.setClassroom(cursor.getInt(4));

        return studentScheduleEntry;
    }

    @Override
    public ContentValues setValues(StudentScheduleEntry studentScheduleEntry) {
        ContentValues values = new ContentValues();

        values.put(
                Database.STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID,
                studentScheduleEntry.getUnivScheduleEntryId());
        values.put(
                Database.STUDENT_SCHEDULE_SUBJECT_ID,
                studentScheduleEntry.getSubjectId());
        values.put(
                Database.STUDENT_SCHEDULE_DAY,
                studentScheduleEntry.getDay());
        values.put(
                Database.STUDENT_SCHEDULE_CLASSROOM,
                studentScheduleEntry.getClassroom());

        return values;
    }

    @Override
    public void deleteBySubjectId(Long subjectId) {
        DatabaseManager.getDatabase().delete(
                getTableName(),
                Database.STUDENT_SCHEDULE_SUBJECT_ID + " = ?",
                new String[]{ subjectId.toString() });
    }
}
