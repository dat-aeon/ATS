/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelManagement;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.service.agentLevelInfoRegisterService.AgentLevelInfoRegisterServiceReqBean;
import mm.aeon.com.ats.base.service.agentLevelInfoUpdateService.AgentLevelInfoUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.exception.InvalidScreenTransitionException;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.front.common.util.DisplayItemBean;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractServiceResBean;
import mm.com.dat.presto.main.common.service.bean.ResponseMessage;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;
import mm.com.dat.presto.utils.common.InputChecker;

public class AgentLevelManagementController extends AbstractController
        implements IControllerAccessor<AgentLevelManagementFormBean, AgentLevelManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentLevelManagementFormBean process(AgentLevelManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        if (!VCSCommon.USER_TYPE_ADMIN.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
            logger.log("Invalid URL Access.[Agent Level Management]", new InvalidScreenTransitionException(),
                    LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        if (!isValidData(formBean)) {
            return formBean;
        }

        String serviceStatus = null;

        if (!formBean.getIsUpdate()) {

            applicationLogger.log("Agent Level Register Process started.", LogLevel.INFO);

            AgentLevelInfoRegisterServiceReqBean serviceReqBean = new AgentLevelInfoRegisterServiceReqBean();

            serviceReqBean.setName(formBean.getRegisterHeaderBean().getName());
            serviceReqBean.setTimeMinuteInterval(formBean.getRegisterHeaderBean().getTimeMinuteInterval());

            this.getServiceInvoker().addRequest(serviceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();

            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                msgBean = new MessageBean(MessageId.MI0001);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Agent Level registration finished.", LogLevel.INFO);

            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } else {
            applicationLogger.log("Agent Level Update Process started.", LogLevel.INFO);

            AgentLevelInfoUpdateServiceReqBean updateServiceReqBean = new AgentLevelInfoUpdateServiceReqBean();

            updateServiceReqBean.setAgentLevelTypeId(formBean.getRegisterHeaderBean().getAgentLevelTypeId());
            updateServiceReqBean.setName(formBean.getRegisterHeaderBean().getName());
            updateServiceReqBean.setTimeMinuteInterval(formBean.getRegisterHeaderBean().getTimeMinuteInterval());
            updateServiceReqBean.setUpdatedTime(formBean.getRegisterHeaderBean().getUpdatedTime());

            this.getServiceInvoker().addRequest(updateServiceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();
            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();
            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                formBean.getUpdateParam().setUpdate(true);
                msgBean = new MessageBean(MessageId.MI0002);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Agent update process finished.", LogLevel.INFO);
            } else {
                setErrorMessage(formBean, serviceStatus);
            }
        }

        return formBean;
    }

    private AgentLevelManagementFormBean setErrorMessage(AgentLevelManagementFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, DisplayItem.AGENT_LEVEL);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Registerd agent data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Update agent data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating agent data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating agent data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(AgentLevelManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (InputChecker.isBlankOrNull(formBean.getRegisterHeaderBean().getName())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.AGENT_LEVEL));
            msgBean.addColumnId(DisplayItem.NAME);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        if (formBean.getRegisterHeaderBean().getTimeMinuteInterval() == null) {
            msgBean = new MessageBean(MessageId.ME0003,
                    DisplayItemBean.getDisplayItemName(DisplayItem.AGENT_TIME_INTERVAL));
            msgBean.addColumnId(DisplayItem.AGENT_TIME_INTERVAL);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        } /*
           * else if (formBean.getRegisterHeaderBean().getTimeMinuteInterval() > 60) { msgBean = new
           * MessageBean(MessageId.ME1052, DisplayItemBean.getDisplayItemName(DisplayItem.AGENT_TIME_INTERVAL));
           * msgBean.addColumnId(DisplayItem.AGENT_TIME_INTERVAL); msgBean.setMessageType(MessageType.ERROR);
           * formBean.getMessageContainer().addMessage(msgBean); isValid = false; }
           */

        return isValid;
    }

}
