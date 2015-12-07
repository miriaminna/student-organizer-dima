package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TeacherDAOSQLite extends GenericDAOSQLite<Teacher> {
    private static String[] TEACHER_TABLE_COLUMNS = {
            Database.TEACHER_ID,
            Database.TEACHER_NAME,
            Database.TEACHER_TYPE,
            Database.TEACHER_CONTACTS
    };

    @Override
    public String[] getTableColumns() {
        return TEACHER_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.TEACHERS;
    }

    @Override
    public Teacher parseEntity(Cursor cursor) {
        Teacher teacher = new Teacher();

        teacher.setId(cursor.getInt(0));
        teacher.setName(cursor.getString(1));
        teacher.setType(cursor.getString(2));
        teacher.setContacts(cursor.getString(3));

        return teacher;
    }

    @Override
    public ContentValues setValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(Database.TEACHER_NAME, teacher.getName());
        values.put(Database.TEACHER_TYPE, teacher.getType());
        values.put(Database.TEACHER_CONTACTS, teacher.getContactsAsString());

        return values;
    }
}
