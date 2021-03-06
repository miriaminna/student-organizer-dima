package app.studentorganizer.db;

import app.studentorganizer.db.dao.ContentItemDAO;
import app.studentorganizer.db.dao.MultiTaskDAO;
import app.studentorganizer.db.dao.StudentScheduleDAO;
import app.studentorganizer.db.dao.SubjectDAO;
import app.studentorganizer.db.dao.TaskDAO;
import app.studentorganizer.db.dao.TeacherDAO;
import app.studentorganizer.db.dao.TestDAO;
import app.studentorganizer.db.dao.UniversityScheduleDAO;
import app.studentorganizer.db.dao_sqlite.ContentItemDAOSQLite;
import app.studentorganizer.db.dao_sqlite.MultiTaskDAOSQLite;
import app.studentorganizer.db.dao_sqlite.StudentScheduleDAOSQLite;
import app.studentorganizer.db.dao_sqlite.SubjectDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TaskDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TeacherDAOSQLite;
import app.studentorganizer.db.dao_sqlite.TestDAOSQLite;
import app.studentorganizer.db.dao_sqlite.UniversityScheduleDAOSQLite;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class DBFactory {
    private static DBFactory mFactory;

    protected ContentItemDAO mContentItemDAO;
    protected MultiTaskDAO mMultiTaskDAO;
    protected StudentScheduleDAO mStudentScheduleDAO;
    protected SubjectDAO mSubjectDAO;
    protected TaskDAO mTaskDAO;
    protected TeacherDAO mTeacherDAO;
    protected TestDAO mTestDAO;
    protected UniversityScheduleDAO mUnivScheduleDAO;

    private DBFactory() {}

    public synchronized static DBFactory getFactory() {
        if (mFactory == null) {
            mFactory = new DBFactory();
        }
        return mFactory;
    }

    public synchronized ContentItemDAO getContentItemDAO() {
        if (mContentItemDAO == null) {
            mContentItemDAO = new ContentItemDAOSQLite();
        }
        return mContentItemDAO;
    }

    public synchronized MultiTaskDAO getMultiTaskDAO() {
        if (mMultiTaskDAO == null) {
            mMultiTaskDAO = new MultiTaskDAOSQLite();
        }
        return mMultiTaskDAO;
    }

    public synchronized StudentScheduleDAO getStudentScheduleDAO() {
        if (mStudentScheduleDAO == null) {
            mStudentScheduleDAO = new StudentScheduleDAOSQLite();
        }
        return mStudentScheduleDAO;
    }

    public synchronized SubjectDAO getSubjectDAO() {
        if (mSubjectDAO == null) {
            mSubjectDAO = new SubjectDAOSQLite();
//            mSubjectDAO = new SubjectDAOTest();
        }
        return mSubjectDAO;
    }

    public synchronized TaskDAO getTaskDAO() {
        if (mTaskDAO == null) {
            mTaskDAO = new TaskDAOSQLite();
//            mTaskDAO = new TaskDAOTest();
        }
        return mTaskDAO;
    }

    public synchronized TestDAO getTestDAO() {
        if (mTestDAO == null) {
            mTestDAO = new TestDAOSQLite();
        }
        return mTestDAO;
    }

    public synchronized UniversityScheduleDAO getUnivScheduleDAO() {
        if (mUnivScheduleDAO == null) {
            mUnivScheduleDAO = new UniversityScheduleDAOSQLite();
        }
        return mUnivScheduleDAO;
    }

    public synchronized TeacherDAO getTeacherDAO() {
        if (mTeacherDAO == null) {
            mTeacherDAO = new TeacherDAOSQLite();
//            mTeacherDAO = new TeacherDAOTest();
        }
        return mTeacherDAO;
    }
}
