package com.jiazhe.youxiang.server.vo.req.eleproductexcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class BatchNamePageReq extends PageSizeNumReq {

    private static final long serialVersionUID = -1761724421448178065L;

    @ApiModelProperty("批次名称")
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
