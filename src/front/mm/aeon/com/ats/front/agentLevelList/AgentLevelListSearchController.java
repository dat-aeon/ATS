/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelList;

import mm.aeon.com.ats.base.dto.agentLevelInfoCount.AgentLevelInfoCountReqDto;
import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListReqDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.exception.InvalidScreenTransitionException;
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

public class AgentLevelListSearchController extends AbstractController
        implements IControllerAccessor<AgentLevelListFormBean, AgentLevelListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentLevelListFormBean process(AgentLevelListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        if (!VCSCommon.USER_TYPE_ADMIN.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
            logger.log("Invalid URL Access.[Agent List]", new InvalidScreenTransitionException(), LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        applicationLogger.log("Agent Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        AgentLevelInfoCountReqDto countReqDto = new AgentLevelInfoCountReqDto();

        AtAgentLevelTypeSelectListReqDto reqDto = new AtAgentLevelTypeSelectListReqDto();

        reqDto.setDelFlag(false);

        try {
            Integer totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(countReqDto);
            formBean.setTotalCount(totalCount);
            formBean.setAgentLevelReqDto(reqDto);

            if (totalCount == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
