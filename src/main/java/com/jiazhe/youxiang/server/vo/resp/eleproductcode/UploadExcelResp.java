package com.jiazhe.youxiang.server.vo.resp.eleproductcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public class UploadExcelResp extends BaseVO {

    private static final long serialVersionUID = -3982593416256463460L;

    @ApiModelProperty("excel路径")
    private String url;

    @ApiModelProperty("电子码数量")
    private Integer count;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
