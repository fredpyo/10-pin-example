package com.swnat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

class GameEngineTest {

    GameEngine ge;

    @BeforeEach
    void init() {
        this.ge = new GameEngine(new ChampionshipScoreCalculator());
    }

    @Test
    void testSingleRoll() {
        this.ge.addRoll(Roll.fromString("John\t2"));

        assertEquals(null, this.ge.scoreBoard.getPlayerFrames("John").get(0).score);
    }

    @Test
    void testSpare() {
        this.ge.addRoll(Roll.fromString("John\t3"));
        this.ge.addRoll(Roll.fromString("John\t7"));

        assertEquals(13, this.ge.scoreBoard.getPlayerFrames("John").get(0).score);
    }

    @Test
    void testOpenFrame() {
        this.ge.addRoll(Roll.fromString("John\t2"));
        this.ge.addRoll(Roll.fromString("John\t7"));

        assertEquals(9, this.ge.scoreBoard.getPlayerFrames("John").get(0).score);
    }

    @Test
    void testStrike() {
        this.ge.addRoll(Roll.fromString("John\t10"));

        assertEquals(30, this.ge.scoreBoard.getPlayerFrames("John").get(0).score);
    }

    @Test
    void testAccumulatedScoreWithOpenFrame() {
        this.ge.addRoll(Roll.fromString("John\t2"));
        this.ge.addRoll(Roll.fromString("John\t7"));
        this.ge.addRoll(Roll.fromString("John\t3"));
        this.ge.addRoll(Roll.fromString("John\t4"));

        assertEquals(9, this.ge.scoreBoard.getPlayerFrames("John").get(0).getAccumulatedScore());
        assertEquals(7, this.ge.scoreBoard.getPlayerFrames("John").get(1).getScore());
        assertEquals(16, this.ge.scoreBoard.getPlayerFrames("John").get(1).getAccumulatedScore());
    }

    @Test
    void testAccumulatedScoreWithSpares() {
        this.ge.addRoll(Roll.fromString("John\t10"));
        this.ge.addRoll(Roll.fromString("John\t7"));
        this.ge.addRoll(Roll.fromString("John\t3"));

        assertEquals(30, this.ge.scoreBoard.getPlayerFrames("John").get(0).getAccumulatedScore());
        assertEquals(17, this.ge.scoreBoard.getPlayerFrames("John").get(1).getScore());
        assertEquals(47, this.ge.scoreBoard.getPlayerFrames("John").get(1).getAccumulatedScore());
    }

    @Test
    void testAccumulatedScoreWithStrikes() {
        this.ge.addRoll(Roll.fromString("John\t10"));
        this.ge.addRoll(Roll.fromString("John\t10"));

        assertEquals(30, this.ge.scoreBoard.getPlayerFrames("John").get(0).getAccumulatedScore());
        assertEquals(60, this.ge.scoreBoard.getPlayerFrames("John").get(1).getAccumulatedScore());
    }

    @Test
    void testAddingTooManyRollsAndFrames() {

    }

    @Test
    void testPrintBoard() {
        this.ge.addRoll(Roll.fromString("John\t10"));
        this.ge.addRoll(Roll.fromString("John\t7"));
        this.ge.addRoll(Roll.fromString("John\t3"));
        this.ge.addRoll(Roll.fromString("John\t10"));
        this.ge.addRoll(Roll.fromString("María\t10"));
        this.ge.addRoll(Roll.fromString("María\t10"));
        this.ge.addRoll(Roll.fromString("María\t10"));
        this.ge.addRoll(Roll.fromString("María\t5"));
        this.ge.addRoll(Roll.fromString("John\t1"));
        this.ge.addRoll(Roll.fromString("John\t2"));
        this.ge.addRoll(Roll.fromString("John\tF"));

        String expected =   "Frame	1		2		3		4		5		6		7		8		9		10\n"+
                            "John\n"+
                            "Pinfalls		X	7	/		X	1	2	F\n"+
                            "Score	30		47		47		33		\n"+
                            "María\n"+
                            "Pinfalls		X		X		X	5\n"+
                            "Score	30		60		60		";
        String actual = this.ge.scoreBoard.toString();

        assertEquals(expected, actual);
    }
}
