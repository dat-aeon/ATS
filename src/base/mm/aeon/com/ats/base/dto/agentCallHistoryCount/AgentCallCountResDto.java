/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentCallHistoryCount;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentCallCountResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -7395729054191766696L;
    
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
