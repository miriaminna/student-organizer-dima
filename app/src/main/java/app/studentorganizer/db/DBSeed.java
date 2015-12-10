package app.studentorganizer.db;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.DayConstants;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.UnivScheduleEntry;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public class DBSeed {
    public static void seed() {
        seedTeachers();
        seedTasks();
        seedSubjects();
        seedUniSchedule();
        seedMySchedule();
    }

    public static void seedTeachers() {
        Teacher teacher = new Teacher();
        teacher.setId(0L);
        teacher.setName("Oleksandr Galkin");
        teacher.setType(TeacherType.DOCENT);
        teacher.setContacts("email:galkin@unicyb.kiev.ua\nskype:kvak313");
        DBFactory.getFactory().getTeacherDAO().addEntity(teacher);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Oleksandr Maksymets");
        teacher.setType(TeacherType.POSTGRADUATE);
        teacher.setContacts("email:maksymets@gmail.com");
        DBFactory.getFactory().getTeacherDAO().addEntity(teacher);
    }

    public static void seedTasks() {
        Task task = new Task();
        task.setName("Programming - Labs");
        task.setDeadline(new LocalDate().plusDays(1));
        task.setPoints(30.0);
        task.setProgress(10);
        task.setTarget(40);
        task.setId(0L);
        task.setSubjectId(1L);
//        task.setSubject(new Subject());
//        task.getSubject().setColorTag(ColorTag.GREEN);
        DBFactory.getFactory().getTaskDAO().addEntity(task);

        task = new Task();
        task.setName("Algebra - Smile :)");
        task.setDeadline(new LocalDate().plusDays(1));
        task.setPoints(30.0);
        task.setProgress(75);
        task.setTarget(100);
        task.setId(1L);
        task.setSubjectId(2L);
//        task.setSubject(new Subject());
//        task.getSubject().setColorTag(ColorTag.BLUE);
        DBFactory.getFactory().getTaskDAO().addEntity(task);
    }

    public static void seedSubjects() {
        Subject subject = new Subject();
        subject.setName("Object-oriented programming");
        subject.setType(SubjectType.EXAM);
        subject.setColorTag(ColorTag.BLUE);
        subject.setStartDate(new LocalDate(2015, 9, 1));
        subject.setEndDate(new LocalDate(2015, 12, 25));
        subject.setTeacherId(0L);

        DBFactory.getFactory().getSubjectDAO().addEntity(subject);

        subject = new Subject();
        subject.setName("Mathematical Analysis");
        subject.setType(SubjectType.CREDIT);
        subject.setColorTag(ColorTag.GREEN);
        subject.setTeacherId(1L);
        subject.setStartDate(new LocalDate(2015, 2, 15));
        subject.setEndDate(new LocalDate(2015, 5, 30));
        List<Long> ids = new ArrayList<>();
        ids.add(0L);
        subject.setTasksIds(ids);
//        subject.setTasks(DBFactory.getFactory().getTaskDAO().getAllEntities());

        DBFactory.getFactory().getSubjectDAO().addEntity(subject);

        subject = new Subject();
        subject.setName("Probability Theory");
        subject.setType(SubjectType.EXAM);
        subject.setColorTag(ColorTag.ORANGE);
        subject.setTeacherId(2L);
        subject.setStartDate(new LocalDate(2015, 9, 1));
        subject.setEndDate(new LocalDate(2016, 1, 15));
        ids = new ArrayList<>();
        ids.add(1L);
        subject.setTasksIds(ids);

        DBFactory.getFactory().getSubjectDAO().addEntity(subject);
    }

    private static void seedUniSchedule() {
        UnivScheduleEntry univSchedule = new UnivScheduleEntry();
        univSchedule.setLessonNumber(1);
        univSchedule.setStart(new LocalTime(8, 40));
        univSchedule.setEnd(new LocalTime(10, 15));

        DBFactory.getFactory().getUnivScheduleDAO().addEntity(univSchedule);

        univSchedule = new UnivScheduleEntry();
        univSchedule.setLessonNumber(2);
        univSchedule.setStart(new LocalTime(10, 35));
        univSchedule.setEnd(new LocalTime(12, 10));

        DBFactory.getFactory().getUnivScheduleDAO().addEntity(univSchedule);
    }

    private static void seedMySchedule() {
        StudentScheduleEntry studentSchedule = new StudentScheduleEntry();
        studentSchedule.setDay(DayConstants.MONDAY);
        studentSchedule.setClassroom(205);
        studentSchedule.setSubjectId(
                DBFactory.getFactory().getSubjectDAO().getAllEntities().get(0).getId()
        );
        studentSchedule.setUnivScheduleEntryId(
                DBFactory.getFactory().getUnivScheduleDAO().getAllEntities().get(0).getId()
        );

        DBFactory.getFactory().getStudentScheduleDAO().addEntity(studentSchedule);

        studentSchedule = new StudentScheduleEntry();
        studentSchedule.setDay(DayConstants.TUESDAY);
        studentSchedule.setClassroom(306);
        studentSchedule.setSubjectId(
                DBFactory.getFactory().getSubjectDAO().getAllEntities().get(1).getId()
        );
        studentSchedule.setUnivScheduleEntryId(
                DBFactory.getFactory().getUnivScheduleDAO().getAllEntities().get(1).getId()
        );

        DBFactory.getFactory().getStudentScheduleDAO().addEntity(studentSchedule);
    }
}
