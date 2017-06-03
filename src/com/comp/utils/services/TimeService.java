package com.comp.utils.services;

import com.comp.utils.models.Time;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class TimeService {

    /**
     * The one and only instance of this class
     */
    private static final TimeService instance = new TimeService();

    private static final Time timeModel = new Time();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private TimeService() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static TimeService getInstance() {
        return instance;
    }

    /**
     * Returns a human readable time string
     * @param time
     * @return String
     */
    public String getHumanReadableTime(double time) {
        return timeModel.getHumanReadableTime(time);
    }
}
