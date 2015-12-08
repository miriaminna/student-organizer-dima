package app.studentorganizer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private static Database mDatabaseHelper;

    public static Database open(Context context) {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new Database(context);
//            DBSeed.seed();
        }
        return mDatabaseHelper;
    }

    public static SQLiteDatabase getDatabase() {
        return mDatabaseHelper.getWritableDatabase();
    }

    public static void close() {
        mDatabaseHelper.close();
    }
}
