package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
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
 * @date 2018/10/21
 */
@Service("rcBiz")
public class RCBiz {

    @Autowired
    private RCService rcService;
    @Autowired
    private CustomerBiz customerBiz;

    public void startUsing(Integer id) {
        rcService.changeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        rcService.changeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }


    public void changeExpiryTime(Integer id, Date expiryTime) {
        rcService.changeExpiryTime(id, expiryTime);
    }


    public void directCharge(String mobile, Integer batchId, BigDecimal faceValue) {
        rcService.directCharge(mobile, batchId, faceValue);
    }

    public List<RCDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        return rcService.getList(mobile, exchangeType, status, expiry, paging);
    }


    public List<RCDTO> findUnexpiredByCustomerId(Integer customerId) {
        List<RCDTO> rcdtoList = rcService.findUnexpiredByCustomerId(customerId);
        return rcdtoList;
    }

    public RCDTO getById(Integer id) {
        return rcService.getById(id);
    }

    public void editSave(RCEditDTO dto) {
        rcService.editSave(dto);
    }

    /**
     * app端根据客户id，和是否可用查询充值卡列表
     *
     * @param customerId
     * @param status     0为所有，1为不可用【包括过期、停用和余额为0】，2为可用
     * @param paging
     * @return
     */
    public List<RCDTO> getListByCustomerId(Integer customerId, Byte status, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        if (status.equals(Byte.valueOf("0"))) {
            List<RCDTO> rcdtoListAll = rcService.getList(customerDTO.getMobile(), null, null, null, paging);
            return rcdtoListAll;
        }
        if (status.equals(Byte.valueOf("1"))) {
            List<RCDTO> rcdtoListAll = rcService.getList(customerDTO.getMobile(), null, null, null, paging);
            List<RCDTO> rcdtoListUnusable = rcdtoListAll.stream()
                    .filter(bean ->
                            bean.getStatus().equals(Byte.valueOf("0"))
                                    || bean.getExpiryTime().compareTo(new Date()) == -1
                                    || bean.getBalance().compareTo(new BigDecimal(0)) == 0
                    ).collect(Collectors.toList());
            paging.setTotal(rcdtoListUnusable.size());
            return rcdtoListUnusable;
        }
        if (status.equals(Byte.valueOf("2"))) {
            List<RCDTO> temp = rcService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
            List<RCDTO> rcdtoListUsable = temp.stream()
                    .filter(bean -> bean.getBalance().compareTo(new BigDecimal(0)) == 1)
                    .collect(Collectors.toList());
            paging.setTotal(rcdtoListUsable.size());
            return rcdtoListUsable;
        }
        return null;
    }

    public List<RCDTO> getListByGoodsAttr(Integer customerId, Integer productId, String cityCode, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        List<RCDTO> temp = rcService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
        List<RCDTO> rcdtoListUsable = temp.stream()
                .filter(bean ->
                        bean.getBalance().compareTo(new BigDecimal(0)) == 1
                                && bean.getCityCodes().contains(cityCode))
                .filter(bean -> productsHasProduct(bean.getProductIds(), productId))
                .collect(Collectors.toList());
        paging.setTotal(rcdtoListUsable.size());
        return rcdtoListUsable;
    }

    public boolean productsHasProduct(String productIds, Integer productId) {
        List<String> result = Arrays.asList(productIds.split(","));
        return result.contains(productId.toString());
    }
}
