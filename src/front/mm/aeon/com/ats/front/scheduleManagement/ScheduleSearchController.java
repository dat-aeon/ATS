/**************************************************************************

 * $Date: 2018-08-02$
 * $Author: Su Su Sandi $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.scheduleManagement;

import java.util.List;

import mm.aeon.com.ats.base.dto.scheduleInfoSearch.ScheduleSearchReqDto;
import mm.aeon.com.ats.base.dto.scheduleInfoSearch.ScheduleSearchResDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
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

public class ScheduleSearchController extends AbstractController
        implements IControllerAccessor<ScheduleManagementFormBean, ScheduleManagementFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public ScheduleManagementFormBean process(ScheduleManagementFormBean formBean) {

        /* formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload()); */

        applicationLogger.log("Schedule Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;

        ScheduleSearchReqDto reqDto = new ScheduleSearchReqDto();

        try {
            List<ScheduleSearchResDto> resScheduleList =
                    (List<ScheduleSearchResDto>) this.getDaoServiceInvoker().execute(reqDto);

            if (resScheduleList.size() > 0) {
                ScheduleSearchResDto resdto = resScheduleList.get(0);
                ScheduleManagementBean lineBean = new ScheduleManagementBean();
                lineBean.setScheduleTimeId(resdto.getScheduleTimeId());
                lineBean.setDurationHour(resdto.getDurationHour());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                formBean.setRegisterHeaderBean(lineBean);
            } else {
                msgBean = new MessageBean(MessageId.MI0008);
                msgBean.setMessageType(MessageType.INFO);
                formBean.getMessageContainer().addMessage(msgBean);
                applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
                applicationLogger.log("Schedule searching finished.", LogLevel.INFO);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }
}
