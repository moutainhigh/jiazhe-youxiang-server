package com.jiazhe.youxiang.server.vo.page;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by tujia on 2018/10/14.
 */
public class SysRolePageResp extends BaseObject{

    @ApiModelProperty("记录总条数")
    private int totalCount;
    @ApiModelProperty("总页数")
    private int totalPage;
    @ApiModelProperty("当前页")
    private int currPage;
    @ApiModelProperty("本页结果")
    private List<Map> dataRows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<Map> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<Map> dataRows) {
        this.dataRows = dataRows;
    }
}
