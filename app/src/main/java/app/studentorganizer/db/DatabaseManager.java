package app.studentorganizer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.ContentType;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.com.TestType;
import app.studentorganizer.entities.Content;
import app.studentorganizer.entities.ContentItem;
import app.studentorganizer.entities.MultiTask;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.Test;
import app.studentorganizer.entities.UnivScheduleEntry;

public class DatabaseManager {
    private Database mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    private String[] SIMPLE_TASK_TABLE_COLUMNS = {
            Database.SIMPLE_TASK_ID,
            Database.SIMPLE_TASK_NAME,
            Database.SIMPLE_TASK_SUBJECT_ID,
            Database.SIMPLE_TASK_MULTI_TASK_ID,
            Database.SIMPLE_TASK_DEADLINE,
            Database.SIMPLE_TASK_POINTS
    };

    private String[] MULTI_TASK_TABLE_COLUMNS = {
            Database.MULTI_TASK_ID,
            Database.MULTI_TASK_NAME,
            Database.MULTI_TASK_SUBJECT_ID
    };

    private String[] TEACHER_TABLE_COLUMNS = {
            Database.TEACHER_ID,
            Database.TEACHER_NAME,
            Database.TEACHER_CONTACTS
    };

    private String[] SUBJECT_TABLE_COLUMNS = {
            Database.SUBJECT_ID,
            Database.SUBJECT_NAME,
            Database.SUBJECT_TEACHER_ID,
            Database.SUBJECT_TYPE
    };

    private String[] CONTENTS_TABLE_COLUMNS = {
            Database.CONTENT_ID,
            Database.CONTENT_SUBJECT_ID,
            Database.CONTENT_TASK_ID,
            Database.CONTENT_TEST_ID
    };

    private String[] CONTENT_ITEM_TABLE_COLUMNS = {
            Database.CONTENT_ITEM_ID,
            Database.CONTENT_ITEM_CONTENT_ID,
            Database.CONTENT_ITEM_TYPE,
            Database.CONTENT_ITEM_SOURCE,
            Database.CONTENT_ITEM_TEXT
    };

    private String[] TEST_TABLE_COLUMNS = {
            Database.TEST_ID,
            Database.TEST_TYPE,
            Database.TEST_SUBJECT_ID,
            Database.TEST_DATE
    };

    private String[] UNIVERSITY_SCHEDULE_COLUMNS = {
            Database.UNIVERSITY_SCHEDULE_ID,
            Database.UNIVERSITY_SCHEDULE_LESSON_NUMBER,
            Database.UNIVERSITY_SCHEDULE_DAY,
            Database.UNIVERSITY_SCHEDULE_START,
            Database.UNIVERSITY_SCHEDULE_END
    };

    private String[] STUDENT_SCHEDULE_COLUMNS = {
            Database.STUDENT_SCHEDULE_ID,
            Database.STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID,
            Database.STUDENT_SCHEDULE_SUBJECT_ID,
            Database.STUDENT_SCHEDULE_CLASSROOM
    };

    public DatabaseManager(Context context) {
        mDatabaseHelper = new Database(context);
    }

