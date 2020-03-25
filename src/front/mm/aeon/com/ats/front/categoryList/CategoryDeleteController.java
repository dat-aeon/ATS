/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryList;

import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.util.DisplayItemBean;
import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.dto.categoryInfoSelectForUpdate.CategoryInfoSelectForUpdateReqDto;
import mm.aeon.com.ats.base.dto.categoryInfoSelectForUpdate.CategoryInfoSelectForUpdateResDto;
import mm.aeon.com.ats.base.dto.messagingHistoryCount.MessagingHistoryCountReqDto;
import mm.aeon.com.ats.base.dto.messagingHistoryCount.MessagingHistoryCountResDto;
import mm.aeon.com.ats.base.service.categoryInfoUpdateService.CategoryInfoUpdateServiceReqBean;
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

public class CategoryDeleteController extends AbstractController
        implements IControllerAccessor<CategoryListFormBean, CategoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public CategoryListFormBean process(CategoryListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(true);
        
        MessageBean msgBean;

            try {
                MessagingHistoryCountReqDto categoryReqDto = new MessagingHistoryCountReqDto();
                categoryReqDto.setCategoryId(formBean.getLineBean().getCategoryId());
                int count = (int) CommonUtil.getDaoServiceInvoker().execute(categoryReqDto);

                if (count > 0) {
                    msgBean = new MessageBean(MessageId.ME1046,
                            DisplayItemBean.getDisplayItemName(DisplayItem.CATEGORY));
                    msgBean.setMessageType(MessageType.ERROR);
                    formBean.getMessageContainer().addMessage(msgBean);
                    return formBean;
                }
            } catch (PrestoDBException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        applicationLogger.log("Category deleting Process started.", LogLevel.INFO);

        CategoryInfoUpdateServiceReqBean updateServiceReqBean = new CategoryInfoUpdateServiceReqBean();

        updateServiceReqBean.setCategoryId(formBean.getLineBean().getCategoryId());
        updateServiceReqBean.setDelFlag(VCSCommon.VALUE_TRUE);
        updateServiceReqBean.setUpdatedTime(formBean.getLineBean().getUpdatedTime());
        updateServiceReqBean.setUpdatedBy(CommonUtil.getPlainCurrentTimeStamp());
        this.getServiceInvoker().addRequest(updateServiceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
        
        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        String serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            msgBean = new MessageBean(MessageId.MI0003);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log("Category deleting Process finished.", LogLevel.INFO);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Category update process finished.", LogLevel.INFO);
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
            // formBean.getUpdateParam().setIsUpdate(true);

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

}
