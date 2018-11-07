package com.jiazhe.youxiang.server.service.impl.order;

import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.dao.mapper.OrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
@Service("orderInfoService")
@Transactional(rollbackFor=Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoPOManualMapper orderInfoPOManualMapper;
    @Autowired
    private OrderInfoPOMapper orderInfoPOMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderPaymentService orderPaymentService;

    @Override
    public List<OrderInfoDTO> getList(Byte status, String orderCode, String mobile, Date orderStartTime, Date orderEndTime,String workerMobile, Paging paging) {
        Integer count = orderInfoPOManualMapper.count(status, orderCode,mobile,orderStartTime,orderEndTime,workerMobile);
        List<OrderInfoPO> orderInfoPOList = orderInfoPOManualMapper.query(status, orderCode,mobile,orderStartTime,orderEndTime,workerMobile, paging.getOffset(), paging.getLimit());
        List<OrderInfoDTO> orderInfoDTOList = orderInfoPOList.stream().map(OrderInfoAdapter::PO2DTO).collect(Collectors.toList());
        orderInfoDTOList.forEach(bean->{
            ProductDTO productDTO = productService.getById(bean.getProductId());
            CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
            bean.setProductDTO(productDTO);
            bean.setCustomerDTO(customerDTO);
        });
        paging.setTotal(count);
        return orderInfoDTOList;
    }

    @Override
    public OrderInfoDTO getById(Integer id) {
        OrderInfoPO po = orderInfoPOMapper.selectByPrimaryKey(id);
        OrderInfoDTO dto = OrderInfoAdapter.PO2DTO(po);
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        ProductDTO productDTO = productService.getById(dto.getProductId());
        dto.setCustomerDTO(customerDTO);
        dto.setProductDTO(productDTO);
        return dto;
    }
}
