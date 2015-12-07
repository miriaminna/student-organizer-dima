package app.studentorganizer.db;

import app.studentorganizer.db.dao.GenericDAO;
import app.studentorganizer.db.dao_sqlite.ContentItemDAOSQLite;
import app.studentorganizer.db.dao_sqlite.ContentsDAOSQLite;
import app.studentorganizer.db.dao_sqlite.MultiTaskDAOSQLite;
import app.studentorganizer.db.dao_sqlite.StudentScheduleDAOSQLite;
import app.studentorganizer.db.dao_sqlite.SubjectDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TaskDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TeacherDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TestDAOSQLite;
import app.studentorganizer.db.dao_sqlite.UniversityScheduleDAOSQLite;
import app.studentorganizer.db.dao_test.TaskDAOTest;
import app.studentorganizer.db.dao_test.TeacherDAOTest;
import app.studentorganizer.entities.Content;
import app.studentorganizer.entities.ContentItem;
import app.studentorganizer.entities.MultiTask;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.Test;
import app.studentorganizer.entities.UnivScheduleEntry;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class DBFactory {
    private static DBFactory mFactory;

    protected GenericDAO<ContentItem> mContentItemDAO;
    protected GenericDAO<Content> mContentsDAO;
    protected GenericDAO<MultiTask> mMultiTaskDAO;
    protected GenericDAO<StudentScheduleEntry> mStudentScheduleDAO;
    protected GenericDAO<Subject> mSubjectDAO;
    protected GenericDAO<Task> mTaskDAO;
    protected GenericDAO<Teacher> mTeacherDAO;
    protected GenericDAO<Test> mTestDAO;
    protected GenericDAO<UnivScheduleEntry> mUnivScheduleDAO;

    private DBFactory() {}

    public synchronized static DBFactory getFactory() {
        if (mFactory == null) {
            mFactory = new DBFactory();
        }
        return mFactory;
    }

    public synchronized GenericDAO<ContentItem> getContentItemDAO() {
        if (mContentItemDAO == null) {
            mContentItemDAO = new ContentItemDAOSQLite();
        }
        return mContentItemDAO;
    }

    public synchronized GenericDAO<Content> getContentsDAO() {
        if (mContentsDAO == null) {
            mContentsDAO = new ContentsDAOSQLite();
        }
        return mContentsDAO;
    }

    public synchronized GenericDAO<MultiTask> getMultiTaskDAO() {
        if (mMultiTaskDAO == null) {
            mMultiTaskDAO = new MultiTaskDAOSQLite();
        }
        return mMultiTaskDAO;
    }

    public synchronized GenericDAO<StudentScheduleEntry> getStudentScheduleDAO() {
        if (mStudentScheduleDAO == null) {
            mStudentScheduleDAO = new StudentScheduleDAOSQLite();
        }
        return mStudentScheduleDAO;
    }

    public synchronized GenericDAO<Subject> getSubjectDAO() {
        if (mSubjectDAO == null) {
            mSubjectDAO = new SubjectDAOSQLite();
        }
        return mSubjectDAO;
    }

    public synchronized GenericDAO<Task> getTaskDAO() {
        if (mTaskDAO == null) {
//            mTaskDAO = new TaskDAOSQLite();
            mTaskDAO = new TaskDAOTest();
        }
        return mTaskDAO;
    }

    public synchronized GenericDAO<Test> getTestDAO() {
        if (mTestDAO == null) {
            mTestDAO = new TestDAOSQLite();
        }
        return mTestDAO;
    }

    public synchronized GenericDAO<UnivScheduleEntry> getUnivScheduleDAO() {
        if (mUnivScheduleDAO == null) {
            mUnivScheduleDAO = new UniversityScheduleDAOSQLite();
        }
        return mUnivScheduleDAO;
    }

    public synchronized GenericDAO<Teacher> getTeacherDAO() {
        if (mTeacherDAO == null) {
//            mTeacherDAO = new TeacherDAOSQLite();
            mTeacherDAO = new TeacherDAOTest();
        }
        return mTeacherDAO;
    }
}
