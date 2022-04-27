package com.swnat;

import java.util.List;

public class ChampionshipScoreCalculator implements ScoreCalculator {

    public void calculate(List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).getScore() == null) {
                Integer frameScore;
                if (frames.get(i).rolls.get(0).getPinsThrown() == 10) {
                    frameScore = 30;
                } else if (frames.get(i).rolls.size() == 2) {
                    frameScore = frames.get(i).rolls.stream().map(c -> c.pinsThrown).reduce(0, Integer::sum);
                    if (frameScore == 10) {
                        frameScore += frames.get(i).rolls.get(0).getPinsThrown();
                    }
                } else {
                    frameScore = null;
                }
                frames.get(i).setScore(frameScore);
                if (frameScore != null) {
                    Integer accumulatedScore = frameScore;
                    if (i > 0) {
                        accumulatedScore += frames.get(i-1).getScore();
                    }
                    frames.get(i).setAccumulatedScore(accumulatedScore);
                }
            }
        }
    }

    public Integer getMaxFrames() {
        return 10;
    }
}
