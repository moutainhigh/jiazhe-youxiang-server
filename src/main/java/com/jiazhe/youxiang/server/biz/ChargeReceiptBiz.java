package com.jiazhe.youxiang.server.biz;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.ChargeReceiptService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
@Service("chargeReceiptBiz")
public class ChargeReceiptBiz {

    @Autowired
    private ChargeReceiptService chargeReceiptService;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;

    public List<ChargeReceiptDTO> getList(Integer auditRecordId, String customerName, String cardNo, String posCode, Date tradeStartTime, Date tradeEndTime, Paging paging) {
        List<ChargeReceiptDTO> result = chargeReceiptService.getList(auditRecordId, customerName, cardNo, posCode, tradeStartTime, tradeEndTime, paging);
        List<String> receiptKeys = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(result)) {
            result.stream().forEach(item -> receiptKeys.add(createReceiptKey(item)));
            List<PointExchangeRecordDTO> recordDTOList = pointExchangeRecordService.findByExtInfos(receiptKeys);
            try {
                Map<String, PointExchangeRecordDTO> recordDTOMap = recordDTOList.stream().collect(toMap(PointExchangeRecordDTO::getReceiptInfo, Function.identity()));
                if (MapUtils.isNotEmpty(recordDTOMap)) {
                    result.stream().forEach(item -> {
                        PointExchangeRecordDTO recordDTO = recordDTOMap.get(createReceiptKey(item));
                        if (recordDTO != null) {
                            item.setExchangeMobile(recordDTO.getOperatorName());
                        }
                    });
                }
            } catch (IllegalStateException e) {
                result.stream().forEach(item -> {
                    item.setExchangeMobile("出现重复记录，请手动查询");
                });
            }

        }
        return result;
    }

    private String createReceiptKey(ChargeReceiptDTO item) {
        StringBuilder sb = new StringBuilder();
        if (item != null) {
            sb.append(item.getCardNo());
            sb.append(',');
            sb.append(item.getExchangePoint().intValue());
            sb.append(',');
            sb.append(DateUtil.yyyyMMDD(item.getTradeTime()));
        }
        return sb.toString();
    }


    public void delete(Integer id) {
        chargeReceiptService.delete(id);
    }

    public void save(ChargeReceiptSaveDTO dto) {
        chargeReceiptService.save(dto);
    }

    public ChargeReceiptDTO getById(Integer id) {
        return chargeReceiptService.getById(id);
    }

    public List<ChargeReceiptDTO> getList(String customerInfo, String submitterName, Byte status, Byte chargeReceiptStatus, String pointCodes, String exchangePoint, Date submitStartTime, Date submitEndTime, String exchangeType, String cityCode) {
        return chargeReceiptService.getList(customerInfo, submitterName, status, chargeReceiptStatus, pointCodes, exchangePoint, submitStartTime, submitEndTime, exchangeType, cityCode);
    }

    public boolean hasExisted(ChargeReceiptSaveDTO dto) {
        return chargeReceiptService.hasExisted(dto);
    }

}
