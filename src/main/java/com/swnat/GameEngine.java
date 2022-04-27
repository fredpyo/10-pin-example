package com.swnat;

import java.util.List;
import java.util.Optional;

public class GameEngine {
    ScoreCalculator scoreCalculator;
    ScoreBoard scoreBoard;

    GameEngine(ScoreCalculator sc) {
        this.scoreBoard = new ScoreBoard();
        this.scoreCalculator = sc;
    }

    void addRoll(Roll roll) {
        Optional<Frame> lastFrame = this.getLastFrameForPlayer(roll.getPlayer());
        List<Frame> playerFrames = scoreBoard.getPlayerFrames(roll.getPlayer());

        if (!lastFrame.isPresent() || (lastFrame.isPresent() && !lastFrame.get().canAddRoll())) {
            lastFrame = Optional.of(new Frame());
            this.addFrame(roll.getPlayer(), lastFrame.get());
        }

        lastFrame.get().addRoll(roll);

        this.scoreCalculator.calculate(playerFrames);
    }

    // using optionals for fun!
    Optional<Frame> getLastFrameForPlayer(String playerName) {
        List<Frame> playerFrames = scoreBoard.getPlayerFrames(playerName);
        if (playerFrames.size() > 0)
            return Optional.of(playerFrames.get(playerFrames.size() - 1));
        return Optional.empty();
    }

    void addFrame(String playerName, Frame frame) {
        List<Frame> playerFrames = scoreBoard.getPlayerFrames(playerName);
        if (playerFrames.size() == this.scoreCalculator.getMaxFrames())
            throw new RuntimeException("Can't add new frames");
        if (playerFrames.size() == this.scoreCalculator.getMaxFrames() - 1)
            frame.isLastFrame = true;
        playerFrames.add(frame);
    }


}
