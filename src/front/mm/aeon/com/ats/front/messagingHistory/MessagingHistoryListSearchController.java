/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import mm.aeon.com.ats.base.dto.messagingHistoryCount.MessagingHistoryCountReqDto;
import mm.aeon.com.ats.base.dto.messagingHistorySearch.MessagingHistorySearchReqDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class MessagingHistoryListSearchController extends AbstractController
        implements IControllerAccessor<MessagingHistoryListFormBean, MessagingHistoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public MessagingHistoryListFormBean process(MessagingHistoryListFormBean formBean) {
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        applicationLogger.log("Application Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        MessagingHistoryCountReqDto reqDto = new MessagingHistoryCountReqDto();
        reqDto.setCustomerName(formBean.getMessagingHistoryListHeaderBean().getCustomerName());
        reqDto.setAgentName(formBean.getMessagingHistoryListHeaderBean().getAgentName());
        reqDto.setFromDate(formBean.getMessagingHistoryListHeaderBean().getFromDate());
        reqDto.setToDate(formBean.getMessagingHistoryListHeaderBean().getToDate());
        reqDto.setLoginName(formBean.getMessagingHistoryListHeaderBean().getLoginName());

        MessagingHistorySearchReqDto messageSearchReqDto = new MessagingHistorySearchReqDto();
        messageSearchReqDto.setCustomerName(formBean.getMessagingHistoryListHeaderBean().getCustomerName());
        messageSearchReqDto.setAgentName(formBean.getMessagingHistoryListHeaderBean().getAgentName());
        messageSearchReqDto.setFromDate(formBean.getMessagingHistoryListHeaderBean().getFromDate());
        messageSearchReqDto.setToDate(formBean.getMessagingHistoryListHeaderBean().getToDate());
        messageSearchReqDto.setLoginName(formBean.getMessagingHistoryListHeaderBean().getLoginName());
        try {
            Integer totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqDto);
            formBean.setTotalCount(totalCount);
            formBean.setMessagingHistorySearchReqDto(messageSearchReqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Application searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
