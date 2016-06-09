import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import app.studentorganizer.entities.Teacher;
import app.studentorganizer.util.Pair;

/**
 * Tests for {@link Teacher} class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TeacherTest {
    @Test
    public void teacherContactsTest() {
        Teacher teacher = new Teacher();
        String expected = "a:a\nb:c\nabc:def";

        teacher.setContacts("a:a\nb:c\nff:0");
        teacher.removeContact(new Pair<>("ff", "0"));
        teacher.addContact(new Pair<>("abc", "def"));
        teacher.removeContact(new Pair<>("x", "y"));

        Assert.assertEquals(expected, teacher.getContactsAsString());
    }
}
