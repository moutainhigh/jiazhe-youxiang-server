package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public interface SysUserPOManualMapper {

    /**
     * 根据条件获取员工记录条数
     * @param loginName
     * @param displayName
     * @return
     */
    Integer count(@Param("loginName") String loginName, @Param("displayName") String displayName);

    /**
     * 根据查询条件和分页参数获取员工信息
     * @param loginName
     * @param displayName
     * @param offset
     * @param limit
     * @return
     */
    List<SysUserPO> query(@Param("loginName") String loginName, @Param("displayName") String displayName, @Param("offset") Integer offset, @Param("limit") Integer limit);

    void delete(Integer id);

    void insert(SysUserPO sysUserPO);

    List<SysUserPO> findByIds(
            @Param("payeeIds") String payeeIds,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    Integer getCountByIds(
            @Param("payeeIds") String payeeIds);
}
