/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryManagement;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.service.categoryInfoRegisterService.CategoryInfoRegisterServiceReqBean;
import mm.aeon.com.ats.base.service.categoryInfoUpdateService.CategoryInfoUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
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

public class CategoryManagementController extends AbstractController
        implements IControllerAccessor<CategoryManagementFormBean, CategoryManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public CategoryManagementFormBean process(CategoryManagementFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        MessageBean msgBean;

        if (!isValidData(formBean)) {
            return formBean;
        }

        String serviceStatus = null;

        if (!formBean.getIsUpdate()) {

            applicationLogger.log("Category Register Process started.", LogLevel.INFO);

            CategoryInfoRegisterServiceReqBean serviceReqBean = new CategoryInfoRegisterServiceReqBean();

            serviceReqBean.setCategoryId(formBean.getRegisterHeaderBean().getCategoryId());
            serviceReqBean.setName(formBean.getRegisterHeaderBean().getName());
            serviceReqBean.setCreatedBy(CommonUtil.getLoginUserName());
            serviceReqBean.setCreatedTime(CommonUtil.getCurrentTimeStamp());

            this.getServiceInvoker().addRequest(serviceReqBean);
            ResponseMessage responseMessage = this.getServiceInvoker().invoke();

            AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
            serviceStatus = resBean.getServiceStatus();

            if (ServiceStatusCode.OK.equals(serviceStatus)) {

                msgBean = new MessageBean(MessageId.MI0001);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);

                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Category registration finished.", LogLevel.INFO);

            } else {
                setErrorMessage(formBean, serviceStatus);
            }

        } else {
            applicationLogger.log("Category Update Process started.", LogLevel.INFO);

            CategoryInfoUpdateServiceReqBean updateServiceReqBean = new CategoryInfoUpdateServiceReqBean();

            updateServiceReqBean.setCategoryId(formBean.getRegisterHeaderBean().getCategoryId());
            updateServiceReqBean.setName(formBean.getRegisterHeaderBean().getName());
            updateServiceReqBean.setUpdatedBy(CommonUtil.getLoginUserName());
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
                applicationLogger.log("Category update process finished.", LogLevel.INFO);
            } else {
                setErrorMessage(formBean, serviceStatus);
            }
        }

        return formBean;
    }

    private CategoryManagementFormBean setErrorMessage(CategoryManagementFormBean formBean, String serviceStatus) {

        MessageBean msgBean;

        if (ServiceStatusCode.RECORD_DUPLICATED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1012, VCSCommon.LOGIN_ID);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Registerd category data already exist.", LogLevel.ERROR);

        } else if (ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1010);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Update category data is locked.", LogLevel.ERROR);

        } else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating category data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating category data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }

    private boolean isValidData(CategoryManagementFormBean formBean) {
        boolean isValid = true;
        MessageBean msgBean;

        if (InputChecker.isBlankOrNull(formBean.getRegisterHeaderBean().getName())) {
            msgBean = new MessageBean(MessageId.ME0003, DisplayItemBean.getDisplayItemName(DisplayItem.NAME));
            msgBean.addColumnId(DisplayItem.NAME);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            isValid = false;
        }

        return isValid;
    }

}
