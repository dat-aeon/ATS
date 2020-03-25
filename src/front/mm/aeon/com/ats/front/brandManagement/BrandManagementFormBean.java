/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brandManagement;

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

@Name("brandManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class BrandManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -8891317664630385651L;

    @In(required = false, value = "adminUpdateParam")
    @Out(required = false, value = "adminUpdateParam")
    private BrandManagementHeaderBean updateParam;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private BrandManagementHeaderBean registerHeaderBean;

    private BrandManagementHeaderBean backUpHeaderBean;

    private boolean isUpdate;

    private boolean isValidStatusUpdate;

    private boolean isError = false;

    private boolean cancel = false;

    @Begin(join = true)
    public void init() {
        init = false;
        isUpdate = false;
        cancel = true;
        this.getMessageContainer().clearAllMessages(true);
        registerHeaderBean = new BrandManagementHeaderBean();
    }

    @Begin(join = true)
    public void updateInit() {
        this.getMessageContainer().clearAllMessages(true);

        init = false;
        isUpdate = true;
        cancel = false;
        registerHeaderBean = new BrandManagementHeaderBean();
        this.registerHeaderBean.setBrandCode(updateParam.getBrandCode());
        this.registerHeaderBean.setBrandName(updateParam.getBrandName());
        this.registerHeaderBean.setUpdatedTime(updateParam.getUpdatedTime());

        this.backUpHeaderBean = new BrandManagementHeaderBean().copyBrandManagementHeaderBean(this.registerHeaderBean);
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            registerHeaderBean = new BrandManagementHeaderBean();
            init = true;
            cancel = true;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        registerHeaderBean = new BrandManagementHeaderBean();
        return LinkTarget.OK;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        this.registerHeaderBean = null;

        return LinkTarget.SEARCH;
    }

    public void clear() {
        this.registerHeaderBean = new BrandManagementHeaderBean();
    }

    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyBrandManagementHeaderBean(backUpHeaderBean);
    }

    public BrandManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(BrandManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public BrandManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(BrandManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public BrandManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(BrandManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
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

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

}
