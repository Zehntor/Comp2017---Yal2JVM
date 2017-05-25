package com.comp.profiler.services;

import com.comp.profiler.models.TimeMemoryProfile;
import com.comp.profiler.models.TimeMemoryProfiler;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Singleton
 */
public class TimeMemoryProfilerService {

    /**
     * The one and only instance of this class
     */
    private static final TimeMemoryProfilerService instance = new TimeMemoryProfilerService();

    /**
     * The TimeMemoryProfiler
     */
    private static final TimeMemoryProfiler timeMemoryProfiler = new TimeMemoryProfiler();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private TimeMemoryProfilerService() {
    }

    /**
     * Returns the one and only instance of this class
     * @return TimeMemoryProfilerService
     */
    public static TimeMemoryProfilerService getInstance() {
        return instance;
    }

    /**
     * Starts profiling
     * Returns a profile key for later retrieval
     * @return String
     */
    public String start() {
        return timeMemoryProfiler.start();
    }

    /**
     * Starts profiling
     * @param key
     */
    public void start(String key) {
        timeMemoryProfiler.start(key);
    }

    /**
     * Returns a TimeMemoryProfile without stopping the profiling
     * @param key
     * @return
     */
    public TimeMemoryProfile lap(String key) {
        return timeMemoryProfiler.lap(key);
    }

    /**
     * Stops profiling and returns a TimeMemoryProfile
     * @param key
     * @return
     */
    public TimeMemoryProfile stop(String key) {
        return timeMemoryProfiler.stop(key);
    }
}
