/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryList;

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
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.categoryManagement.CategoryManagementHeaderBean;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("categoryListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class CategoryListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private CategoryListHeaderBean searchHeaderBean;

    private List<CategoryListLineBean> lineBeans;

    private LazyDataModel<CategoryListLineBean> lazyModel;

    private CategoryListLineBean lineBean;

    private ArrayList<SelectItem> statusList;

    @Out(required = false, value = "categoryUpdateParam")
    private CategoryManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    @Begin(nested = true)
    public void init() {
        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new CategoryListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && lineBeans.size() != 0) {
            lazyModel = new CategoryListPaginationController(lineBeans.size(), lineBeans);
        }

        return LinkTarget.OK;
    }

    public String prepareUpdate(CategoryListLineBean lineBean) {

        this.updateParam = new CategoryManagementHeaderBean();
        this.updateParam.setCategoryId(lineBean.getCategoryId());
        this.updateParam.setName(lineBean.getName());
        this.updateParam.setCreatedBy(lineBean.getCreatedBy());
        this.updateParam.setCreatedTime(lineBean.getCreatedTime());
        this.updateParam.setUpdatedBy(lineBean.getUpdatedBy());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        this.updateParam.setDelFlag(lineBean.getDelFlag());

        return LinkTarget.UPDATE_INIT;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        return LinkTarget.SEARCH;
    }
    

    public String detail(CategoryListLineBean lineBean) {
        this.lineBean=lineBean;
        return LinkTarget.DETAIL;
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
        this.searchHeaderBean = new CategoryListHeaderBean();
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }

    public CategoryManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(CategoryManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public CategoryListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(CategoryListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public List<CategoryListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<CategoryListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<CategoryListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CategoryListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public CategoryListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(CategoryListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public ArrayList<SelectItem> getStatusList() {

        statusList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(VCSCommon.TWO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.DISABLED);
        item.setValue(VCSCommon.ZERO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.ENABLED);
        item.setValue(VCSCommon.ONE);
        statusList.add(item);

        return statusList;
    }

    public void setStatusList(ArrayList<SelectItem> statusList) {
        this.statusList = statusList;
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

}
