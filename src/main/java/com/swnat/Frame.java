package com.swnat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    List<Roll> rolls = new ArrayList<>();
    Integer score = null;
    Integer accumulatedScore = null;
    boolean isLastFrame = false;

    public Frame() {

    }

    public boolean canAddRoll() {
        // TODO is does not apply to everyone :/
        if (this.isLastFrame && this.rolls.size() > 0 && this.rolls.size() < 3 && this.rolls.get(0).getPinsThrown() == 10) return true;
        if (this.rolls.size() == 2 ||
            (this.rolls.size() > 0 && this.rolls.get(0).getPinsThrown() == 10)
        ) return false;

        return true;
    }

    public void addRoll(Roll roll) {
        if (!this.validateRoll(roll))
            throw new RuntimeException("Invalid roll, must be same player");
        if (!this.isLastFrame  && this.rolls.stream().map(Roll::getPinsThrown).reduce(roll.getPinsThrown(), Integer::sum) > 10)
            throw new RuntimeException("Invalid roll");
        rolls.add(roll);
    }

    public boolean validateRoll(Roll roll) {
        return roll.player != null && !"".equals(roll.player);

    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer getAccumulatedScore() {
        return this.accumulatedScore;
    }

    public void setAccumulatedScore(Integer accumulatedScore) {
        this.accumulatedScore = accumulatedScore;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0).pinsThrown == 10;
    }

    public boolean isSpare() {
        return rolls.size() > 1 && rolls.stream().map(Roll::getPinsThrown).reduce(0, Integer::sum) == 10;
    }

    public boolean isOpen() {
        return rolls.stream().map(Roll::getPinsThrown).reduce(0, Integer::sum) < 10;
    }

    public String toString() {
        if (this.isStrike())
            return "\tX";
        if (this.isSpare())
            return this.rolls.get(0).toString() + "\t/";
        return this.rolls.stream().map(Roll::toString).collect(Collectors.joining("\t"));
    }
}
