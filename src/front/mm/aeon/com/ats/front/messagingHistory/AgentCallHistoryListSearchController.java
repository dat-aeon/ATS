/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import mm.aeon.com.ats.base.dto.agentCallHistoryCount.AgentCallCountReqDto;
import mm.aeon.com.ats.base.dto.agentCallHistorySearch.AgentCallHistorySearchReqDto;
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

public class AgentCallHistoryListSearchController extends AbstractController
        implements IControllerAccessor<AgentCallHistoryListFormBean, AgentCallHistoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentCallHistoryListFormBean process(AgentCallHistoryListFormBean formBean) {

        applicationLogger.log("Application Searching Process started.", LogLevel.INFO);
        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());
        MessageBean msgBean;

        AgentCallCountReqDto reqDto = new AgentCallCountReqDto();
        reqDto.setAgentName(formBean.getAgentCallHeaderBean().getAgentName());
        reqDto.setLoginName(formBean.getAgentCallHeaderBean().getLoginName());

        AgentCallHistorySearchReqDto messageSearchReqDto = new AgentCallHistorySearchReqDto();
        messageSearchReqDto.setAgentName(formBean.getAgentCallHeaderBean().getAgentName());
        messageSearchReqDto.setLoginName(formBean.getAgentCallHeaderBean().getLoginName());

        try {
            Integer totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgentCallHistorySearchReqDto(messageSearchReqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent Call Report searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
