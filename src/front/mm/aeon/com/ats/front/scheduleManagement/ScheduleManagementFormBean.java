/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi$
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.scheduleManagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("scheduleManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class ScheduleManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private ScheduleManagementBean registerHeaderBean;

    private ScheduleManagementBean backUpHeaderBean;

    private boolean isUpdate;

    private boolean isValidStatusUpdate;

    private boolean isError = false;

    @Begin(join = true)
    @Action
    public String init() {
        init = false;
        isUpdate = false;
        this.getMessageContainer().clearAllMessages((null == this.doReload || !this.doReload));
        doReload = false;
        this.backUpHeaderBean = new ScheduleManagementBean().copyScheduleManagementBean(this.registerHeaderBean);

        return LinkTarget.OK;
    }

    @Action
    public String update() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        }
        this.init = true;
        this.isUpdate = true;
        doReload = new Boolean(true);
        registerHeaderBean = new ScheduleManagementBean();
        return LinkTarget.OK;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.registerHeaderBean = null;

        return LinkTarget.SEARCH;
    }

    public void reset() {
        this.registerHeaderBean = new ScheduleManagementBean().copyScheduleManagementBean(backUpHeaderBean);
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public ScheduleManagementBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(ScheduleManagementBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isValidStatusUpdate() {
        return isValidStatusUpdate;
    }

    public void setValidStatusUpdate(boolean isValidStatusUpdate) {
        this.isValidStatusUpdate = isValidStatusUpdate;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public ScheduleManagementBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(ScheduleManagementBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

}
