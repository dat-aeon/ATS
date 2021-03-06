/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelList;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.dto.atAgentLevelSelectForDelete.AtAgentLevelSelectForDeleteReqDto;
import mm.aeon.com.ats.base.dto.atAgentLevelSelectForDelete.AtAgentLevelSelectForDeleteResDto;
import mm.aeon.com.ats.base.service.agentLevelInfoUpdateService.AgentLevelInfoUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentLevelDeleteController extends AbstractController
        implements IControllerAccessor<AgentLevelListFormBean, AgentLevelListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentLevelListFormBean process(AgentLevelListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        if (!VCSCommon.USER_TYPE_ADMIN.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
            logger.log("Invalid URL Access.[Category List]", new InvalidScreenTransitionException(), LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        applicationLogger.log("Category deleting Process started.", LogLevel.INFO);
        MessageBean msgBean;

        AtAgentLevelSelectForDeleteReqDto selectForUpdateReqDto = new AtAgentLevelSelectForDeleteReqDto();
        selectForUpdateReqDto.setAgentLevelTypeId(formBean.getLineBean().getAtAgentLevelTypeId());

        try {
            AtAgentLevelSelectForDeleteResDto selectForUpdateResDto =
                    (AtAgentLevelSelectForDeleteResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto != null) {
                msgBean = new MessageBean(MessageId.ME1051);
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log("This Agent Level is already used by other user..", LogLevel.ERROR);
                return formBean;
            }
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AgentLevelInfoUpdateServiceReqBean updateServiceReqBean = new AgentLevelInfoUpdateServiceReqBean();

        updateServiceReqBean.setAgentLevelTypeId(formBean.getLineBean().getAtAgentLevelTypeId());
        updateServiceReqBean.setUpdatedTime(formBean.getLineBean().getUpdatedTime());
        updateServiceReqBean.setDelFlag(true);
        this.getServiceInvoker().addRequest(updateServiceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();

        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        String serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            msgBean = new MessageBean(MessageId.MI0003);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log("Agent Level deleting Process finished.", LogLevel.INFO);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Agent Level update process finished.", LogLevel.INFO);
        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Update agent level data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            // formBean.getUpdateParam().setIsUpdate(true);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating agent level data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating agent level data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

}
