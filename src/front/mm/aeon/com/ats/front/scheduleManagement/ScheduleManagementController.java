/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.scheduleManagement;

import java.nio.charset.Charset;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.service.scheduleUpdateService.ScheduleUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.front.common.util.DisplayItemBean;
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

public class ScheduleManagementController extends AbstractController
        implements IControllerAccessor<ScheduleManagementFormBean, ScheduleManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    @Override
    public ScheduleManagementFormBean process(ScheduleManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        if (!isValidData(formBean)) {
            return formBean;
        }

        String serviceStatus = null;

        applicationLogger.log("Schedule Register Process started.", LogLevel.INFO);

        ScheduleUpdateServiceReqBean serviceReqBean = new ScheduleUpdateServiceReqBean();

        serviceReqBean.setScheduleTimeId(formBean.getRegisterHeaderBean().getScheduleTimeId());
        serviceReqBean.setDurationHour(formBean.getRegisterHeaderBean().getDurationHour());
        serviceReqBean.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserName());
        serviceReqBean.setUpdatedTime(formBean.getRegisterHeaderBean().getUpdatedTime());
        serviceReqBean.setDelFlag(false);

        this.getServiceInvoker().addRequest(serviceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();

        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            msgBean = new MessageBean(MessageId.MI0002);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Schedule Update finished.", LogLevel.INFO);

        } else {
            setErrorMessage(formBean, serviceStatus);
        }

        return formBean;
    }

    private ScheduleManagementFormBean setErrorMessage(ScheduleManagementFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Registerd Schedule data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Update Schedule data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            // formBean.getUpdateParam().setIsUpdate(true);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating Schedule data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating Schedule data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(ScheduleManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (formBean.getRegisterHeaderBean().getDurationHour() == null) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.DURATION_HOUR));
            msgBean.addColumnId(DisplayItem.DURATION_HOUR);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        } else {
            if (formBean.getRegisterHeaderBean().getDurationHour() <= 0) {
                msgBean = new MessageBean(MessageId.ME1050,
                        DisplayItemBean.getDisplayItemName(DisplayItem.DURATION_HOUR));
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                isValid = false;
            }
        }

        /*
         * if (formBean.getRegisterHeaderBean().getDurationHour() > 24) { msgBean = new MessageBean(MessageId.ME1044,
         * DisplayItemBean.getDisplayItemName(DisplayItem.DURATION_HOUR));
         * msgBean.addColumnId(DisplayItem.DURATION_HOUR); msgBean.setMessageType(MessageType.ERROR);
         * formBean.getMessageContainer().addMessage(msgBean); isValid = false; }
         */
        return isValid;
    }

    public boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
        // or "ISO-8859-1" for ISO Latin 1
        // or StandardCharsets.US_ASCII with JDK1.7+
    }

}
