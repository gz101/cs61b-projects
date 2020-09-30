import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void testFlik() {
        int a = 128;
        int b = 128;
        int c = 3;
        assertTrue(Flik.isSameNumber(a, b));
        assertFalse(Flik.isSameNumber(a, c));
    }
}
