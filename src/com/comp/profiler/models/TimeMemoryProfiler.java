package com.comp.profiler.models;

import java.util.UUID;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class TimeMemoryProfiler {

    private static final String NO_PROFILE = "There is no profile with key '%s'";

    private Hashtable<String, TimeMemoryProfile> profiles = new Hashtable<>();

    public String start() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (profiles.get(uuid) != null);
        start(uuid);

        return uuid;
    }

    public void start(String key) {
        profiles.put(key, new TimeMemoryProfile());
    }

    public TimeMemoryProfile lap(String key) throws NoSuchElementException {
        TimeMemoryProfile timeMemoryProfile = profiles.get(key);
        if (timeMemoryProfile == null) {
            throw new NoSuchElementException(String.format(NO_PROFILE, key));
        }
        timeMemoryProfile.computeDeltas();

        return timeMemoryProfile;
    }

    public TimeMemoryProfile stop(String key) throws NoSuchElementException {
        TimeMemoryProfile timeMemoryProfile = lap(key);
        profiles.remove(key);

        return timeMemoryProfile;
    }
}

