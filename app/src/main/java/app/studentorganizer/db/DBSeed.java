package app.studentorganizer.db;

import org.joda.time.LocalDate;

import app.studentorganizer.com.TeacherType;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public class DBSeed {
    public static void seed() {
        seedTeachers();
        seedTasks();
    }

    public static void seedTeachers() {
        Teacher teacher = new Teacher();
        teacher.setName("Oleksandr Galkin");
        teacher.setType(TeacherType.DOCENT);
        teacher.setContacts("email:galkin@unicyb.kiev.ua\nskype:kvak313");
        DBFactory.getFactory().getTeacherDAO().addEntity(teacher);

        teacher = new Teacher();
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
//        task.setSubject(new Subject());
//        task.getSubject().setColorTag(ColorTag.GREEN);
        DBFactory.getFactory().getTaskDAO().addEntity(task);

        task = new Task();
        task.setName("Algebra - Smile :)");
        task.setDeadline(new LocalDate().plusDays(1));
        task.setPoints(30.0);
        task.setProgress(75);
        task.setTarget(100);
//        task.setSubject(new Subject());
//        task.getSubject().setColorTag(ColorTag.BLUE);
        DBFactory.getFactory().getTaskDAO().addEntity(task);
    }
}
