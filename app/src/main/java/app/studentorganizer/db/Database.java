package app.studentorganizer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student.db";

    // Table Names
    private static final String SIMPLE_TASKS = "simple_tasks";
    private static final String MULTI_TASKS = "multi_tasks";
    private static final String SIMPLE_TO_MULTI = "simple_to_multi";
    private static final String TEACHERS = "teachers";
    private static final String SUBJECTS = "subjects";
    private static final String TEACHER_TO_SUBJECT = "teacher_to_subject";
    private static final String CONTENTS = "content";
    private static final String CONTENT_ITEMS = "content_items";
    private static final String TESTS = "tests";
    private static final String SUBJECT_TO_CONTENT = "subject_to_content";
    private static final String TEST_TO_CONTENT = "test_to_content";
    private static final String TASK_TO_CONTENT = "task_to_content";

    // SIMPLE_TASKS column names
    public static final String SIMPLE_TASK_ID = "_id";
    public static final String SIMPLE_TASK_NAME = "_name";
    public static final String SIMPLE_TASK_SUBJECT_ID = "_subject_id";
    public static final String SIMPLE_TASK_DEADLINE = "_deadline";
    public static final String SIMPLE_TASK_POINTS = "_points";

    // MULTI_TASKS column names
    public static final String MULTI_TASK_ID = "_id";
    public static final String MULTI_TASK_NAME = "_name";

    // SIMPLE_TO_MULTI column names
    public static final String SIMPLE_TO_MULTI_SIMPLE_TASK_ID = "_simple_task_id";
    public static final String SIMPLE_TO_MULTI_MULTI_TASK_ID = "_multi_task_id";

    // TEACHERS column names
    public static final String TEACHER_ID = "_id";
    public static final String TEACHER_NAME = "_name";
    public static final String TEACHER_CONTACTS = "_contacts";

    // SUBJECT column names
    public static final String SUBJECT_ID = "_id";
    public static final String SUBJECT_NAME = "_name";
    public static final String SUBJECT_TYPE = "_type";

    // TEACHER_TO_SUBJECT column names
    public static final String TEACHER_TO_SUBJECT_TEACHER_ID = "_teacher_id";
    public static final String TEACHER_TO_SUBJECT_SUBJECT_ID = "_subject_id";

    // CONTENTS column names
    public static final String CONTENT_ID = "_id";

    // CONTENT_ITEMS column names
    public static final String CONTENT_ITEM_ID = "_id";
    public static final String CONTENT_ITEM_CONTENT_ID = "_content_id";
    public static final String CONTENT_ITEM_TYPE = "_type";
    public static final String CONTENT_SOURCE = "_source";

    // TESTS column names
    public static final String TEST_ID = "_id";
    public static final String TEST_TYPE = "_type";
    public static final String TEST_SUBJECT_ID = "_subject_id";
    public static final String TEST_DATE = "_date";

    // SUBJECT_TO_CONTENT column names
    public static final String SUBJECT_TO_CONTENT_SUBJECT_ID = "_subject_id";
    public static final String SUBJECT_TO_CONTENT_CONTENT_ID = "_content_id";

    // TEST_TO_CONTENT column names
    public static final String TEST_TO_CONTENT_TEST_ID = "_test_id";
    public static final String TEST_TO_CONTENT_CONTENT_ID = "_content_id";

    // TASK_TO_CONTENT column names
    public static final String TASK_TO_CONTENT_TASK_ID = "_task_id";
    public static final String TASK_TO_CONTENT_CONTENT_ID = "_content_id";

    // Table Create Statements
    /*
    private static final String CREATE_TABLE_SIMPLE_TASK = "create table " + SIMPLE_TASKS + "("
            + GOAL_ID + " integer primary key autoincrement, "
            + GOAL_NAME + " text not null, "
            + GOAL_CATEGORY + " text not null, "
            + GOAL_START_DATE + " text not null, "
            + GOAL_END_DATE + " text not null, "
            + GOAL_CURRENT_CHECK_IN_PROGRESS + " integer, "
            + GOAL_TARGET_CHECK_IN_PROGRESS + " integer, "
            + GOAL_CURRENT_PROGRESS + " integer, "
            + GOAL_TARGET_PROGRESS + " integer, "
            + GOAL_CHECK_IN_START_DATE + " text not null, "
            + GOAL_CHECK_IN_END_DATE + " text not null, "
            + GOAL_SPLIT_TYPE + " text not null, "
            + GOAL_TARGET_TYPE + " text not null, "
            KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO
            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    */

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GOALS);
        onCreate(db);
    }
    */
}
