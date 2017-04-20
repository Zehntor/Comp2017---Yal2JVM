package com.comp.utils.services;

import com.comp.utils.models.Utils;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class UtilsService {

    /**
     * The one and only instance of this class
     */
    private static final UtilsService instance = new UtilsService();

    private static final Utils utils = new Utils();

    private UtilsService() {
    }

    public static UtilsService getInstance() {
        return instance;
    }

    public String formatBytes(long bytes) {
        return utils.formatBytes(bytes);
    }
}
