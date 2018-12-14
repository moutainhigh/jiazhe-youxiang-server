package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointBiz")
public class PointBiz {

    @Autowired
    private PointService pointService;
    @Autowired
    private CustomerBiz customerBiz;

    public List<PointDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        return pointService.getList(mobile,exchangeType,status,expiry,paging);
    }

    public List<PointDTO> getListByCustomerId(Integer customerId, Byte status, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        if (status.equals(Byte.valueOf("0"))) {
            List<PointDTO> pointdtoListAll = pointService.getList(customerDTO.getMobile(), null, null, null, paging);
            return pointdtoListAll;
        }
        if (status.equals(Byte.valueOf("1"))) {
            List<PointDTO> pointdtoListAll = pointService.getList(customerDTO.getMobile(), null, null, null, paging);
            List<PointDTO> pointdtoListUnusable = pointdtoListAll.stream()
                    .filter(bean ->
                            bean.getStatus().equals(Byte.valueOf("0"))
                                    || bean.getExpiryTime().compareTo(new Date()) == -1
                                    || bean.getBalance().compareTo(new BigDecimal(0)) == 0
                    ).collect(Collectors.toList());
            paging.setTotal(pointdtoListUnusable.size());
            return pointdtoListUnusable;
        }
        if (status.equals(Byte.valueOf("2"))) {
            List<PointDTO> temp = pointService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
            List<PointDTO> pointdtoListUsable = temp.stream()
                    .filter(bean -> bean.getBalance().compareTo(new BigDecimal(0)) == 1)
                    .collect(Collectors.toList());
            paging.setTotal(pointdtoListUsable.size());
            return pointdtoListUsable;
        }
        return null;
    }

    public List<PointDTO> getListByGoodsAttr(Integer customerId, Integer productId, String cityCode, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        List<PointDTO> temp = pointService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
        List<PointDTO> pointDtoListUsable = temp.stream()
                .filter(bean ->
                        bean.getBalance().compareTo(new BigDecimal(0)) == 1
                                && bean.getCityCodes().contains(cityCode))
                .filter(bean -> productsHasProduct(bean.getProductIds(), productId))
                .collect(Collectors.toList());
        paging.setTotal(pointDtoListUsable.size());
        return pointDtoListUsable;
    }

    public boolean productsHasProduct(String productIds, Integer productId) {
        List<String> result = Arrays.asList(productIds.split(","));
        return result.contains(productId.toString());
    }

    public void startUsing(Integer id) {
        pointService.changeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        pointService.changeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public void directCharge(Integer id, Integer batchId, BigDecimal faceValue) {
        pointService.directCharge(id, batchId, faceValue);
    }

    public PointDTO getById(Integer id) {
        return pointService.getById(id);
    }

    public void editSave(PointEditDTO dto) {
        pointService.editSave(dto);
    }
}
