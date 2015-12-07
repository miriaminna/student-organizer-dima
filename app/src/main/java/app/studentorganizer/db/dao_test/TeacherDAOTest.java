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

    static {
        Teacher teacher = new Teacher();
        teacher.setId((int) getNewId());
        teacher.setName("Oleksandr Galkin");
        teacher.setType(TeacherType.DOCENT.toString());
        teacher.setContacts("email:galkin@unicyb.kiev.ua\nskype:kvak313");
        mTeachers.add(teacher);

        teacher = new Teacher();
        teacher.setId((int) getNewId());
        teacher.setName("Oleksandr Maksymets");
        teacher.setType(TeacherType.POSTGRADUATE.toString());
        teacher.setContacts("email:maksymets@gmail.com");
        mTeachers.add(teacher);
    }

    @Override
    public List<Teacher> getAllEntities() {
        return mTeachers;
    }

    @Override
    public Teacher getByID(long id) {
        for (Teacher t: mTeachers) {
            if (t.getId() == id) {
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
    public boolean deleteEntity(long id) {
        for (Teacher t: mTeachers) {
            if (t.getId() == id) {
                mTeachers.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public long addEntity(Teacher teacher) {
        teacher.setId((int) getNewId());
        mTeachers.add(teacher);
        System.out.println("Teachers: " + mTeachers.size());
        return teacher.getId();
    }
}
