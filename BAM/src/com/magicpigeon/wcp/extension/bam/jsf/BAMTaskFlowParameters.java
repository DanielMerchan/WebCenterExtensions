package com.magicpigeon.wcp.extension.bam.jsf;

import java.io.Serializable;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Class holding the Task Flow Input Parameters
 * @author Daniel Merchan Garcia - Magic Pigeon Ltd
 */
public class BAMTaskFlowParameters implements Serializable {
    
    /**
     * Serializaiton ID
     */
    @SuppressWarnings("compatibility:6144401758359646331")
    private static final long serialVersionUID = -1289680940433158468L;

    /**
     * BAM Connection Name used for loading the details from the ADF Connections MXBean
     */
    private String bamConnectionName;
    
    /**
     * Project Name
     * Example: ProcessAnalytics_v2
     */
    private String project;
    
    /**
     * Dashbaord Name
     * Example: CycleTime
     */
    private String dashboard;
    
    /**
     * Dashboard Parameters - Without the brackets
     * Example: ProcessSelection=[ALL];TimePeriodInDays=30;ShowTopN=10;
     */
    private String dashboardParameters;
    
    /**
     * Default Constructor
     */
    public BAMTaskFlowParameters() {
        super();
    }

    /**
     * Set the BAM Connection Name
     * @param bamConnectionName - BAM Connection Name
     */
    public void setBamConnectionName(String bamConnectionName) {
        this.bamConnectionName = bamConnectionName;
    }

    /**
     * Get the configured BAM Connection Name
     * @return String
     */
    public String getBamConnectionName() {
        return bamConnectionName;
    }

    /**
     * Set the BAM Project Name the Dashboard belongs to
     * @param project - BAM Project Name
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Get the BAM Project Name the Dashboard belongs to
     * @return String
     */
    public String getProject() {
        return project;
    }

    /**
     * Set the BAM Dashboard Name
     * @param dashboard - Dashboard Name
     */
    public void setDashboard(String dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * Get the BAM Dashboard Name
     * @return - String
     */
    public String getDashboard() {
        return dashboard;
    }

    /**
     * Set the Dashboard Parameters
     * @param dashboardParameters
     */
    public void setDashboardParameters(String dashboardParameters) {
        this.dashboardParameters = dashboardParameters;
    }

    /**
     * Get the Dashboard Parameters
     * @return dashboardPameters
     */
    public String getDashboardParameters() {
        return dashboardParameters;
    }
    
    /**
     * To String
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
    }
}