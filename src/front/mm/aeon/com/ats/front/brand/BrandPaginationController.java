/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ats.base.dto.brandList.BrandSelectListReqDto;
import mm.aeon.com.ats.base.dto.brandList.BrandSelectListResDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class BrandPaginationController extends LazyDataModel<BrandLineBean> {

    private int rowCount;

    private BrandSelectListReqDto brandSelectListReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public BrandPaginationController(int rowCount, BrandSelectListReqDto brandSelectListReqDto) {
        this.rowCount = rowCount;
        this.brandSelectListReqDto = brandSelectListReqDto;
    }

    @Override
    public Object getRowKey(BrandLineBean lineBean) {
        // TODO Auto-generated method stub
        return lineBean.getBrandCode();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowCount;
    }

    @Override
    public List<BrandLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        applicationLogger.log("Brand Search Pagination Process Started.", LogLevel.INFO);
        brandSelectListReqDto.setLimit(pageSize);
        brandSelectListReqDto.setOffset(first);
        brandSelectListReqDto.setSortField(sortField);
        brandSelectListReqDto.setSortOrder(sortOrder.toString());

        List<BrandLineBean> resultList = new ArrayList<BrandLineBean>();
        BrandLineBean lineBean;

        try {
            List<BrandSelectListResDto> resDtoList =
                    (List<BrandSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(brandSelectListReqDto);
            for (BrandSelectListResDto resDto : resDtoList) {
                lineBean = new BrandLineBean();
                lineBean.setBrandId(resDto.getBrandId());
                lineBean.setBrandCode(resDto.getBrandCode());
                lineBean.setBrandName(resDto.getBrandName());
                lineBean.setUpdatedTime(resDto.getUpdatedTime());
                resultList.add(lineBean);

            }
        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("Brand Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }
}
