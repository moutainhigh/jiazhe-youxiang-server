package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointExchangeRecordBiz")
public class PointExchangeRecordBiz {

    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;

    public List<PointExchangeRecordDTO> getList(Date beginDate, Date endDate, String code, String keyt, Paging paging) {
        return null;
    }

    public PointExchangeRecordDTO getByPointId(Integer id) {
        return null;
    }

    public PointExchangeRecordDTO getByCodeId(Integer id) {
        return pointExchangeRecordService.findByCodeId(id);
    }

}
