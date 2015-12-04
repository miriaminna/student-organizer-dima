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
    public static final String SIMPLE_TASK_MULTI_TASK_ID = "_multi_task_id";
    public static final String SIMPLE_TASK_DEADLINE = "_deadline";
    public static final String SIMPLE_TASK_POINTS = "_points";

    // MULTI_TASKS column names
    public static final String MULTI_TASK_ID = "_id";
    public static final String MULTI_TASK_NAME = "_name";
    public static final String MULTI_TASK_SUBJECT_ID = "_subject_id";

    // TEACHERS column names
    public static final String TEACHER_ID = "_id";
    public static final String TEACHER_NAME = "_name";
    public static final String TEACHER_CONTACTS = "_contacts";

    // SUBJECT column names
    public static final String SUBJECT_ID = "_id";
    public static final String SUBJECT_NAME = "_name";
    public static final String SUBJECT_TEACHER_ID = "_teacher_id";
    public static final String SUBJECT_TYPE = "_type";

    // CONTENTS column names
    public static final String CONTENT_ID = "_id";
    public static final String CONTENT_SUBJECT_ID = "_subject_id";
    public static final String CONTENT_TASK_ID = "_task_id";
    public static final String CONTENT_TEST_ID = "_test_id";

    // CONTENT_ITEMS column names
    public static final String CONTENT_ITEM_ID = "_id";
    public static final String CONTENT_ITEM_CONTENT_ID = "_content_id";
    public static final String CONTENT_ITEM_TYPE = "_type";
    public static final String CONTENT_ITEM_SOURCE = "_source";
    public static final String CONTENT_ITEM_TEXT = "_source";

    // TESTS column names
    public static final String TEST_ID = "_id";
    public static final String TEST_TYPE = "_type";
    public static final String TEST_SUBJECT_ID = "_subject_id";
    public static final String TEST_DATE = "_date";

    // Table Create Statements

    private static final String CREATE_TABLE_SIMPLE_TASK =
            "create table " + SIMPLE_TASKS + "("
            + SIMPLE_TASK_ID + " integer primary key autoincrement, "
            + SIMPLE_TASK_NAME + " text not null, "
            + SIMPLE_TASK_SUBJECT_ID + " integer, "
            + SIMPLE_TASK_MULTI_TASK_ID + " integer, "
            + SIMPLE_TASK_DEADLINE + " text not null, "
            + SIMPLE_TASK_POINTS + " integer)";

    private static final String CREATE_TABLE_MULTI_TASK =
            "create table " + MULTI_TASKS + "("
            + MULTI_TASK_ID + " integer primary key autoincrement, "
            + MULTI_TASK_NAME + " text not null, "
            + MULTI_TASK_SUBJECT_ID + " integer)";

    private static final String CREATE_TABLE_TEACHERS =
            "create table " + TEACHERS + "("
            + TEACHER_ID + " integer primary key autoincrement, "
            + TEACHER_NAME + " text not null, "
            + TEACHER_CONTACTS + " text)";

    private static final String CREATE_TABLE_SUBJECTS =
            "create table " + SUBJECTS + "("
            + SUBJECT_ID + " integer primary key autoincrement, "
            + SUBJECT_NAME + " text not null, "
            + SUBJECT_TEACHER_ID + " integer, "
            + SUBJECT_TYPE + " text not null)";

    private static final String CREATE_TABLE_CONTENTS =
            "create table " + CONTENTS + "("
            + CONTENT_ID + " integer primary key autoincrement"
            + CONTENT_SUBJECT_ID + " integer, "
            + CONTENT_TASK_ID + " integer, "
            + CONTENT_TEST_ID + " integer)";

    private static final String CREATE_TABLE_CONTENT_ITEMS =
            "create table " + CONTENT_ITEMS + "("
            + CONTENT_ITEM_ID + " integer primary key autoincrement"
            + CONTENT_ITEM_CONTENT_ID + " integer, "
            + CONTENT_ITEM_TYPE + " text not null, "
            + CONTENT_ITEM_SOURCE + " text, "
            + CONTENT_ITEM_TEXT + " text)";

    private static final String CREATE_TABLE_TESTS =
            "create table " + TESTS + "("
            + TEST_ID + " integer primary key autoincrement"
            + TEST_TYPE + " text not null, "
            + TEST_SUBJECT_ID + " integer, "
            + TEST_DATE + " text not null)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SIMPLE_TASK);
        db.execSQL(CREATE_TABLE_MULTI_TASK);
        db.execSQL(CREATE_TABLE_SUBJECTS);
        db.execSQL(CREATE_TABLE_TEACHERS);
        db.execSQL(CREATE_TABLE_TESTS);
        db.execSQL(CREATE_TABLE_CONTENTS);
        db.execSQL(CREATE_TABLE_CONTENT_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SIMPLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + MULTI_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TEACHERS);
        db.execSQL("DROP TABLE IF EXISTS " + TESTS);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENTS);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_ITEMS);

        onCreate(db);
    }
}
