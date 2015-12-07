package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.db.Database;
import app.studentorganizer.entities.StudentScheduleEntry;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class StudentScheduleDAOSQLite extends GenericDAOSQLite<StudentScheduleEntry> {
    private String[] STUDENT_SCHEDULE_COLUMNS = {
            Database.STUDENT_SCHEDULE_ID,
            Database.STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID,
            Database.STUDENT_SCHEDULE_SUBJECT_ID,
            Database.STUDENT_SCHEDULE_CLASSROOM
    };

    @Override
    public String[] getTableColumns() {
        return STUDENT_SCHEDULE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.STUDENT_SCHEDULE;
    }

    @Override
    public StudentScheduleEntry parseEntity(Cursor cursor) {
        StudentScheduleEntry studentScheduleEntry = new StudentScheduleEntry();

        studentScheduleEntry.setId(cursor.getInt(0));
        studentScheduleEntry.setUnivScheduleEntryId(cursor.getInt(1));
        studentScheduleEntry.setSubjectId(cursor.getInt(2));
        studentScheduleEntry.setClassroom(cursor.getInt(3));

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
                Database.STUDENT_SCHEDULE_CLASSROOM,
                studentScheduleEntry.getClassroom());

        return values;
    }
}
