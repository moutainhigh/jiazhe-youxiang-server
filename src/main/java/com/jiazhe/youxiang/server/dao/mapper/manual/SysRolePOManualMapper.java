package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author TU
 * @date 2018/10/19
 */
public interface SysRolePOManualMapper {

    /**
     * 插入实体，并将id返回到sysRolePO中
     *
     * @param sysRolePO
     * @return
     */
    void insert(SysRolePO sysRolePO);

    /**
     * 计数
     *
     * @param name
     * @return
     */
    Integer count(@Param("name") String name);

    /**
     * 根据参数查询分页结果
     *
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    List<SysRolePO> query(@Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 单个删除角色，不删除权限
     *
     * @param id
     * @return
     */
    void delete(Integer id);
}
