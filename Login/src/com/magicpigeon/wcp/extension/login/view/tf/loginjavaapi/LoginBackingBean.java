package com.magicpigeon.wcp.extension.login.view.tf.loginjavaapi;

import java.io.IOException;

import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.webcenter.framework.service.AnalyticsUtil;

import oracle.webcenter.framework.service.Utility;

/**
 * This Backing Bean performs an authentiaction using J2EE Servlet Authentication
 * Note: WebLogic Authentication methods has been deprecated and J2EE methods are suggested
 * @author Daniel Merchan Garcia
 */
public final class LoginBackingBean {

    /**
     * Holds the Login Form User Name
     */
    private String mUserName;
    
    /**
     * Holds the Login Form Password
     */
    private String mPassword;

    /**
     * Logger
     */
    private static final ADFLogger logger = ADFLogger.createADFLogger(LoginBackingBean.class);

    /**
     * Default Contructor
     */
    public LoginBackingBean() {
        super();
    }

    /**
     * Action fired when the Login Button is clicked
     * @param ae
     */
    public String doLogin() throws IOException {
        HttpServletRequest req = (HttpServletRequest) ADFContext.getCurrent()
                                                                .getEnvironment()
                                                                .getRequest();

        HttpServletResponse res = (HttpServletResponse) ADFContext.getCurrent()
                                                                  .getEnvironment()
                                                                  .getResponse();
        int loginOutcome = 1;
        try {
            req.login(mUserName, mPassword);
            loginOutcome = req.getRemoteUser() == null ? 1 : 0;
        } catch (ServletException ex) {
            loginOutcome = 1;
            if (logger.isFiner()) {
                logger.logp(Level.FINER, getClass().getName(), "handleLogin", "Login failed");
                logger.finer(ex);
            }
        }

        // Integration with WebCenter Analytics because is not using wcAuthentication servlet
        if (0 == loginOutcome) {
            if (AnalyticsUtil.isSendingEvents()) {
                Utility.sendUserDetails(this.mUserName, req);
            }    
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            FacesContext.getCurrentInstance().renderResponse();
        } else {
            FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", "You are a noob and you could not login properly");
            FacesContext fCtx = FacesContext.getCurrentInstance();
            fCtx.addMessage(null, fMsg);
        }
        return "";
    }

    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getMUserName() {
        return mUserName;
    }

    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getMPassword() {
        return mPassword;
    }
}
