package com.jiazhe.youxiang.server.service.advancepay;

import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/10.
 */
public interface AdvancePayService {

    List<AdvancePayDTO> getList(Date timeStart, Date timeEnd);

    void save(AdvancePayDTO advancePayDTO);
}
