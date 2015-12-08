package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.com.TeacherType;
import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TeacherDAOSQLite extends GenericDAOSQLite<Teacher> {

    @Override
    public String[] getTableColumns() {
        return Database.TEACHER_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.TEACHERS;
    }

    @Override
    public Teacher parseEntity(Cursor cursor) {
        Teacher teacher = new Teacher();

        teacher.setId(cursor.getLong(0));
        teacher.setName(cursor.getString(1));
        teacher.setType(TeacherType.valueOf(cursor.getString(2)));
        teacher.setContacts(cursor.getString(3));

        return teacher;
    }

    @Override
    public ContentValues setValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(Database.TEACHER_NAME, teacher.getName());
        values.put(Database.TEACHER_TYPE, teacher.getType().toString());
        values.put(Database.TEACHER_CONTACTS, teacher.getContactsAsString());

        return values;
    }
}
