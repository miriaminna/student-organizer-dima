package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.com.ContentParent;
import app.studentorganizer.db.Database;
import app.studentorganizer.entities.Content;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class ContentsDAOSQLite extends GenericDAOSQLite<Content> {

    @Override
    public String[] getTableColumns() {
        return Database.CONTENTS_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.CONTENTS;
    }

    @Override
    public Content parseEntity(Cursor cursor) {
        Content content = new Content();

        content.setId(cursor.getLong(0));
        content.setParentType(ContentParent.valueOf(cursor.getString(1)));
        content.setParentId(cursor.getLong(2));

        return content;
    }

    @Override
    public ContentValues setValues(Content content) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_PARENT_TYPE, content.getParentType().toString());
        values.put(Database.CONTENT_PARENT_ID, content.getParentId());

        return values;
    }
}
