package com.swnat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
class FileParserTest {
    /**
     * Rigorous Test.
     * @throws IOException
     */
    @Test
    void testReadFile() throws IOException {
        FileParser fp = new FileParser();
        List<String> expected = Arrays.asList(new String[] {
            "Jeff	10",
            "John	3",
            "John	7",
            "Jeff	0",
            "Jeff	3",
            "John	F"
        });
        List<String> actual = fp.readFile("src/test/resources/input.txt");

        assertEquals(expected, actual);
    }
}
