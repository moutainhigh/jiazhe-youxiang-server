package com.jiazhe.youxiang.server.vo.req.eleproductcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author tu
 * @description：商品电子码有效期修改
 * @date 2018/10/24
 */
public class ExpiryTimeEditReq extends BaseVO{

    private static final long serialVersionUID = -7527977837155349260L;

    @ApiModelProperty("批次名称")
    private String batchName;

    @ApiModelProperty("新的过期时间")
    private Long expiryTime;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
