package com.jiazhe.youxiang.server.vo.resp.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description  中行信用卡（BOCCC）三个实时接口的返回
 * @date 2019-09-06.
 */
public class BOCCCResp extends BaseVO {

    private static final long serialVersionUID = -1724765761268020202L;

    private String stat ;

    private String result ;

    private String date;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
