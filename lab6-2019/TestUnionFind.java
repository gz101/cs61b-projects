import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testSizeOf() {
        UnionFind u = new UnionFind(5);
        u.union(1, 2);
        assertEquals(2, u.sizeOf(1));
        u.union(2, 3);
        assertEquals(3, u.sizeOf(3));
        assertEquals(3, u.sizeOf(2));
        assertEquals(1, u.sizeOf(4));
    }

    @Test
    public void testParent() {
        UnionFind u = new UnionFind(5);
        assertEquals(-1, u.parent(3));
        u.union(3, 4);
        assertEquals(4, u.parent(3));
        u.union(3, 2);
        assertEquals(3, u.parent(2));
    }

    @Test
    public void testConnected() {
        UnionFind u = new UnionFind(5);
        assertTrue(u.connected(1, 1));
        assertFalse(u.connected(1, 2));
        u.union(1, 2);
        assertTrue(u.connected(1, 2));
    }

    @Test
    public void testFind() {
        UnionFind u = new UnionFind(5);
        assertEquals(1, u.find(1));
        u.union(1, 2);
        assertEquals(2, u.find(1));
    }
}
