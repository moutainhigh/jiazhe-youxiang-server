package com.jiazhe.youxiang.server.vo.req.eleproductexcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

/**
 * @author TU
 * @description 导入商品电子兑换码请求
 * @date 2018/10/25.
 */
public class ImportCodeReq extends BaseVO {

    @ApiModelProperty("excel路径")
    private String excelUrl;

    @ApiModelProperty("新批次还是沿用老批次")
    private Byte isNew;

    @ApiModelProperty("批次名称")
    private String batchName;

    @ApiModelProperty("过期时间")
    private Date expiryTime;

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }
}
