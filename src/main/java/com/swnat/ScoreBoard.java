package com.swnat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreBoard {
    Integer totalNumberOfFrames = 10;
    Map<String, List<Frame>> frames = new HashMap<>();

    List<Frame> getPlayerFrames(String playerName) {
        List<Frame> playerFrames = frames.getOrDefault(playerName, new ArrayList<>());
        frames.put(playerName, playerFrames);
        return playerFrames;
    }

    public String toString() {
        String frameString = "Frame\t" + IntStream.range(1, totalNumberOfFrames + 1)
            .boxed()
            .map(i -> Integer.toString(i))
            .collect(Collectors.joining("\t\t"));

        frameString  += "\n" + this.frames.entrySet().stream()
            .map(nameFrameSet ->
                nameFrameSet.getKey() + "\n" +
                "Pinfalls\t" + nameFrameSet.getValue().stream()
                    .map(Frame::toString).collect(Collectors.joining("\t")) + "\n" +
                "Score\t" + nameFrameSet.getValue().stream()
                    .map(Frame::getAccumulatedScore).map(s -> s == null ? "" : Integer.toString(s)).collect(Collectors.joining("\t\t"))
            ).collect(Collectors.joining("\n"));

        return frameString;
    }
}
