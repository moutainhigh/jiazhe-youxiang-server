package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.base.util.EncryptPasswordUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserRoleAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.UserException;
import com.jiazhe.youxiang.server.dao.mapper.SysUserPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysUserPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @date 2018/10/15.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserPOMapper sysUserPOMapper;
    @Autowired
    private SysUserPOManualMapper sysUserPOManualMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysUserDTO> findAll() {
        SysUserPOExample sysUserPOExample = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysUserPO> sysUserPOList = sysUserPOMapper.selectByExample(sysUserPOExample);
        List<SysUserDTO> sysUserDTOList = sysUserPOList.stream().map(SysUserAdapter::po2Dto).collect(Collectors.toList());
        List<SysUserDTO> listSorted = sysUserDTOList.stream().sorted(Comparator.comparing(SysUserDTO::getLastLoginTime)).collect(Collectors.toList());
        return listSorted;
    }

    @Override
    public List<SysUserDTO> getList(String loginName, String displayName, Paging paging) {
        Integer count = sysUserPOManualMapper.count(loginName, displayName);
        List<SysUserPO> sysUserPOList = sysUserPOManualMapper.query(loginName, displayName, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return sysUserPOList.stream().map(SysUserAdapter::po2Dto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserWithRole(Integer userId) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(userId);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        sysUserPO.setIsDeleted(CommonConstant.CODE_DELETED);
        sysUserPOMapper.updateByPrimaryKey(sysUserPO);
        List<SysUserRoleDTO> sysUserRoleDTOList = sysUserRoleService.findByUserId(userId);
        if (!CollectionUtils.isEmpty(sysUserRoleDTOList)) {
            List<Integer> ids = sysUserRoleDTOList.stream().map(SysUserRoleDTO::getId).collect(Collectors.toList());
            sysUserRoleService.batchDelete(ids);
        }
    }

    @Override
    public UserWithRoleDTO findUserWithRoleById(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        List<SysUserRoleDTO> sysUserRoleDTOList = sysUserRoleService.findByUserId(id);
        StringBuilder roleIds = new StringBuilder();
        for (SysUserRoleDTO dto : sysUserRoleDTOList) {
            roleIds.append(dto.getRoleId() + ",");
        }
        if (sysUserRoleDTOList.size() > 0) {
            roleIds.deleteCharAt(roleIds.length() - 1);
        }
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(sysUserPO.getId());
        userWithRoleDTO.setMobile(sysUserPO.getMobile());
        userWithRoleDTO.setLoginName(sysUserPO.getLoginname());
        userWithRoleDTO.setDisplayName(sysUserPO.getDisplayname());
        userWithRoleDTO.setRoleIds(roleIds.toString());
        return userWithRoleDTO;
    }

    @Override
    public SysUserDTO findById(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        return SysUserAdapter.po2Dto(sysUserPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserWithRole(boolean isAdd, SysUserDTO sysUserDTO, List<SysUserRoleDTO> newRolesDto, List<SysUserRoleDTO> oldRolesDto) {
        SysUserPO sysUserPO = SysUserAdapter.dto2Po(sysUserDTO);
        sysUserPO.setModTime(new Date());
        List<SysUserRolePO> newPermsPO = newRolesDto.stream().map(SysUserRoleAdapter::DTO2PO).collect(Collectors.toList());
        if (isAdd) {
            sysUserPO.setRemark("");
            sysUserPO.setExtInfo("");
            sysUserPO.setAddTime(new Date());
            sysUserPO.setIsDeleted(Byte.valueOf("0"));
            sysUserPO.setLastLoginTime(new Date());
            sysUserPOManualMapper.insert(sysUserPO);
        } else {
            sysUserPOMapper.updateByPrimaryKeySelective(sysUserPO);
        }
        if (!CollectionUtils.isEmpty(newPermsPO)) {
            for (SysUserRolePO newPO : newPermsPO) {
                newPO.setUserId(sysUserPO.getId());
                newPO.setIsDeleted(Byte.valueOf("0"));
                newPO.setExtInfo("");
                newPO.setAddTime(new Date());
                newPO.setModTime(new Date());
            }
            sysUserRoleService.batchInsert(newPermsPO);
        }
        if (!CollectionUtils.isEmpty(oldRolesDto)) {
            List<Integer> oldIds = oldRolesDto.stream().map(SysUserRoleDTO::getId).collect(Collectors.toList());
            sysUserRoleService.batchDelete(oldIds);
        }
    }

    @Override
    public List<SysUserDTO> findByLoginName(String loginName) {
        SysUserPOExample sysUserPOExample = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysUserPO> sysUserPOList = sysUserPOMapper.selectByExample(sysUserPOExample);
        return sysUserPOList.stream().map(SysUserAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public void updateLastLoginInfo(Integer userId, String ipAdrress) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(userId);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        if (sysUserPO.getLastLoginIp().contains(ipAdrress)) {
            ipAdrress = "";
        } else {
            ipAdrress = ipAdrress + ";";
        }
        String newIpAddress = new String(ipAdrress + sysUserPO.getLastLoginIp());
        String[] newIpAddressAttr = newIpAddress.toString().split(";");
        if (newIpAddressAttr.length > CommonConstant.IP_WHITE_LIST_UPPER_LIMIT) {
            StringBuilder ip = new StringBuilder("");
            for (int i = 0; i < CommonConstant.IP_WHITE_LIST_UPPER_LIMIT; i++) {
                ip.append(newIpAddressAttr[i] + ";");
            }
            sysUserPO.setLastLoginIp(ip.toString());
        } else {
            sysUserPO.setLastLoginIp(newIpAddress);
        }
        sysUserPO.setLastLoginTime(new Date());
        sysUserPOMapper.updateByPrimaryKeySelective(sysUserPO);
    }

    @Override
    public void changePassword(Integer id, String newPassword) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        String salt = RandomUtil.generateSalt(6);
        sysUserPO.setSalt(salt);
        sysUserPO.setPassword(EncryptPasswordUtil.encrypt(salt, newPassword));
        sysUserPOMapper.updateByPrimaryKeySelective(sysUserPO);
    }

    @Override
    public SysUserDTO getById(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        if (null == sysUserPO) {
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        return SysUserAdapter.po2Dto(sysUserPO);
    }

    @Override
    public List<SysUserDTO> findByIds(String ids, Paging paging) {
        List<SysUserPO> sysUserPOList = sysUserPOManualMapper.findByIds(ids, paging.getOffset(), paging.getLimit());
        return sysUserPOList.stream().map(SysUserAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public Integer getCountByIds(String ids) {
        return sysUserPOManualMapper.getCountByIds(ids);
    }


}
