/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Khin Yadanar Thein $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.common.abstractController;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListReqDto;
import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListResDto;
import mm.aeon.com.ats.base.dto.brandList.BrandSelectListReqDto;
import mm.aeon.com.ats.base.dto.brandList.BrandSelectListResDto;
import mm.aeon.com.ats.base.dto.productTypeList.ProductTypeSelectListReqDto;
import mm.aeon.com.ats.base.dto.productTypeList.ProductTypeSelectListResDto;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

/**
 * AbstractProjectController Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
public abstract class AbstractATSController extends AbstractController {

    private ASSLogger logger = new ASSLogger();

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    protected ArrayList<SelectItem> getEmptyList() {

        applicationLogger.log("User Search Process started.", LogLevel.INFO);

        ArrayList<SelectItem> emptyList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(null);
        emptyList.add(item);

        return emptyList;
    }

    protected ArrayList<SelectItem> getBrandSelectList() {
        ArrayList<SelectItem> brandTypeSelectList = new ArrayList<>();
        BrandSelectListReqDto reqDto = new BrandSelectListReqDto();
        reqDto.setDelFlag(0);

        try {
            ArrayList<BrandSelectListResDto> resDtoList =
                    (ArrayList<BrandSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (BrandSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getBrandName());
                selectItem.setValue(resDto.getBrandId());

                brandTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return brandTypeSelectList;
    }

    protected ArrayList<SelectItem> getProductTypeSelectList() {
        ArrayList<SelectItem> productTypeSelectList = new ArrayList<>();
        ProductTypeSelectListReqDto reqDto = new ProductTypeSelectListReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<ProductTypeSelectListResDto> resDtoList =
                    (ArrayList<ProductTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (ProductTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getProductName());
                selectItem.setValue(resDto.getProductTypeId());

                productTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return productTypeSelectList;
    }

    protected ArrayList<SelectItem> getAtAgentLevelTypeSelectList() {
        ArrayList<SelectItem> atAgentLevelTypeSelectList = new ArrayList<>();
        AtAgentLevelTypeSelectListReqDto reqDto = new AtAgentLevelTypeSelectListReqDto();
        reqDto.setDelFlag(false);

        try {
            ArrayList<AtAgentLevelTypeSelectListResDto> resDtoList =
                    (ArrayList<AtAgentLevelTypeSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(reqDto);

            for (AtAgentLevelTypeSelectListResDto resDto : resDtoList) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(resDto.getName());
                selectItem.setValue(resDto.getAtAgentLevelTypeId());

                atAgentLevelTypeSelectList.add(selectItem);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return atAgentLevelTypeSelectList;
    }

}
