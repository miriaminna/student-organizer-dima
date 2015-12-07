package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.com.ContentType;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.dao.GenericDAO;
import app.studentorganizer.entities.ContentItem;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class ContentItemDAOSQLite extends GenericDAOSQLite<ContentItem> {
    private static String[] CONTENT_ITEM_TABLE_COLUMNS = {
            Database.CONTENT_ITEM_ID,
            Database.CONTENT_ITEM_CONTENT_ID,
            Database.CONTENT_ITEM_TYPE,
            Database.CONTENT_ITEM_SOURCE,
            Database.CONTENT_ITEM_TEXT
    };

    @Override
    public String[] getTableColumns() {
        return CONTENT_ITEM_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.CONTENT_ITEMS;
    }

    @Override
    public ContentItem parseEntity(Cursor cursor) {
        ContentItem contentItem = new ContentItem();

        contentItem.setId(cursor.getInt(0));
        contentItem.setContentId(cursor.getInt(1));
        contentItem.setType(ContentType.valueOf(cursor.getString(2)));
        contentItem.setSource(cursor.getString(3));
        contentItem.setText(cursor.getString(4));

        return contentItem;
    }

    @Override
    public ContentValues setValues(ContentItem contentItem) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_ITEM_CONTENT_ID, contentItem.getContent().getId());
        values.put(Database.CONTENT_ITEM_TYPE, contentItem.getType().name());
        values.put(Database.CONTENT_ITEM_SOURCE, contentItem.getSource());
        values.put(Database.CONTENT_ITEM_TEXT, contentItem.getText());

        return values;
    }
}
