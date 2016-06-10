import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import app.studentorganizer.util.Pair;

/**
 * Created by akalinichenko on 6/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PairTest {
    @Test
    public void pairConstructorTest() {
        long first = 1;
        long second = 2;
        Pair<Long, Long> p = new Pair<>(first, second);

        Assert.assertEquals((Long) first, p.first);
        Assert.assertEquals((Long) second, p.second);
    }
}
