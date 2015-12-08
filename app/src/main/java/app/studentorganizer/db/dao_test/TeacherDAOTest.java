package app.studentorganizer.db.dao_test;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.TeacherType;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TeacherDAOTest extends BaseDAOTest<Teacher> {
    private static final List<Teacher> mTeachers = new ArrayList<>();

    @Override
    public List<Teacher> getAllEntities() {
        return mTeachers;
    }

    @Override
    public Teacher getByID(Long id) {
        for (Teacher t: mTeachers) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean updateEntity(Teacher teacher) {
        for (Teacher t: mTeachers) {
            if (t.getId().equals(teacher.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Long id) {
        for (Teacher t: mTeachers) {
            if (t.getId().equals(id)) {
                mTeachers.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public Long addEntity(Teacher teacher) {
        teacher.setId(getNewId());
        mTeachers.add(teacher);
        System.out.println("Teachers: " + mTeachers.size());
        return teacher.getId();
    }
}
