/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryManagement;

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

@Name("categoryManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class CategoryManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "categoryUpdateParam")
    @Out(required = false, value = "categoryUpdateParam")
    private CategoryManagementHeaderBean updateParam;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private CategoryManagementHeaderBean registerHeaderBean;

    private CategoryManagementHeaderBean backUpHeaderBean;

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
        registerHeaderBean = new CategoryManagementHeaderBean();
    }

    @Begin(join = true)
    public void updateInit() {
        this.getMessageContainer().clearAllMessages(true);

        init = false;
        isUpdate = true;
        cancel = false;
        registerHeaderBean = new CategoryManagementHeaderBean();
        this.registerHeaderBean.setCategoryId(updateParam.getCategoryId());
        this.registerHeaderBean.setName(updateParam.getName());
        this.registerHeaderBean.setCreatedBy(updateParam.getCreatedBy());
        this.registerHeaderBean.setCreatedTime(updateParam.getCreatedTime());
        this.registerHeaderBean.setUpdatedBy(updateParam.getUpdatedBy());
        this.registerHeaderBean.setUpdatedTime(updateParam.getUpdatedTime());
        this.registerHeaderBean.setDelFlag(updateParam.getDelFlag());
        this.backUpHeaderBean = new CategoryManagementHeaderBean().copyCategoryManagementHeaderBean(this.registerHeaderBean);
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            registerHeaderBean = new CategoryManagementHeaderBean();
            init = true;
            cancel = true;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        registerHeaderBean = new CategoryManagementHeaderBean();
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
        this.registerHeaderBean = new CategoryManagementHeaderBean();
    }

    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyCategoryManagementHeaderBean(backUpHeaderBean);
    }

    public CategoryManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(CategoryManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public CategoryManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(CategoryManagementHeaderBean registerHeaderBean) {
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

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public CategoryManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public void setBackUpHeaderBean(CategoryManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

}
