package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.resp.djbx.PointsQueryResp;

/**
 * @author TU
 * @description 大家保险
 * @date 2020-05-19.
 */
public class DJBXAdapter {

    public static PointsQueryResp PointQueryDTO2Resp(PointsQueryDTO dto) {
        if (dto == null) {
            return null;
        }
        PointsQueryResp pointsQueryResp = new PointsQueryResp();
        pointsQueryResp.setAgentCode(dto.getAgentCode());
        pointsQueryResp.setPoints(dto.getPoints());
        return pointsQueryResp;
    }
}
