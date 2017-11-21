package com.magicpigeon.wcp.extension.bam.jsf;

import javax.el.ELContext;

import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.NamingException;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.tip.tools.ide.bam.rc.connection.BAMProviderImpl;

import org.apache.commons.lang.StringUtils;

/**
 * Utility class used by the BAM Task Flows
 * @author Daniel Merchan Garcia & Magic Pigeon Ltd
 */
public final class BAMTaskFlowHelper {
    
    /**
     * Default Constructor
     */
    public BAMTaskFlowHelper() {
        super();
    }

    /**
     * Evaluate an EL Expression
     * @param <T> Managed Bean class to be Returned
     * @param expression - EL Expression to be evaluated
     * @param objClass - Return Object
     * @return T
     */
    public static <T> T getValueExpression(String expression, Class<T> objClass) {
        if (!expression.startsWith("#{")) {
            expression = "#{" + expression + "}";
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            return null;
        }
        ELContext elContext = facesContext.getELContext();
        ValueExpression ve = facesContext.getApplication()
                                         .getExpressionFactory()
                                         .createValueExpression(elContext, expression, objClass);
        return (T)ve.getValue(elContext);
    }

    /**
     * Returns a BAM Connection fromt the MBean Server
     * @param connectionName - BAM Connection Name
     * @return - BAMProviderImpl
     * @throws NamingException
     */
    public static BAMProviderImpl GetConnection(String connectionName) throws NamingException {
        // FIXME: Try to find an alternative for retrieving the BAMConnection from MBean
        Context ctx = ADFContext.getCurrent().getConnectionsContext();
        BAMProviderImpl bamProvider = (BAMProviderImpl) ctx.lookup(connectionName);;
        return bamProvider;
    }

    /**
     * Display an error when the Input Parameters of the Task Flow has been setup unproperly
     */
    public static void addErrorMessageToFacesContext() {
        // FIXME: Improve the Error showing + multilanguage
        FacesContext fct = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary("Error Occurred Please contact the administartor");
        message.setSeverity(FacesMessage.SEVERITY_FATAL);
        fct.addMessage(null, message);
    }

    /**
     * Generates the BAM URL based on the BAM Connection
     * If WebServer Host/Port configured then it is used, if not then it uses the BAM Host/Port
     * @param mBAMConnection - BAM Connection
     * @return String
     */
    public static String generateURLFromBAMConn(BAMProviderImpl mBAMConnection) {
        System.out.println(mBAMConnection.toString());
        System.out.println(mBAMConnection.getBAMConnectionMode());
        System.out.println(mBAMConnection.getBAMServerHost());
        System.out.println(mBAMConnection.getBAMServerPort());
        System.out.println(mBAMConnection.getBAMWsilRootUrl());
        System.out.println(mBAMConnection.getWebTierHost());
        System.out.println(mBAMConnection.getWebTierHostPort());
        System.out.println(mBAMConnection.getWebTierProtocol());
//        StringBuilder url = new StringBuilder(StringUtils.EMPTY);
//        // 1. URL using the Front (Web Tier or LBR)
//        final String webTierProtocol = mBAMConnection.getWebTierProtocol();
//        final String webTierHost = mBAMConnection.getWebTierHost();
//        int webTierHostPort = mBAMConnection.getWebTierHostPort();
//        // 2. URL directly to the WL Cluster / Managed Server
//        mBAMConnection.get
//        if (StringUtils.isNotEmpty(webTierProtocol) && StringUtils.isNotEmpty(webTierHost)) {
//            url.append(webTierProtocol);
//            url.append("://");
//            url.append(webTierHost);
//            url.append(":");
//            url.append(String.valueOf(webTierHostPort));
//        } else if (StringUtils.isNotEmpty(arg0))
        return "http://wcp12c:7004";
                                                        
    }
}
