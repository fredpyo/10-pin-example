package com.swnat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;

public class FrameTest {

    @Test
    void testRollSumIsValid() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(1);

        Roll c2 = new Roll();
        c2.setPlayer("John Doe");
        c2.setPinsThrown(10);

        Frame f = new Frame();

        f.addRoll(c);
        Assertions.assertThrows(RuntimeException.class, () -> {
            f.addRoll(c2);
        });
    }

    @Test
    void testIsStrike() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(10);

        Frame f = new Frame();
        f.addRoll(c);

        assertEquals(true, f.isStrike());
    }

    @Test
    void testIsNotStrike() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(7);
        Roll c2 = new Roll();
        c2.setPlayer("John Doe");
        c2.setPinsThrown(3);

        Frame f = new Frame();
        f.addRoll(c);
        f.addRoll(c2);

        assertEquals(false, f.isStrike());
    }

    @Test
    void testIsSpare() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(7);
        Roll c2 = new Roll();
        c2.setPlayer("John Doe");
        c2.setPinsThrown(3);

        Frame f = new Frame();
        f.addRoll(c);
        f.addRoll(c2);

        assertEquals(true, f.isSpare());
    }

    @Test
    void testIsNotSpare() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(2);
        Roll c2 = new Roll();
        c2.setPlayer("John Doe");
        c2.setPinsThrown(1);

        Frame f = new Frame();
        f.addRoll(c);
        f.addRoll(c2);

        assertEquals(false, f.isSpare());
    }

    @Test
    void testStrikeIsSpare() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(10);

        Frame f = new Frame();
        f.addRoll(c);

        assertEquals(false, f.isSpare());
    }


    @Test
    void testIsOpen() {
        Roll c = new Roll();
        c.setPlayer("John Doe");
        c.setPinsThrown(2);
        Roll c2 = new Roll();
        c2.setPlayer("John Doe");
        c2.setPinsThrown(1);

        Frame f = new Frame();
        f.addRoll(c);
        f.addRoll(c2);

        assertEquals(true, f.isOpen());
    }
}
