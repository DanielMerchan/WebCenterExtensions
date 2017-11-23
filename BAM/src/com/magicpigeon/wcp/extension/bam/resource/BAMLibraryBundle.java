package com.magicpigeon.wcp.extension.bam.resource;

import java.util.ListResourceBundle;

/**
 * Holds the Translations for the BAM Extension
 * @author Daniel Merchan Garcia - Magic Pigeon Ltd
 */
public final class BAMLibraryBundle extends ListResourceBundle {

    /**
     * Get the configured translations
     * @return
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }

    /**
     * Translation Map
     */
    static Object[][] contents = {
        { "WCP_EXTENSION_BAM_CONNECTION_ERROR", "Can not connect to BAM using BAM Connection Name {0}. Please, check your BAM Connection settings." },
        { "WCP_EXTENSION_BAM_UNSUPPORTED_ENCONDING_PARAM", "An error has occurred when encoding the Input Parameters. Double check the configuration."},
        { "WCP_EXTENSION_BAM_USER_FRIENDLY_ERROR", "BAM Dashboard cannot be displayed, please contact an Admistrator."}
    };
    
    // Direct Constant Access to the Messages
    public static final String WCP_EXTENSION_BAM_CONNECTION_ERROR = "WCP_EXTENSION_BAM_CONNECTION_ERROR";
    public static final String WCP_EXTENSION_BAM_UNSUPPORTED_ENCONDING_PARAM = "WCP_EXTENSION_BAM_UNSUPPORTED_ENCONDING_PARAM";
    public static final String WCP_EXTENSION_BAM_USER_FRIENDLY_ERROR = "WCP_EXTENSION_BAM_USER_FRIENDLY_ERROR";

}
