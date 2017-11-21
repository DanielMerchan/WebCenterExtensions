package com.magicpigeon.wcp.extension.bam.jsf;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import oracle.adf.share.logging.ADFLogger;

import oracle.tip.tools.ide.bam.rc.connection.BAMProviderImpl;

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
    private static final String SHOW_DASHBOARD = "showDashboard";
    
    /**
     * Logging
     */
    private static ADFLogger LOGGER = ADFLogger.createADFLogger(BAMDashboardURLGenerator.class);

    /*
     * Generated URL for loading BAM Dashboards
     */
    private String generatedURL;

    /**
     * Method Call used for calculating the BAM Dashboard URL and navigate to the view
     * @return
     */
    public String generateURL() {
        
        String result = BAMDashboardURLGenerator.SHOW_DASHBOARD;
        String url = null;
        StringBuilder urlBuilder = new StringBuilder();
        BAMTaskFlowParameters bamTaskFlowParameters =
            BAMTaskFlowHelper.getValueExpression("#{pageFlowScope.bamTaskFlowParameters}",
                                                 BAMTaskFlowParameters.class);
        if (bamTaskFlowParameters != null) {
            if (LOGGER.isFinest()) {
                LOGGER.finest("BAM Task Flow Parameters");
                LOGGER.finest(bamTaskFlowParameters.toString());
            }
            try {
                String bamConnectionName = bamTaskFlowParameters.getBamConnectionName();
                if ((bamConnectionName != null) && (!bamConnectionName.isEmpty())) {
                    BAMProviderImpl mBAMConnection = BAMTaskFlowHelper.GetConnection(bamConnectionName);
                    if (mBAMConnection != null) {
                        url = BAMTaskFlowHelper.generateURLFromBAMConn(mBAMConnection);
                    }
                }
            } catch (Exception e) {
                LOGGER.warning(e);
                result = "error";
                BAMTaskFlowHelper.addErrorMessageToFacesContext();
            }

            if (url != null) {
                url = url + "/";
                urlBuilder.append(url + BAM_PROXY_PAGE);
            }
            if (!urlBuilder.toString().isEmpty()) {
                StringBuilder parameterBuilder = new StringBuilder();
                try {
                    urlBuilder.append("?project=");
                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getProject(), "UTF-8") + "&");
                    urlBuilder.append("dashboard=");
                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getDashboard(), "UTF-8") + "&");
                    urlBuilder.append("DashboardParameters=(");
                    urlBuilder.append(URLEncoder.encode(bamTaskFlowParameters.getDashboardParameters(), "UTF-8"));
                    urlBuilder.append(")");
                } catch (UnsupportedEncodingException e) {
                }

            }
        }
        setGeneratedURL(urlBuilder.toString());
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