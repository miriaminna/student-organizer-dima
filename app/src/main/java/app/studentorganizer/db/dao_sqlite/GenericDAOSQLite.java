package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.db.dao.GenericDAO;
import app.studentorganizer.entities.IDable;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public abstract class GenericDAOSQLite<Entity extends IDable> implements GenericDAO<Entity> {
    @Override
    public List<Entity> getAllEntities() {
        ArrayList<Entity> entities = new ArrayList<>();

        Cursor cursor = DatabaseManager.getDatabase().query(
                getTableName(),
                getTableColumns(),
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            entities.add(parseEntity(cursor));
            cursor.moveToNext();
        }

        return entities;
    }

    @Override
    public Entity getByID(Long id) {
        Cursor cursor = DatabaseManager.getDatabase().query(
                getTableName(),
                getTableColumns(),
                getTableIDColumn() + " = ? ",
                new String[] { id.toString() },
                null, null, null
        );
        return cursor.moveToFirst() ? parseEntity(cursor) : null;
    }

    @Override
    public boolean updateEntity(Entity entity) {
        return DatabaseManager.getDatabase().update(
                getTableName(),
                setValues(entity),
                getTableIDColumn() + " = ? ",
                new String[] { entity.getId().toString() }
        ) != 0;
    }

    @Override
    public boolean deleteEntity(Long id) {
        return DatabaseManager.getDatabase().delete(
                getTableName(),
                getTableIDColumn() + " = ? ",
                new String[] { id.toString() }
        ) != 0;
    }

    @Override
    public Long addEntity(Entity entity) {
        return DatabaseManager.getDatabase().insert(
                getTableName(),
                null,
                setValues(entity)
        );
    }

    public abstract String[] getTableColumns();
    public abstract String getTableName();
    public String getTableIDColumn() { return getTableColumns()[0]; }
    public abstract Entity parseEntity(Cursor cursor);
    public abstract ContentValues setValues(Entity entity);
}
