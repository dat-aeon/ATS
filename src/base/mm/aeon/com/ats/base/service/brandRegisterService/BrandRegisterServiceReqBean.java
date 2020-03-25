/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.brandRegisterService;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class BrandRegisterServiceReqBean extends AbstractServiceReqBean {
    /**
     * 
     */
    private static final long serialVersionUID = -6412763422215993497L;
    private String brandCode;
    private String brandName;

    @Override
    public String getServiceId() {
        // TODO Auto-generated method stub
        return "BRANDI";
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
