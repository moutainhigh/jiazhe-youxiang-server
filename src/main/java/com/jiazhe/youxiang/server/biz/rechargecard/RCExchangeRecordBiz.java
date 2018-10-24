package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordListDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeRecordBiz")
public class RCExchangeRecordBiz {

    public List<RCExchangeRecordListDTO> getList(Date beginDate, Date endDate, String code, String keyt, Paging paging) {
        return null;
    }
}