    public void open() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        mDatabaseHelper.close();
    }

    public ArrayList<Task> getAllTasksTest() {
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("Programming - Labs");
        task1.setDeadline(new LocalDate().plusDays(1));
        task1.setPoints(30.0);
        task1.setProgress(10);
        task1.setTarget(40);
        task1.setSubject(new Subject());
        task1.getSubject().setColorTag(ColorTag.GREEN);

        Task task2 = new Task();
        task2.setId(1);
        task2.setName("Algebra - Smile :)");
        task2.setDeadline(new LocalDate().plusDays(1));
        task2.setPoints(30.0);
        task2.setProgress(75);
        task2.setTarget(10);
        task2.setSubject(new Subject());
        task2.getSubject().setColorTag(ColorTag.BLUE);

        ArrayList<Task> test = new ArrayList<>();

        test.add(task1);
        test.add(task2);

        return test;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.SIMPLE_TASKS,
                SIMPLE_TASK_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tasks.add(parseSimpleTask(cursor));
            cursor.moveToNext();
        }

        return tasks;
    }

    public ArrayList<MultiTask> getAllMultiTasks() {
        ArrayList<MultiTask> tasks = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.MULTI_TASKS,
                MULTI_TASK_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tasks.add(parseMultiTask(cursor));
            cursor.moveToNext();
        }

        return tasks;
    }

    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.TEACHERS,
                TEACHER_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            teachers.add(parseTeacher(cursor));
            cursor.moveToNext();
        }

        return teachers;
    }

    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> teachers = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.SUBJECTS,
                SUBJECT_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            teachers.add(parseSubject(cursor));
            cursor.moveToNext();
        }

        return teachers;
    }

    public ArrayList<Content> getAllContents() {
        ArrayList<Content> contents = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.CONTENTS,
                CONTENTS_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            contents.add(parseContent(cursor));
            cursor.moveToNext();
        }

        return contents;
    }

    public ArrayList<ContentItem> getAllContentItems() {
        ArrayList<ContentItem> contentItems = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.CONTENT_ITEMS,
                CONTENT_ITEM_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            contentItems.add(parseContentItem(cursor));
            cursor.moveToNext();
        }

        return contentItems;
    }

    public ArrayList<Test> getAllTests() {
        ArrayList<Test> tests = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.TESTS,
                TEST_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tests.add(parseTest(cursor));
            cursor.moveToNext();
        }

        return tests;
    }

    public ArrayList<UnivScheduleEntry> getAllUniversitySchedule() {
        ArrayList<UnivScheduleEntry> univScheduleEntries = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.UNIVERSITY_SCHEDULE,
                UNIVERSITY_SCHEDULE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            univScheduleEntries.add(parseUniversitySchedule(cursor));
            cursor.moveToNext();
        }

        return univScheduleEntries;
    }

    public ArrayList<StudentScheduleEntry> getAllStudentSchedule() {
        ArrayList<StudentScheduleEntry> studentScheduleEntries = new ArrayList<>();

        Cursor cursor = mDatabase.query(
                Database.STUDENT_SCHEDULE,
                STUDENT_SCHEDULE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            studentScheduleEntries.add(parseStudentScheduleEntry(cursor));
            cursor.moveToNext();
        }

        return studentScheduleEntries;
    }

    public Task updateSimpleTask(Task task) {
        deleteSimpleTask(task);
        return addSimpleTask(task);
    }

    public void deleteSimpleTask(Task task) {
        long id = task.getId();
        deleteSimpleTask(id);
    }

    public void deleteSimpleTask(long id) {
        mDatabase.delete(Database.SIMPLE_TASKS, Database.SIMPLE_TASK_ID + " = " + id, null);
    }

    public Task addSimpleTask(Task task) {
        ContentValues values = new ContentValues();

        values.put(Database.SIMPLE_TASK_NAME, task.getName());
        values.put(Database.SIMPLE_TASK_SUBJECT_ID, task.getSubjectId());
        values.put(Database.SIMPLE_TASK_MULTI_TASK_ID,
                (task.getMultiTask() == null ? 0 : task.getMultiTaskId()));
        values.put(Database.SIMPLE_TASK_DEADLINE, task.getDeadline().toString());
        values.put(Database.SIMPLE_TASK_POINTS, task.getPoints());

        long id = mDatabase.insert(Database.SIMPLE_TASKS, null, values);

        Cursor cursor = mDatabase.query(
                Database.SIMPLE_TASKS,
                SIMPLE_TASK_TABLE_COLUMNS,
                Database.SIMPLE_TASK_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        Task taskFromDB = parseSimpleTask(cursor);
        cursor.close();
        return taskFromDB;
    }

    public MultiTask updateMultiTask(MultiTask task) {
        deleteMultiTask(task);
        return addMultiTask(task);
    }

    public void deleteMultiTask(Task task) {
        long id = task.getId();
        deleteMultiTask(id);
    }

    public void deleteMultiTask(long id) {
        mDatabase.delete(Database.MULTI_TASKS, Database.MULTI_TASK_ID + " = " + id, null);
    }

    public MultiTask addMultiTask(MultiTask task) {
        ContentValues values = new ContentValues();

        values.put(Database.MULTI_TASK_NAME, task.getName());
        values.put(Database.MULTI_TASK_SUBJECT_ID, task.getSubjectId());

        long id = mDatabase.insert(Database.MULTI_TASKS, null, values);

        Cursor cursor = mDatabase.query(
                Database.MULTI_TASKS,
                MULTI_TASK_TABLE_COLUMNS,
                Database.MULTI_TASK_SUBJECT_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        MultiTask taskFromDB = parseMultiTask(cursor);
        cursor.close();
        return taskFromDB;
    }

    public Teacher updateTeacher(Teacher teacher) {
        deleteTeacher(teacher);
        return addTeacher(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        long id = teacher.getId();
        deleteTeacher(id);
    }

    public void deleteTeacher(long id) {
        mDatabase.delete(Database.TEACHERS, Database.TEACHER_ID + " = " + id, null);
    }

    public Teacher addTeacher(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(Database.TEACHER_NAME, teacher.getName());
        values.put(Database.TEACHER_CONTACTS, teacher.getContactsAsString());

        long id = mDatabase.insert(Database.TEACHERS, null, values);

        Cursor cursor = mDatabase.query(
                Database.TEACHERS,
                TEACHER_TABLE_COLUMNS,
                Database.TEACHER_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        Teacher teacherFromDB = parseTeacher(cursor);
        cursor.close();
        return teacherFromDB;
    }

    public Subject updateSubject(Subject subject) {
        deleteSubject(subject);
        return addSubject(subject);
    }

    public void deleteSubject(Subject subject) {
        long id = subject.getId();
        deleteSubject(id);
    }

    public void deleteSubject(long id) {
        mDatabase.delete(Database.SUBJECTS, Database.SUBJECT_ID + " = " + id, null);
    }

    public Subject addSubject(Subject subject) {
        ContentValues values = new ContentValues();

        values.put(Database.SUBJECT_NAME, subject.getName());
        values.put(Database.SUBJECT_TEACHER_ID, subject.getTeacherId());
        values.put(Database.SUBJECT_TYPE, subject.getType().name());
        values.put(Database.SUBJECT_COLOR, subject.getColorTag().name());

        long id = mDatabase.insert(Database.SUBJECTS, null, values);

        Cursor cursor = mDatabase.query(
                Database.SUBJECTS,
                SUBJECT_TABLE_COLUMNS,
                Database.SUBJECT_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        Subject subjectFromDB = parseSubject(cursor);
        cursor.close();
        return subjectFromDB;
    }

    public Content updateContent(Content content) {
        deleteContent(content);
        return addContent(content);
    }

    public void deleteContent(Content content) {
        long id = content.getId();
        deleteContent(id);
    }

    public void deleteContent(long id) {
        mDatabase.delete(Database.CONTENTS, Database.CONTENT_ID + " = " + id, null);
    }

    public Content addContent(Content content) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_SUBJECT_ID,
                (content.getSubject() == null ? 0 : content.getSubject().getId()));
        values.put(Database.CONTENT_TASK_ID,
                (content.getTask() == null ? 0 : content.getTask().getId()));
        values.put(Database.CONTENT_TEST_ID,
                (content.getTest() == null ? 0 : content.getTest().getId()));

        long id = mDatabase.insert(Database.CONTENTS, null, values);

        Cursor cursor = mDatabase.query(
                Database.CONTENTS,
                CONTENTS_TABLE_COLUMNS,
                Database.CONTENT_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        Content contentFromDB = parseContent(cursor);
        cursor.close();
        return contentFromDB;
    }

    public ContentItem updateContentItem(ContentItem contentItem) {
        deleteContentItem(contentItem);
        return addContentItem(contentItem);
    }

    public void deleteContentItem(ContentItem contentItem) {
        long id = contentItem.getId();
        deleteContentItem(id);
    }

    public void deleteContentItem(long id) {
        mDatabase.delete(Database.CONTENT_ITEMS, Database.CONTENT_ITEM_ID + " = " + id, null);
    }

    public ContentItem addContentItem(ContentItem contentItem) {
        ContentValues values = new ContentValues();

        values.put(Database.CONTENT_ITEM_CONTENT_ID, contentItem.getContent().getId());
        values.put(Database.CONTENT_ITEM_TYPE, contentItem.getType().name());
        values.put(Database.CONTENT_ITEM_SOURCE, contentItem.getSource());
        values.put(Database.CONTENT_ITEM_TEXT, contentItem.getText());

        long id = mDatabase.insert(Database.CONTENT_ITEMS, null, values);

        Cursor cursor = mDatabase.query(
                Database.CONTENT_ITEMS,
                CONTENT_ITEM_TABLE_COLUMNS,
                Database.CONTENT_ITEM_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        ContentItem contentItemFromDB = parseContentItem(cursor);
        cursor.close();
        return contentItemFromDB;
    }

    public Test updateTest(Test test) {
        deleteTest(test);
        return addTest(test);
    }

    public void deleteTest(Test test) {
        long id = test.getId();
        deleteTest(id);
    }

    public void deleteTest(long id) {
        mDatabase.delete(Database.TESTS, Database.TEST_ID + " = " + id, null);
    }

    public Test addTest(Test test) {
        ContentValues values = new ContentValues();

        values.put(Database.TEST_TYPE, test.getTestType().name());
        values.put(Database.TEST_SUBJECT_ID, test.getSubject().getId());
        values.put(Database.TEST_DATE, test.getDate().toString());

        long id = mDatabase.insert(Database.TESTS, null, values);

        Cursor cursor = mDatabase.query(
                Database.TESTS,
                TEST_TABLE_COLUMNS,
                Database.TEST_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        Test testFromDB = parseTest(cursor);
        cursor.close();
        return testFromDB;
    }

    public UnivScheduleEntry updateUniversitySchedule(UnivScheduleEntry univScheduleEntry) {
        deleteUniversitySchedule(univScheduleEntry);
        return addUniversitySchedule(univScheduleEntry);
    }

    public void deleteUniversitySchedule(UnivScheduleEntry univScheduleEntry) {
        long id = univScheduleEntry.getId();
        deleteUniversitySchedule(id);
    }

    public void deleteUniversitySchedule(long id) {
        mDatabase.delete(
                Database.UNIVERSITY_SCHEDULE,
                Database.UNIVERSITY_SCHEDULE_ID + " = " + id, null);
    }

    public UnivScheduleEntry addUniversitySchedule(UnivScheduleEntry univScheduleEntry) {
        ContentValues values = new ContentValues();

        values.put(
                Database.UNIVERSITY_SCHEDULE_LESSON_NUMBER,
                univScheduleEntry.getLessonNumber());
        values.put(
                Database.UNIVERSITY_SCHEDULE_DAY,
                univScheduleEntry.getDay());
        values.put(
                Database.UNIVERSITY_SCHEDULE_START,
                univScheduleEntry.getStart().toString());
        values.put(
                Database.UNIVERSITY_SCHEDULE_END,
                univScheduleEntry.getEnd().toString());

        long id = mDatabase.insert(Database.UNIVERSITY_SCHEDULE, null, values);

        Cursor cursor = mDatabase.query(
                Database.UNIVERSITY_SCHEDULE,
                UNIVERSITY_SCHEDULE_COLUMNS,
                Database.UNIVERSITY_SCHEDULE_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        UnivScheduleEntry universityScheduleFromDB = parseUniversitySchedule(cursor);
        cursor.close();
        return universityScheduleFromDB;
    }

    public StudentScheduleEntry updateStudentSchedule(StudentScheduleEntry studentScheduleEntry) {
        deleteStudentSchedule(studentScheduleEntry);
        return addStudentSchedule(studentScheduleEntry);
    }

    public void deleteStudentSchedule(StudentScheduleEntry studentScheduleEntry) {
        long id = studentScheduleEntry.getId();
        deleteStudentSchedule(id);
    }

    public void deleteStudentSchedule(long id) {
        mDatabase.delete(
                Database.STUDENT_SCHEDULE,
                Database.STUDENT_SCHEDULE_ID + " = " + id, null);
    }

    public StudentScheduleEntry addStudentSchedule(StudentScheduleEntry studentScheduleEntry) {
        ContentValues values = new ContentValues();

        values.put(
                Database.STUDENT_SCHEDULE_UNIVERSITY_SCHEDULE_ID,
                studentScheduleEntry.getUnivScheduleEntryId());
        values.put(
                Database.STUDENT_SCHEDULE_SUBJECT_ID,
                studentScheduleEntry.getSubjectId());
        values.put(
                Database.STUDENT_SCHEDULE_CLASSROOM,
                studentScheduleEntry.getClassroom());

        long id = mDatabase.insert(Database.STUDENT_SCHEDULE, null, values);

        Cursor cursor = mDatabase.query(
                Database.STUDENT_SCHEDULE,
                STUDENT_SCHEDULE_COLUMNS,
                Database.STUDENT_SCHEDULE_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        StudentScheduleEntry studentScheduleEntryFromDB = parseStudentScheduleEntry(cursor);
        cursor.close();
        return studentScheduleEntryFromDB;
    }

    private Task parseSimpleTask(Cursor cursor) {
        Task task = new Task();

        task.setId(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setSubjectId(cursor.getInt(2));
        task.setMultiTaskId(cursor.getInt(3));
        task.setDeadline(new LocalDate(cursor.getString(4)));
        task.setPoints(cursor.getDouble(5));

        return task;
    }

    private MultiTask parseMultiTask(Cursor cursor) {
        MultiTask task = new MultiTask();

        task.setId(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setSubjectId(cursor.getInt(2));

        return task;
    }

    private Teacher parseTeacher(Cursor cursor) {
        Teacher teacher = new Teacher();

        teacher.setId(cursor.getInt(0));
        teacher.setName(cursor.getString(1));
        teacher.setContacts(cursor.getString(2));

        return teacher;
    }

    private Subject parseSubject(Cursor cursor) {
        Subject subject = new Subject();

        subject.setId(cursor.getInt(0));
        subject.setName(cursor.getString(1));
        subject.setTeacherId(cursor.getInt(2));
        subject.setType(SubjectType.valueOf(cursor.getString(3)));
        subject.setColorTag(ColorTag.valueOf(cursor.getString(4)));

        return subject;
    }

    private Content parseContent(Cursor cursor) {
        Content content = new Content();

        content.setId(cursor.getInt(0));
        content.setSubjectId(cursor.getInt(1));
        content.setTaskId(cursor.getInt(2));
        content.setTestId(cursor.getInt(3));

        return content;
    }

    private ContentItem parseContentItem(Cursor cursor) {
        ContentItem contentItem = new ContentItem();

        contentItem.setId(cursor.getInt(0));
        contentItem.setContentId(cursor.getInt(1));
        contentItem.setType(ContentType.valueOf(cursor.getString(2)));
        contentItem.setSource(cursor.getString(3));
        contentItem.setText(cursor.getString(4));

        return contentItem;
    }

    private Test parseTest(Cursor cursor) {
        Test test = new Test();

        test.setId(cursor.getInt(0));
        test.setTestType(TestType.valueOf(cursor.getString(1)));
        test.setSubjectId(cursor.getInt(2));
        test.setDate(new LocalDate(cursor.getString(3)));

        return test;
    }

    private UnivScheduleEntry parseUniversitySchedule(Cursor cursor) {
        UnivScheduleEntry univScheduleEntry = new UnivScheduleEntry();

        univScheduleEntry.setId(cursor.getInt(0));
        univScheduleEntry.setLessonNumber(cursor.getInt(1));
        univScheduleEntry.setDay(cursor.getInt(2));
        univScheduleEntry.setStart(new LocalTime(cursor.getString(3)));
        univScheduleEntry.setEnd(new LocalTime(cursor.getString(4)));

        return univScheduleEntry;
    }

    private StudentScheduleEntry parseStudentScheduleEntry(Cursor cursor) {
        StudentScheduleEntry studentScheduleEntry = new StudentScheduleEntry();

        studentScheduleEntry.setId(cursor.getInt(0));
        studentScheduleEntry.setUnivScheduleEntryId(cursor.getInt(1));
        studentScheduleEntry.setSubjectId(cursor.getInt(2));
        studentScheduleEntry.setClassroom(cursor.getInt(3));

        return studentScheduleEntry;
    }
}
