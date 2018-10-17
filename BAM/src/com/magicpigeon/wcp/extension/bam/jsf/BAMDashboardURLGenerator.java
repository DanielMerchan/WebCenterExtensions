package com.magicpigeon.wcp.extension.bam.jsf;

import com.magicpigeon.wcp.extension.bam.util.BAMLogger;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


/**
 * Manage Bean for generating the BAM Dashboard URL
 * @author Daniel Merchan Garcia - Magic Pigeon Ltd
 */
public final class BAMDashboardURLGenerator implements Serializable {

    /**
     * Serialization ID
     */
    @SuppressWarnings("compatibility:-643972069486880128")
    private static final long serialVersionUID = 5628103740992942362L;
    
    /**
     * BAM Proxy Page for loading Dashboards
     */
    private static final String BAM_PROXY_PAGE = "bam/composer/faces/proxypage";
    
    // Outcomes
    private static final String SHOW_DASHBOARD_OUTCOME = "showDashboard";
    private static final String ERROR_OUTCOME = "toError";
    private static final String INPUT_PARAMETERS_ERROR_OUTCOME = "toConfigError";
    
    /**
     * Logging
     */
    private static ADFLogger LOGGER = BAMLogger.getLogger();;
    
    /**
     * Class Name
     */
    private static String CLASS_NAME = BAMDashboardURLGenerator.class.getName(); 

    /*
     * Generated URL for loading BAM Dashboards
     */
    private String generatedURL;

    /**
     * Method Call used for calculating the BAM Dashboard URL and navigate to the view
     * @return
     */
    public String generateURL() {
        final String METHOD_NAME = "generateUrl";
        LOGGER.entering(CLASS_NAME, METHOD_NAME);
        String result = BAMDashboardURLGenerator.SHOW_DASHBOARD_OUTCOME;
        // FIXME: Remove the dependency with BAM Connection
//        String url = null;
//        StringBuilder urlBuilder = new StringBuilder();
//        BAMTaskFlowParameters bamTaskFlowParameters =
//            BAMTaskFlowHelper.getValueExpression("#{pageFlowScope.bamTaskFlowParameters}",
//                                                 BAMTaskFlowParameters.class);
//        if (bamTaskFlowParameters != null) {
//            if (LOGGER.isFinest()) {
//                LOGGER.finest("BAM Task Flow Parameters");
//                LOGGER.finest(bamTaskFlowParameters.toString());
//            }
//            String bamConnectionName = bamTaskFlowParameters.getBamConnectionName();
//            try {
//                if ((bamConnectionName != null) && (!bamConnectionName.isEmpty())) {
//                    BAMProviderImpl mBAMConnection = BAMTaskFlowHelper.GetConnection(bamConnectionName);
//                    if (mBAMConnection != null) {
//                        url = BAMTaskFlowHelper.generateURLFromBAMConn(mBAMConnection);
//                    }
//                }
//            } catch (NamingException e) {
//                LOGGER.logp(ADFLogger.INTERNAL_ERROR, CLASS_NAME, METHOD_NAME, BAMLibraryBundle.WCP_EXTENSION_BAM_CONNECTION_ERROR, new Object[]{bamConnectionName},e);
//                result = BAMDashboardURLGenerator.ERROR_OUTCOME;
//                BAMTaskFlowHelper.addMessageToFacesContext(BAMLibraryBundle.WCP_EXTENSION_BAM_CONNECTION_ERROR, FacesMessage.SEVERITY_ERROR);
//            }
//
//            if (url != null) {
//                url = url + "/";
//                urlBuilder.append(url + BAM_PROXY_PAGE);
//            }
//            if (!urlBuilder.toString().isEmpty()) {
//                try {
//                    urlBuilder.append("?project=");
//                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getProject(), "UTF-8") + "&");
//                    urlBuilder.append("dashboard=");
//                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getDashboard(), "UTF-8") + "&");
//                    urlBuilder.append("DashboardParameters=(");
//                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getDashboardParameters(), "UTF-8"));
//                    urlBuilder.append(")");
//                } catch (UnsupportedEncodingException e) {
//                    LOGGER.logp(ADFLogger.WARNING, CLASS_NAME, METHOD_NAME, BAMLibraryBundle.WCP_EXTENSION_BAM_UNSUPPORTED_ENCONDING_PARAM, new Object[]{bamConnectionName},e);
//                    result = BAMDashboardURLGenerator.INPUT_PARAMETERS_ERROR_OUTCOME;
//                    BAMTaskFlowHelper.addMessageToFacesContext(BAMLibraryBundle.WCP_EXTENSION_BAM_UNSUPPORTED_ENCONDING_PARAM, FacesMessage.SEVERITY_WARN);
//                }
//
//            }
//        }
//        setGeneratedURL(urlBuilder.toString());
        return result;
    }
    
    // Setters and Getters
    
    /**
     * Set the Generated URL from the BAM Connection
     * @param generatedURL - Generated URL 
     */
    public void setGeneratedURL(String generatedURL) {
        this.generatedURL = generatedURL;
    }

    /**
     * Get the Generated URL from the BAM Connection
     * @return String
     */
    public String getGeneratedURL() {
        return this.generatedURL;
    }
}