package com.magicpigeon.wcp.extension.bam.util;


import oracle.adf.share.logging.ADFLogger;

/**
 * Wrapper for getting the ADFLogger for the BAM Extension
 * @author Daniel Merchan Garcia - Magic Pigeon Ltd
 */
public final class BAMLogger {
    
    /**
     * Logger Base Name
     */
    private static final String BAM_LOGGER = "com.magicpigeon.wcp.bam";
    
    /**
     * Return the multilanguage logger for BAM Extension
     * @param cls
     * @return ADFLogger
     */
    public static final ADFLogger getLogger() {
        return ADFLogger.createADFLogger(BAM_LOGGER, BAMTaskFlowHelper.BAM_EXTENSION_BASE_BUNDLE_NAME);
    }
}
