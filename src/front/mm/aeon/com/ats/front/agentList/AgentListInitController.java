/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentList;

import mm.aeon.com.ats.front.common.abstractController.AbstractATSController;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentListInitController extends AbstractATSController
        implements IControllerAccessor<AgentListFormBean, AgentListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public AgentListFormBean process(AgentListFormBean formBean) {
        formBean.getMessageContainer().clearAllMessages(true);

        applicationLogger.log("Agent List Init process stared.", LogLevel.INFO);
        MessageBean messageBean;

        formBean.setAtAgentLevelTypeSelectItemList(super.getAtAgentLevelTypeSelectList());
        if (formBean.getAtAgentLevelTypeSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "AGENT LEVEL");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        applicationLogger.log("Operator Init process ended.", LogLevel.INFO);
        return formBean;
    }

}
