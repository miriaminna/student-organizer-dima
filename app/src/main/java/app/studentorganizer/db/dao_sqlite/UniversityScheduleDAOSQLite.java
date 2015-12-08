package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.LocalTime;

import app.studentorganizer.db.Database;
import app.studentorganizer.entities.UnivScheduleEntry;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class UniversityScheduleDAOSQLite extends GenericDAOSQLite<UnivScheduleEntry> {

    @Override
    public String[] getTableColumns() {
        return Database.UNIVERSITY_SCHEDULE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.UNIVERSITY_SCHEDULE;
    }

    @Override
    public UnivScheduleEntry parseEntity(Cursor cursor) {
        UnivScheduleEntry univScheduleEntry = new UnivScheduleEntry();

        univScheduleEntry.setId(cursor.getLong(0));
        univScheduleEntry.setLessonNumber(cursor.getInt(1));
        univScheduleEntry.setDay(cursor.getInt(2));
        univScheduleEntry.setStart(new LocalTime(cursor.getString(3)));
        univScheduleEntry.setEnd(new LocalTime(cursor.getString(4)));

        return univScheduleEntry;
    }

    @Override
    public ContentValues setValues(UnivScheduleEntry univScheduleEntry) {
        ContentValues values = new ContentValues();

        values.put(
                Database.UNIVERSITY_SCHEDULE_LESSON_NUMBER,
                univScheduleEntry.getLessonNumber());
        values.put(
                Database.UNIVERSITY_SCHEDULE_DAY,
                univScheduleEntry.getDay());
        values.put(
                Database.UNIVERSITY_SCHEDULE_START,
                univScheduleEntry.getStart().toString());
        values.put(
                Database.UNIVERSITY_SCHEDULE_END,
                univScheduleEntry.getEnd().toString());

        return values;
    }
}
