package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ChargeOffPO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.QuerySummaryDTO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ChargeOffPOManualMapper {

    List<ChargeOffPO> query(@Param("queryDTO") ChargeOffQueryDTO queryDTO, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<ChargeOffPO> fuzzyQuery(@Param("fuzzyQueryDTO") ChargeOffFuzzyQueryDTO fuzzyQueryDTO, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int fuzzyQueryCount(@Param("fuzzyQueryDTO") ChargeOffFuzzyQueryDTO fuzzyQueryDTO);

    int queryCount(@Param("queryDTO") ChargeOffQueryDTO queryDTO);

    int insertSelective(ChargeOffPO chargeOffPO);

    BigDecimal queryTotalPoint(@Param("queryDTO") ChargeOffQueryDTO queryDTO);

    QuerySummaryDTO querySummary(@Param("queryDTO") ChargeOffQueryDTO dto);
}
