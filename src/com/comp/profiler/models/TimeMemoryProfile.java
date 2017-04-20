package com.comp.profiler.models;

import com.comp.utils.services.TimeService;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class TimeMemoryProfile {

    private double startTime;
    private double rawTime;
    private String humanTime;
    private long totalMemory;

    public TimeMemoryProfile() {
        startTime = System.currentTimeMillis() / 1e3;
    }

    public TimeMemoryProfile setStartTime(double startTime) {
        this.startTime = startTime;
        return this;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getRawTime() {
        return rawTime;
    }

    public String getHumanTime() {
        return humanTime;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public TimeMemoryProfile computeDeltas() {
        rawTime     = System.currentTimeMillis() / 1e3 - startTime;
        humanTime   = TimeService.getInstance().getHumanReadableTime(rawTime);
        totalMemory = Runtime.getRuntime().totalMemory();

        return this;
    }
}
