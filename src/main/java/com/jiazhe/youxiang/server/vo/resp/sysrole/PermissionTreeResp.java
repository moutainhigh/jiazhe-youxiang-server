package com.jiazhe.youxiang.server.vo.resp.sysrole;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/12/6.
 */
public class PermissionTreeResp extends BaseVO {

    @ApiModelProperty("权限id")
    private Integer id;

    @ApiModelProperty("权限父id")
    private Integer pId;

    @ApiModelProperty("权限名")
    private String name;

    @ApiModelProperty("权限字符串")
    private String perm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
