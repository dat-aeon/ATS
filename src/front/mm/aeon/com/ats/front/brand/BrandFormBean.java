/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brand;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ats.base.dto.brandList.BrandSelectListReqDto;
import mm.aeon.com.ats.front.brandManagement.BrandManagementHeaderBean;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("brandFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class BrandFormBean extends AbstractFormBean implements IRequest, IResponse {
    /**
     * 
     */
    private static final long serialVersionUID = 4271122722526673607L;

    private BrandHeaderBean searchHeaderBean;

    private int totalCount;

    private List<BrandLineBean> lineBeans;

    private LazyDataModel<BrandLineBean> lazyModel;

    private BrandLineBean lineBean;
    private BrandSelectListReqDto brandSelectListReqDto;
    private ArrayList<SelectItem> statusList;

    @Out(required = false, value = "adminUpdateParam")
    private BrandManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    @Begin(nested = true)
    public void init() {
        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new BrandHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new BrandPaginationController(totalCount, brandSelectListReqDto);
        }

        return LinkTarget.OK;
    }

    public String prepareUpdate(BrandLineBean lineBean) {

        this.updateParam = new BrandManagementHeaderBean();
        this.updateParam.setBrandCode(lineBean.getBrandCode());
        this.updateParam.setBrandName(lineBean.getBrandName());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());

        return LinkTarget.UPDATE_INIT;
    }

    @Action
    public String delete() {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new BrandHeaderBean();
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }

    public BrandHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(BrandHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public List<BrandLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<BrandLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<BrandLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<BrandLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public BrandLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(BrandLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public ArrayList<SelectItem> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<SelectItem> statusList) {
        this.statusList = statusList;
    }

    public BrandManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(BrandManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BrandSelectListReqDto getBrandSelectListReqDto() {
        return brandSelectListReqDto;
    }

    public void setBrandSelectListReqDto(BrandSelectListReqDto brandSelectListReqDto) {
        this.brandSelectListReqDto = brandSelectListReqDto;
    }

}
