/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import mm.aeon.com.ats.front.common.abstractController.AbstractATSController;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class MessagingHistoryListInitController extends AbstractATSController
        implements IControllerAccessor<MessagingHistoryListFormBean, MessagingHistoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public MessagingHistoryListFormBean process(MessagingHistoryListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);

        /*
         * if (!VCSCommon.ONE.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
         * logger.log("Invalid URL Access.[Customer List Init]", new InvalidScreenTransitionException(),
         * LogLevel.ERROR); throw new InvalidScreenTransitionException(); }
         */

        applicationLogger.log("Customer Init process stared.", LogLevel.INFO);
        MessageBean messageBean;

        formBean.setBrandStatusSelectItemList(super.getBrandSelectList());
        if (formBean.getBrandStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "BRAND");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        formBean.setProductTypeStatusSelectItemList(super.getProductTypeSelectList());
        if (formBean.getProductTypeStatusSelectItemList().size() == 0) {
            messageBean = new MessageBean(MessageId.ME1006, "BRAND");
            messageBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
        }

        applicationLogger.log("Customer Init process ended.", LogLevel.INFO);
        return formBean;
    }

}
