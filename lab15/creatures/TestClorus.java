package creatures;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(20);
        Clorus d = c.replicate();
        assertEquals(10, c.energy(), 0.01);
        assertEquals(10, d.energy(), 0.01);

        Plip e = new Plip(10);
        Plip f = e.replicate();
        assertEquals(5, e.energy(), 0.01);
        assertEquals(5, f.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Plip p = new Plip(2);
        Clorus c = new Clorus(8);
        c.attack(p);
        assertEquals(10, c.energy(), 0.01);
    }
}
