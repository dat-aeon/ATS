/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brand;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.dto.messagingHistoryCount.MessagingHistoryCountReqDto;
import mm.aeon.com.ats.base.service.brandUpdateService.BrandUpdateServiceReqBean;
import mm.aeon.com.ats.front.common.constants.DisplayItem;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.front.common.util.DisplayItemBean;
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

public class BrandDeleteController extends AbstractController
        implements IControllerAccessor<BrandFormBean, BrandFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();
    private ASSLogger logger = new ASSLogger();

    @Override
    public BrandFormBean process(BrandFormBean formBean) {
        formBean.getMessageContainer().clearAllMessages(true);

        applicationLogger.log("Brand deleting Process started.", LogLevel.INFO);
        MessageBean msgBean;

        try {
            MessagingHistoryCountReqDto brandReqDto = new MessagingHistoryCountReqDto();
            brandReqDto.setBrandId(formBean.getLineBean().getBrandId());
            int count = (int) CommonUtil.getDaoServiceInvoker().execute(brandReqDto);

            if (count > 0) {
                msgBean = new MessageBean(MessageId.ME1045, DisplayItemBean.getDisplayItemName(DisplayItem.Brand_Code));
                msgBean.setMessageType(MessageType.ERROR);
                formBean.getMessageContainer().addMessage(msgBean);
                return formBean;
            }
        } catch (PrestoDBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BrandUpdateServiceReqBean updateServiceReqBean = new BrandUpdateServiceReqBean();

        updateServiceReqBean.setBrandCode(formBean.getLineBean().getBrandCode());
        updateServiceReqBean.setUpdateTime(formBean.getLineBean().getUpdatedTime());
        updateServiceReqBean.setDelFlag(1);

        this.getServiceInvoker().addRequest(updateServiceReqBean);
        ResponseMessage responseMessage = this.getServiceInvoker().invoke();
        AbstractServiceResBean resBean = responseMessage.getMessageBean(0);
        String serviceStatus = resBean.getServiceStatus();

        if (ServiceStatusCode.OK.equals(serviceStatus)) {

            msgBean = new MessageBean(MessageId.MI0003);
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log("Brand deleting Process finished.", LogLevel.INFO);

            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Brand update process finished.", LogLevel.INFO);
        }

        else if (ASSServiceStatusCommon.RECORD_ALREADY_UPDATE.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1011);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);
            // formBean.getUpdateParam().setIsUpdate(true);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating Brand data already updated.", LogLevel.ERROR);

        } else if (ServiceStatusCode.RECORD_NOT_FOUND_ERROR.equals(serviceStatus)) {
            msgBean = new MessageBean(MessageId.ME1009);
            msgBean.setMessageType(MessageType.ERROR);
            formBean.getMessageContainer().addMessage(msgBean);

            applicationLogger.log(msgBean.getMessage(), LogLevel.ERROR);
            applicationLogger.log("Updating brand data already deleted by other.", LogLevel.ERROR);

        } else if (ServiceStatusCode.SQL_ERROR.equals(serviceStatus)) {
            throw new BaseException();
        }

        return formBean;
    }
}
