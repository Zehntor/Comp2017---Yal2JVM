package com.comp.utils.models;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class Time {

    private static final long ONE_DAY    = 86400;
    private static final long ONE_HOUR   = 3600;
    private static final long ONE_MINUTE = 60;

    /**
     * Returns a human readable time string
     * @param time
     * @return String
     */
    public String getHumanReadableTime(double time) {
        double d = 0;
        while (time >= ONE_DAY) {
            time -= ONE_DAY;
            d++;
        }
        double h = 0;
        while (time >= ONE_HOUR) {
            time -= ONE_HOUR;
            h++;
        }
        double m = 0;
        while (time >= ONE_MINUTE) {
            time -= ONE_MINUTE;
            m++;
        }
        double s = new Utils().roundTo(time, 3);

        String sStr = getFormattedTimeComponent(s, "second");

        if (d != 0) {
            return getHumanReadableTimeStartingWithDays(d, h, m, s);
        }
        if (h != 0) {
            return getHumanReadableTimeStartingWithHours(h, m, s);
        }
        if (m != 0) {
            return getHumanReadableTimeStartingWithMinutes(m, s);
        }
        if (s < 1) {
            double ms    = Math.round(s * 1000);
            String msStr = getFormattedMillisecondTimeComponent((int) ms);
            return msStr;
        }

        return sStr;
    }

    private String getHumanReadableTimeStartingWithDays(double d, double h, double m, double s) {
        String
            dStr = getFormattedTimeComponent(d, "day"),
            hStr = getFormattedTimeComponent(h, "hour"),
            mStr = getFormattedTimeComponent(m, "minute"),
            sStr = getFormattedTimeComponent(s, "second");

        if (m != 0) {
            if (s != 0) {
                return String.format("%s, %s, %s and %s", dStr, hStr, mStr, sStr);
            }
            return String.format("%s, %s and %s", dStr, hStr, mStr);
        }
        if (s != 0) {
            return String.format("%s, %s, %s and %s", dStr, hStr, mStr, sStr);
        }

        return hStr;
    }

    private String getHumanReadableTimeStartingWithHours(double h, double m, double s) {
        String
            hStr = getFormattedTimeComponent(h, "hour"),
            mStr = getFormattedTimeComponent(m, "minute"),
            sStr = getFormattedTimeComponent(s, "second");

        if (m != 0) {
            if (s != 0) {
                return String.format("%s, %s and %s", hStr, mStr, sStr);
            }
            return String.format("%s and %s", hStr, mStr);
        }
        if (s != 0) {
            return String.format("%s, %s and %s", hStr, mStr, sStr);
        }

        return hStr;
    }

    private String getHumanReadableTimeStartingWithMinutes(double m, double s) {
        String
            mStr = getFormattedTimeComponent(m, "minute"),
            sStr = getFormattedTimeComponent(s, "second");

        if (s != 0) {
            return String.format("%s and %s", mStr, sStr);
        }

        return mStr;
    }

    private String getFormattedTimeComponent(double time, String componentName) {
        String timeComponent;
        if (time == 0) {
            timeComponent = String.format("0 %ss", componentName);
        } else if (time == 1) {
            timeComponent = String.format("1 %s", componentName);
        } else {
            timeComponent = String.format("%.3f %ss", time, componentName);
        }

        return timeComponent;
    }

    private String getFormattedMillisecondTimeComponent(int time) {
        String timeComponent;
        if (time == 0) {
            timeComponent = "0 milliseconds";
        } else if (time == 1) {
            timeComponent = "1 millisecond";
        } else {
            timeComponent = String.format("%d milliseconds", time);
        }

        return timeComponent;
    }
}
