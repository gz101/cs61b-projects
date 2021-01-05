package hw4.puzzle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard {

    /** Helper method to generate board like in HW description. */
    private int[][] tileGen() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = -1;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        return tiles;
    }

    @Test
    public void testManhattan() {
        Board b = new Board(tileGen());
        assertEquals(10, b.manhattan());
    }

    @Test
    public void testHamming() {
        Board b = new Board(tileGen());
        assertEquals(5, b.hamming());
    }

    @Test
    public void testEquals() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }

        int cnt2 = 0;
        int[][] y = new int[r][c];
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                y[i][j] = cnt2;
                cnt2 += 1;
            }
        }

        int[][] z = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                z[i][j] = i + j + 1;
            }
        }

        Board b = new Board(x);
        Board b2 = new Board(x);
        Board b3 = new Board(y);
        Board b4 = new Board(z);

        assertTrue(b.equals(b2));
        assertTrue(b.equals(b3));
        assertFalse(b.equals(b4));
    }

    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
        Board b = new Board(x);
        assertEquals("Your Board class is not being initialized with the right values.", 0, b.tileAt(0, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 1, b.tileAt(0, 1));
        assertEquals("Your Board class is not being initialized with the right values.", 2, b.tileAt(1, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 3, b.tileAt(1, 1));

        x[1][1] = 1000;
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }
}
