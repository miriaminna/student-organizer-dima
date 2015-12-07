package app.studentorganizer.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private static Database mDatabaseHelper;
    private static SQLiteDatabase mDatabase;

    public static Database getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public static SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public DatabaseManager(Context context) {
        mDatabaseHelper = new Database(context);
    }

    public void open() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        mDatabaseHelper.close();
    }
}
