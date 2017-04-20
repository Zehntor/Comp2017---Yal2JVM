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

    private TimeService() {
    }

    public static TimeService getInstance() {
        return instance;
    }

    public String getHumanReadableTime(double time) {
        return timeModel.getHumanReadableTime(time);
    }
}
