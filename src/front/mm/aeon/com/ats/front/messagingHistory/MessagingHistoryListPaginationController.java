/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ats.base.dto.messagingHistorySearch.MessagingHistorySearchReqDto;
import mm.aeon.com.ats.base.dto.messagingHistorySearch.MessagingHistorySearchResDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class MessagingHistoryListPaginationController extends LazyDataModel<MessagingHistoryListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private MessagingHistorySearchReqDto messagingHistorySearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public MessagingHistoryListPaginationController(int rowCount,
            MessagingHistorySearchReqDto messagingHistorySearchReqDto) {
        this.rowCount = rowCount;
        this.messagingHistorySearchReqDto = messagingHistorySearchReqDto;
    }

    @Override
    public Object getRowKey(MessagingHistoryListLineBean line) {
        return line.getChatMessageId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<MessagingHistoryListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Messaging History Search Pagination Process started.", LogLevel.INFO);
        messagingHistorySearchReqDto.setLimit(pageSize);
        messagingHistorySearchReqDto.setOffset(first);
        messagingHistorySearchReqDto.setSortField(sortField);
        messagingHistorySearchReqDto.setSortOrder(sortOrder.toString());

        List<MessagingHistoryListLineBean> resultList = new ArrayList<MessagingHistoryListLineBean>();
        try {
            List<MessagingHistorySearchResDto> resDtoList = (List<MessagingHistorySearchResDto>) CommonUtil
                    .getDaoServiceInvoker().execute(messagingHistorySearchReqDto);

            for (MessagingHistorySearchResDto messagingHistorySearchResDto : resDtoList) {
                MessagingHistoryListLineBean lineBean = new MessagingHistoryListLineBean();

                lineBean.setChatMessageId(messagingHistorySearchResDto.getChatMessageId());
                lineBean.setProductTypeId(messagingHistorySearchResDto.getProductTypeId());
                lineBean.setBrandId(messagingHistorySearchResDto.getBrandId());
                lineBean.setCustomerLocation(messagingHistorySearchResDto.getCustomerLocation());
                lineBean.setMessageContent(messagingHistorySearchResDto.getMessageContent());
                lineBean.setPrice(messagingHistorySearchResDto.getPrice());
                lineBean.setPhoneNo(messagingHistorySearchResDto.getPhoneNo());
                lineBean.setUrlLink(messagingHistorySearchResDto.getUrlLink());
                lineBean.setSendTime(messagingHistorySearchResDto.getSendTime());
                if (messagingHistorySearchResDto.getOpSendFlag() == 1) {
                    lineBean.setAgentName(messagingHistorySearchResDto.getAgentName());
                    lineBean.setLoginName(messagingHistorySearchResDto.getLoginName());
                } else {
                    lineBean.setAgentName("");
                    lineBean.setLoginName("");
                }

                lineBean.setCustomerName(messagingHistorySearchResDto.getCustomerName());

                resultList.add(lineBean);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("App Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
