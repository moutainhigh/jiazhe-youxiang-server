package com.jiazhe.youxiang.server.service.impl.point;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePOExample;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
@Service("pointExchangeCodeService")
public class PointExchangeCodeServiceImpl implements PointExchangeCodeService {

}
