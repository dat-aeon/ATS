/**************************************************************************
 * $Date: 2019-02-12$
 * $Author: Aung Ko lin$
 * $Rev:  $
 * 2019 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ats.base.dto.messagingHistorySearch.MessagingHistorySearchReqDto;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("messagingHistoryListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class MessagingHistoryListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private List<MessagingHistoryListLineBean> lineBeans;

    private LazyDataModel<MessagingHistoryListLineBean> lazyModel;

    private MessagingHistoryListLineBean lineBean;

    private MessagingHistoryListHeaderBean messagingHistoryListHeaderBean;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private int pageFirst;

    private int activeIndex;

    private int totalCount;

    private MessagingHistorySearchReqDto messagingHistorySearchReqDto;

    private ArrayList<SelectItem> brandStatusSelectItemList;

    private ArrayList<SelectItem> productTypeStatusSelectItemList;

    @Begin(nested = true)
    @Action
    public void init() {
        getMessageContainer().clearAllMessages(true);
        messagingHistoryListHeaderBean = new MessagingHistoryListHeaderBean();
        doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        lazyModel = null;
        doReload = new Boolean(false);
        if (!getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new MessagingHistoryListPaginationController(totalCount, messagingHistorySearchReqDto);
        }

        return LinkTarget.OK;
    }

    public String getBrandStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : brandStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public String getProductTypeStatusValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : productTypeStatusSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public void onTabChange(TabChangeEvent event) {
        activeIndex = ((TabView) event.getSource()).getIndex();
    }

    public void reset() {
        this.messagingHistoryListHeaderBean = new MessagingHistoryListHeaderBean();
    }

    public List<MessagingHistoryListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<MessagingHistoryListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<MessagingHistoryListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<MessagingHistoryListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public MessagingHistoryListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(MessagingHistoryListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public MessagingHistoryListHeaderBean getMessagingHistoryListHeaderBean() {
        return messagingHistoryListHeaderBean;
    }

    public void setMessagingHistoryListHeaderBean(MessagingHistoryListHeaderBean messagingHistoryListHeaderBean) {
        this.messagingHistoryListHeaderBean = messagingHistoryListHeaderBean;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public MessagingHistorySearchReqDto getMessagingHistorySearchReqDto() {
        return messagingHistorySearchReqDto;
    }

    public void setMessagingHistorySearchReqDto(MessagingHistorySearchReqDto messagingHistorySearchReqDto) {
        this.messagingHistorySearchReqDto = messagingHistorySearchReqDto;
    }

    public ArrayList<SelectItem> getBrandStatusSelectItemList() {
        return brandStatusSelectItemList;
    }

    public void setBrandStatusSelectItemList(ArrayList<SelectItem> brandStatusSelectItemList) {
        this.brandStatusSelectItemList = brandStatusSelectItemList;
    }

    public ArrayList<SelectItem> getProductTypeStatusSelectItemList() {
        return productTypeStatusSelectItemList;
    }

    public void setProductTypeStatusSelectItemList(ArrayList<SelectItem> productTypeStatusSelectItemList) {
        this.productTypeStatusSelectItemList = productTypeStatusSelectItemList;
    }

}
