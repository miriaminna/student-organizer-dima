package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import app.studentorganizer.com.ContentType;
import app.studentorganizer.db.Database;
import app.studentorganizer.db.dao.ContentItemDAO;
import app.studentorganizer.db.dao.GenericDAO;
import app.studentorganizer.entities.ContentItem;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class ContentItemDAOSQLite
        extends GenericDAOSQLite<ContentItem>
        implements ContentItemDAO {

    @Override
    public String[] getTableColumns() {
        return Database.CONTENT_ITEM_TABLE_COLUMNS;
    }

    @Override
    public String getTableName() {
        return Database.CONTENT_ITEMS;
    }

    @Override
    public ContentItem parseEntity(Cursor cursor) {
        ContentItem contentItem = new ContentItem();

        contentItem.setId(cursor.getLong(0));
        contentItem.setContentId(cursor.getLong(1));
        contentItem.setType(ContentType.valueOf(cursor.getString(2)));
        contentItem.setSource(cursor.getString(3));
        contentItem.setText(cursor.getString(4));

        return contentItem;
    }

    @Override
    public ContentValues setValues(ContentItem contentItem) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_ITEM_CONTENT_ID, contentItem.getContentId());
        values.put(Database.CONTENT_ITEM_TYPE, contentItem.getType().name());
        values.put(Database.CONTENT_ITEM_SOURCE, contentItem.getSource());
        values.put(Database.CONTENT_ITEM_TEXT, contentItem.getText());

        return values;
    }
}
