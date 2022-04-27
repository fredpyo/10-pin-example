package com.swnat;

import java.util.List;

public interface ScoreCalculator {

    public void calculate(List<Frame> frames);

    public Integer getMaxFrames();

}
