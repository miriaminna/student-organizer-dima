package app.studentorganizer.db.dao_sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.db.Database;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.db.dao.GenericDAO;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public abstract class GenericDAOSQLite<Entity> implements GenericDAO<Entity> {
    @Override
    public List getAllEntities() {
        ArrayList<Entity> entities = new ArrayList<>();

        Cursor cursor = DatabaseManager.getDatabase().query(
                Database.TEACHERS,
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
    public Entity getByID(long id) {
        // TODO: add get
        return null;
    }

    @Override
    public boolean updateEntity(Entity entity) {
        // TODO: Make normal update
//        deleteEntity(entity.getId());
        addEntity(entity);
        return true;
    }

    @Override
    public boolean deleteEntity(long id) {
        // TODO: Concatenation is wrong
        DatabaseManager.getDatabase().delete(
                getTableName(),
                getTableIDColumn() + " = " + id, null);
        return true;
    }

    @Override
    public long addEntity(Entity entity) {
        return DatabaseManager.getDatabase().insert(getTableName(), null, setValues(entity));
    }

    public abstract String[] getTableColumns();
    public abstract String getTableName();
    public String getTableIDColumn() { return getTableColumns()[0]; }
    public abstract Entity parseEntity(Cursor cursor);
    public abstract ContentValues setValues(Entity entity);
}
