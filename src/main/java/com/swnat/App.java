package com.swnat;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        GameEngine ge = new GameEngine(new ChampionshipScoreCalculator());
        FileParser fp = new FileParser();

        List<String> rollStrings = fp.readFile(args[0]);
        List<Roll> rolls = rollStrings.stream().map(Roll::fromString).collect(Collectors.toList());

        rolls.forEach(c -> ge.addRoll(c));

        System.out.println(ge.scoreBoard);
    }
}
