package com.swnat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;

public class RollTest {

    @Test
    void testFromString0() {
        Roll expected = new Roll();
        expected.setPlayer("John Doe");
        expected.setFoul(false);
        expected.setPinsThrown(0);

        Roll actual = Roll.fromString("John Doe\t0");

        assertEquals(expected, actual);
    }

    @Test
    void testFromString7() {
        Roll expected = new Roll();
        expected.setPlayer("John Doe");
        expected.setFoul(false);
        expected.setPinsThrown(7);

        Roll actual = Roll.fromString("John Doe\t7");

        assertEquals(expected, actual);
    }

    @Test
    void testFromString10() {
        Roll expected = new Roll();
        expected.setPlayer("John Doe");
        expected.setFoul(false);
        expected.setPinsThrown(10);

        Roll actual = Roll.fromString("John Doe\t10");

        assertEquals(expected, actual);
    }

    @Test
    void testFromStringFoul() {
        Roll expected = new Roll();
        expected.setPlayer("John Doe");
        expected.setFoul(true);
        expected.setPinsThrown(0);

        Roll actual = Roll.fromString("John Doe\tF");

        assertEquals(expected, actual);
    }

    @Test
    void testToString0() {
        Roll r = new Roll();
        r.setPinsThrown(0);
        r.setPlayer("Peter");

        assertEquals("0", r.toString());
    }

    @Test
    void testToStringLessThanTen() {
        Roll r = new Roll();
        r.setPinsThrown(8);
        r.setPlayer("Peter");

        assertEquals("8", r.toString());
    }

    @Test
    void testToStringTen() {
        Roll r = new Roll();
        r.setPinsThrown(10);
        r.setPlayer("Peter");

        assertEquals("10", r.toString());
    }

    @Test
    void testToStringFoul() {
        Roll r = new Roll();
        r.setFoul(true);
        r.setPlayer("Peter");

        assertEquals("F", r.toString());
    }

    @Test
    void testInvalidRolls() {
        Roll r = new Roll();
        r.setPlayer("Peter");

        Assertions.assertThrows(RuntimeException.class, () -> {
            r.setPinsThrown(-1);
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            r.setPinsThrown(11);
        });
    }

    @Test
    void testFoulDoesntThrowPins() {
        Roll r = new Roll();
        r.setPlayer("Peter");
        r.setFoul(true);

        Assertions.assertThrows(RuntimeException.class, () -> {
            r.setPinsThrown(10);
        });
    }

}
