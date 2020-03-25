/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brandManagement;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.service.brandRegisterService.BrandRegisterServiceReqBean;
import mm.aeon.com.ats.base.service.brandUpdateService.BrandUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
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

public class BrandManagementController extends AbstractController
        implements IControllerAccessor<BrandManagementFormBean, BrandManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public BrandManagementFormBean process(BrandManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        if (!isValidData(formBean)) {
            return formBean;
        }

        String serviceStatus = null;

        if (!formBean.getIsUpdate()) {

            applicationLogger.log("Brand Register Process started.", LogLevel.INFO);

            BrandRegisterServiceReqBean serviceReqBean = new BrandRegisterServiceReqBean();

            serviceReqBean.setBrandCode(formBean.getRegisterHeaderBean().getBrandCode());
            serviceReqBean.setBrandName(formBean.getRegisterHeaderBean().getBrandName());

            this.getServiceInvoker().addRequest(serviceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();

            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                msgBean = new MessageBean(MessageId.MI0001);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Brand Register finished.", LogLevel.INFO);

            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } else {
            applicationLogger.log("Brand Update Process started.", LogLevel.INFO);

            BrandUpdateServiceReqBean updateServiceReqBean = new BrandUpdateServiceReqBean();

            updateServiceReqBean.setBrandCode(formBean.getRegisterHeaderBean().getBrandCode());
            updateServiceReqBean.setBrandName(formBean.getRegisterHeaderBean().getBrandName());
            updateServiceReqBean.setUpdateTime(formBean.getRegisterHeaderBean().getUpdatedTime());

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
                applicationLogger.log("Brand update process finished.", LogLevel.INFO);
            } else {
                setErrorMessage(formBean, serviceStatus);
            }
        }

        return formBean;
    }

    private BrandManagementFormBean setErrorMessage(BrandManagementFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.BRAND_CODE);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Registerd Brand Code already exist.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating Brand Data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating Brand Data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(BrandManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (InputChecker.isBlankOrNull(formBean.getRegisterHeaderBean().getBrandCode())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.Brand_Code));
            msgBean.addColumnId(DisplayItem.Brand_Code);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        if (InputChecker.isBlankOrNull(formBean.getRegisterHeaderBean().getBrandName())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.Brand_Name));
            msgBean.addColumnId(DisplayItem.Brand_Name);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

}
