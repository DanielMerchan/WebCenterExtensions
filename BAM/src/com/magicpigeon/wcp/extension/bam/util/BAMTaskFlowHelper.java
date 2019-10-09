package com.magicpigeon.wcp.extension.bam.util;

import com.magicpigeon.wcp.extension.bam.resource.BAMLibraryBundle;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.el.ELContext;

import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.NamingException;

import oracle.adf.share.ADFContext;

import oracle.tip.tools.ide.bam.rc.connection.BAMProviderImpl;

import org.apache.commons.lang.StringUtils;

/**
 * Utility class used by the BAM Task Flows
 * @author Daniel Merchan Garcia & Magic Pigeon Ltd
 */
public final class BAMTaskFlowHelper {
    
    /**
     * Base Resource Bundle for BAM Extension
     */
    public static final String BAM_EXTENSION_BASE_BUNDLE_NAME = BAMLibraryBundle.class.getName();
    
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
        Context ctx = ADFContext.getCurrent().getConnectionsContext();
        BAMProviderImpl bamProvider = (BAMProviderImpl) ctx.lookup(connectionName);;
        return bamProvider;
    }
    
    /**
     * Retrieves the current locale loaded in the ADF Context
     * @return Locale
     */
    private static final Locale getLocale() {
        Locale currLocale = ADFContext.getCurrent().getLocale();
        if (currLocale == null) {
          currLocale = Locale.getDefault();
        }
        return currLocale;
    }
    
    /**
     * Get the Resource Bundle Message base on the provided message key
     * @param messageKey - Message Key of the Resource Bundle
     */
    public static final String getResourceString (String messageKey) {
        ResourceBundle bamResBundle = ResourceBundle.getBundle(BAM_EXTENSION_BASE_BUNDLE_NAME,getLocale(),Thread.currentThread().getContextClassLoader());
        return bamResBundle.getString(messageKey);
    }
 
    /**
     * Display an error when the Input Parameters of the Task Flow has been setup unproperly
     * @param messageKey - Message to display in the Faces Message
     * @param severity - Severity of the Message
     */
    public static void addMessageToFacesContext(String messageKey, FacesMessage.Severity severity) {
        FacesContext fct = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(getResourceString(messageKey));
        message.setSeverity(severity);
        fct.addMessage(null, message);
    }

    /**
     * Generates the BAM URL based on the BAM Connection
     * If WebServer Host/Port configured then it is used, if not then it uses the BAM Host/Port
     * @param mBAMConnection - BAM Connection
     * @return String
     */
    // FIXME: Remove the dependency with BAM Connection
    public static String generateURLFromBAMConn(BAMProviderImpl mBAMConnection) {
        StringBuilder url = new StringBuilder(StringUtils.EMPTY);
        final String webTierProtocol = mBAMConnection.getWebTierProtocol();
        url.append(webTierProtocol);
        url.append("://");
        // 1. URL using the Front (Web Tier or LBR)
        final String webTierHost = mBAMConnection.getWebTierHost();
        int webTierHostPort = mBAMConnection.getWebTierHostPort();
        final String bamServerHost = mBAMConnection.getBAMServerHost();
        final String bamServerPort = mBAMConnection.getBAMServerPort();
        // 2. URL directly to the WL Cluster / Managed Server
        if (StringUtils.isNotEmpty(webTierHost)) {
            url.append(webTierHost);
            url.append(":");
            url.append(String.valueOf(webTierHostPort));
        } else if (StringUtils.isNotEmpty(bamServerHost)) {
            url.append(bamServerHost);
            url.append(":");
            url.append(String.valueOf(bamServerPort));
        }
        return url.toString();                                       
    }
}
