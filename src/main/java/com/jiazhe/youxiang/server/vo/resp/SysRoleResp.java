package com.jiazhe.youxiang.server.vo.resp;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by tujia on 2018/10/14.
 */
public class SysRoleResp extends BaseObject{

    @ApiModelProperty("接口请求返回代码")
    private String code;
    @ApiModelProperty("接口请求返回消息")
    private int msg;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色排序")
    private int priority;
    @ApiModelProperty("权限字符串list")
    private List<String> perStrList;
    @ApiModelProperty("分页结果")
    private JSONObject pageObject;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<String> getPerStrList() {
        return perStrList;
    }

    public void setPerStrList(List<String> perStrList) {
        this.perStrList = perStrList;
    }

    public JSONObject getPageObject() {
        return pageObject;
    }

    public void setPageObject(JSONObject pageObject) {
        this.pageObject = pageObject;
    }
}
