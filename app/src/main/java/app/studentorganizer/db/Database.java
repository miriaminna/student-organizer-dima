package app.studentorganizer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Student.db";

    // Table Names
    public static final String SIMPLE_TASKS = "simple_tasks";
    public static final String MULTI_TASKS = "multi_tasks";
    public static final String TEACHERS = "teachers";
    public static final String SUBJECTS = "subjects";
    public static final String CONTENTS = "content";
    public static final String CONTENT_ITEMS = "content_items";
    public static final String TESTS = "tests";
    public static final String UNIVERSITY_SCHEDULE = "university_schedule";
    public static final String STUDENT_SCHEDULE = "student_schedule";

    // SIMPLE_TASKS column names
    public static final String SIMPLE_TASK_ID = "_id";
    public static final String SIMPLE_TASK_NAME = "_name";
    public static final String SIMPLE_TASK_SUBJECT_ID = "_subject_id";
    public static final String SIMPLE_TASK_MULTI_TASK_ID = "_multi_task_id";
    public static final String SIMPLE_TASK_DEADLINE = "_deadline";
    public static final String SIMPLE_TASK_POINTS = "_points";
    public static final String SIMPLE_TASK_PROGRESS = "_progress";
    public static final String SIMPLE_TASK_TARGET = "_target";

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
    public static final String SUBJECT_COLOR = "_color";

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
    public static final String CONTENT_ITEM_TEXT = "_text";

    // TESTS column names
    public static final String TEST_ID = "_id";
    public static final String TEST_TYPE = "_type";
    public static final String TEST_SUBJECT_ID = "_subject_id";
    public static final String TEST_DATE = "_date";

    // UNIVERSITY_SCHEDULE column names
    public static final String UNIVERSITY_SCHEDULE_ID = "_id";
    public static final String UNIVERSITY_SCHEDULE_LESSON_NUMBER = "_lesson_number";
    public static final String UNIVERSITY_SCHEDULE_DAY = "_day";
    public static final String UNIVERSITY_SCHEDULE_START = "_start";
    public static final String UNIVERSITY_SCHEDULE_END = "_end";

    // STUDENT_SCHEDULE column names
    public static final String STUDENT_SCHEDULE_ID = "_id";
    public static final String STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID = "_university_schedule_id";
    public static final String STUDENT_SCHEDULE_SUBJECT_ID = "_subject_id";
    public static final String STUDENT_SCHEDULE_CLASSROOM = "_classroom";

    // Table Create Statements

    private static final String CREATE_TABLE_SIMPLE_TASK =
            "create table " + SIMPLE_TASKS + "("
            + SIMPLE_TASK_ID + " integer primary key autoincrement, "
            + SIMPLE_TASK_NAME + " text not null, "
            + SIMPLE_TASK_SUBJECT_ID + " integer, "
            + SIMPLE_TASK_MULTI_TASK_ID + " integer, "
            + SIMPLE_TASK_DEADLINE + " text not null, "
            + SIMPLE_TASK_POINTS + " integer, "
            + SIMPLE_TASK_PROGRESS + " integer, "
            + SIMPLE_TASK_TARGET + " integer)";

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
            + SUBJECT_TYPE + " text not null, "
            + SUBJECT_COLOR + " text not null)";

    private static final String CREATE_TABLE_CONTENTS =
            "create table " + CONTENTS + "("
            + CONTENT_ID + " integer primary key autoincrement, "
            + CONTENT_SUBJECT_ID + " integer, "
            + CONTENT_TASK_ID + " integer, "
            + CONTENT_TEST_ID + " integer)";

    private static final String CREATE_TABLE_CONTENT_ITEMS =
            "create table " + CONTENT_ITEMS + "("
            + CONTENT_ITEM_ID + " integer primary key autoincrement, "
            + CONTENT_ITEM_CONTENT_ID + " integer, "
            + CONTENT_ITEM_TYPE + " text not null, "
            + CONTENT_ITEM_SOURCE + " text, "
            + CONTENT_ITEM_TEXT + " text)";

    private static final String CREATE_TABLE_TESTS =
            "create table " + TESTS + "("
            + TEST_ID + " integer primary key autoincrement, "
            + TEST_TYPE + " text not null, "
            + TEST_SUBJECT_ID + " integer, "
            + TEST_DATE + " text not null)";

    private static final String CREATE_TABLE_UNIVERSITY_SCHEDULE =
            "create table " + UNIVERSITY_SCHEDULE + "("
                    + UNIVERSITY_SCHEDULE_ID + " integer primary key autoincrement, "
                    + UNIVERSITY_SCHEDULE_LESSON_NUMBER + " integer, "
                    + UNIVERSITY_SCHEDULE_DAY + " integer, "
                    + UNIVERSITY_SCHEDULE_START + " text not null, "
                    + UNIVERSITY_SCHEDULE_END + " text not null)";

    private static final String CREATE_TABLE_STUDENT_SCHEDULE =
            "create table " + STUDENT_SCHEDULE + "("
                    + STUDENT_SCHEDULE_ID + " integer primary key autoincrement, "
                    + STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID + " integer, "
                    + STUDENT_SCHEDULE_SUBJECT_ID + " integer, "
                    + STUDENT_SCHEDULE_CLASSROOM + " integer)";

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
        db.execSQL(CREATE_TABLE_UNIVERSITY_SCHEDULE);
        db.execSQL(CREATE_TABLE_STUDENT_SCHEDULE);
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
        db.execSQL("DROP TABLE IF EXISTS " + UNIVERSITY_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_SCHEDULE);

        onCreate(db);
    }
}
