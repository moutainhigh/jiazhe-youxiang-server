/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/24
 */
public class Test1 {

    public static void main(String[] args) {


//        Map<String, List<String>> dimValueListMap = Maps.newLinkedHashMap();
//
//        dimValueListMap.put("subject", Lists.newArrayList("语文", "数学"));
//        dimValueListMap.put("name", Lists.newArrayList("张三", "李四"));
//
//        dimValueListMap.put("year", Lists.newArrayList("2018", "2019"));
////        dimValueListMap.put("季度", Lists.newArrayList("一季度", "二季度", "三季度"));
//
//
//        /**组装sql**/
//        DtsDataSource dataSource = getTestDataSource();
//
//        List<DtsDim> columnDimList = getTestColumnDimList();
//        List<DtsKPI> kpiList = getTestKpiList();
//        Map<String, String> searchMap = getTestSearchMap();
//
//
//        List<HeaderItemDTO> result = getHeaderRows(dimValueListMap);
//
//
//        String sql = buildSQL(dataSource, result, columnDimList, kpiList, searchMap);
//
//
//        System.out.println(JSONObject.toJSONString(result));
//        System.out.println(JSONObject.toJSONString(sql));

    }


    private static List<HeaderItemDTO> getHeaderRows(Map<String, List<String>> dimValueListMap) {
        List<HeaderItemDTO> result = Lists.newArrayList();
        List<List<HeaderItemDTO>> headerRowsList = getHeaderRowsList(dimValueListMap);

        if (CollectionUtils.isNotEmpty(headerRowsList)) {
            //获取第一行表头（根节点）
            result = headerRowsList.get(0);
            for (int i = 1; i < headerRowsList.size(); i++) {
                setChildren(headerRowsList.get(i - 1), headerRowsList.get(i));
            }
        }
        return result;

    }

    private static List<HeaderItemDTO> setChildren(List<HeaderItemDTO> fathers, List<HeaderItemDTO> children) {
        if (CollectionUtils.isNotEmpty(fathers)) {
            for (HeaderItemDTO father : fathers) {
                father.setChildren(children);
            }
        }
        return fathers;
    }

    private static List<List<HeaderItemDTO>> getHeaderRowsList(Map<String, List<String>> dimValueListMap) {
        List<List<HeaderItemDTO>> result = Lists.newArrayList();
        if (MapUtils.isNotEmpty(dimValueListMap)) {
            for (String field : dimValueListMap.keySet()) {
                result.add(getHeaderRows(field, dimValueListMap.get(field)));
            }

        }
        return result;
    }

    private static List<HeaderItemDTO> getHeaderRows(String field, List<String> dimValueList) {
        List<HeaderItemDTO> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dimValueList)) {
            for (String dimValue : dimValueList) {
                HeaderItemDTO headerItemDTO = new HeaderItemDTO();
                headerItemDTO.setField(field);
                headerItemDTO.setFieldValue(dimValue);
                result.add(headerItemDTO);
            }
        }
        return result;
    }


    /********/

    private static DtsDataSource getTestDataSource() {
        DtsDataSource dtsDataSource = new DtsDataSource();
        dtsDataSource.setDsTable("C");
        return dtsDataSource;
    }

    private static List<DtsDim> getTestColumnDimList() {
        List<DtsDim> result = Lists.newLinkedList();
        DtsDim dtsDim = new DtsDim();
        dtsDim.setDimField("year");
        result.add(dtsDim);
        DtsDim dtsDim1 = new DtsDim();
        dtsDim1.setDimField("quarter");
        result.add(dtsDim1);
        return result;
    }

    private static List<DtsKPI> getTestKpiList() {
        List<DtsKPI> result = Lists.newArrayList();
        DtsKPI dtsKPI = new DtsKPI();
        dtsKPI.setKpiField("result");
        result.add(dtsKPI);
        return result;
    }

    private static Map<String, String> getTestSearchMap() {
        Map<String, String> result = Maps.newHashMap();
        result.put("year", "2018");
        return result;
    }

    private static String buildSQL(DtsDataSource dts, List<HeaderItemDTO> headerRows, List<DtsDim> columnDimList, List<DtsKPI> kpiList, Map<String, String> searchMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        //展开表头，并获得表头展开后的headerItemDTO集合，如表头是
        List<List<HeaderItemDTO>> flatList = flatHeaderRows(headerRows);

        //根据表头信息拼接查询项
        if (CollectionUtils.isNotEmpty(flatList)) {
            int index = 0;
            for (List<HeaderItemDTO> item :
                    flatList) {
                if (index > 0) {
                    sb.append(",");
                }
                sb.append(buildDim(item, kpiList));
                index++;
            }
        }
        sb.append("FROM ").append(dts.getDsTable()).append(" a ");
        if (MapUtils.isNotEmpty(searchMap)) {
            int index = 0;
            sb.append("WHERE ");
            for (String key : searchMap.keySet()) {
                if (index > 0) {
                    sb.append(" AND ");
                }
                sb.append(key).append("=").append("'").append(searchMap.get(key)).append("'");
                index++;
            }
        }
        sb.append(" GROUP BY ");
        if (CollectionUtils.isNotEmpty(columnDimList)) {
            int index = 0;
            for (DtsDim columnDim : columnDimList) {
                if (index > 0) {
                    sb.append(" , ");
                }
                sb.append(columnDim.getDimField());
                index++;

            }
        }
        return sb.toString();
    }


    private static String buildDim(List<HeaderItemDTO> headerRowList, List<DtsKPI> kpiList) {
        int index = 0;
        int dimIndex = 0;
        StringBuilder sb = new StringBuilder();
        for (DtsKPI kpi : kpiList) {
            if (dimIndex > 0) {
                sb.append(",");
            }
            sb.append(" sum(case when ");
            for (HeaderItemDTO item : headerRowList) {
                if (index > 0) {
                    sb.append(" and ");
                }
                sb.append(" a.").append(item.getField()).append("='").append(item.getFieldValue()).append("'");
                index++;
            }
            index = 0;
            sb.append(" then ").append(kpi.getKpiField()).append(" else null end) as ").append("'").append(getHeaderRowsCode(headerRowList)).append("'");
            dimIndex++;
        }
        return sb.toString();
    }

    private static String getHeaderRowsCode(List<HeaderItemDTO> headerRowList) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (HeaderItemDTO item : headerRowList) {
            if (index > 0) {
                sb.append("-");
            }
            sb.append(item.getFieldValue());
            index++;
        }
        return sb.toString();
    }


    /**
     * 展开表头，并获得表头展开后的HeaderItemDTO集合（没有children）
     * 2018        2017
     * Q1 Q2 Q3    Q1 Q2 Q3
     * 展开后的list是{[2018,Q1],[2018,Q2],[2018,Q3],[2017,Q1],[2017,Q2],[2017,Q3]}
     *
     * @param headerRows
     * @return
     */
    private static List<List<HeaderItemDTO>> flatHeaderRows(List<HeaderItemDTO> headerRows) {
        List<List<HeaderItemDTO>> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(headerRows)) {
            for (HeaderItemDTO item : headerRows) {
                flatHeaderRows(item, null, result);
            }
        }
        return result;
    }

    /**
     * 展开表头，并获得表头展开后的HeaderItemDTO集合（没有children）
     * 2018        2017
     * Q1 Q2 Q3    Q1 Q2 Q3
     * 展开后的list是{[2018,Q1],[2018,Q2],[2018,Q3],[2017,Q1],[2017,Q2],[2017,Q3]}
     *
     * @param item
     * @param itemList
     * @param result
     */
    private static void flatHeaderRows(HeaderItemDTO item, List<HeaderItemDTO> itemList, List<List<HeaderItemDTO>> result) {

        List<HeaderItemDTO> cloneItemList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(itemList)) {
            for (HeaderItemDTO itemDTO : itemList) {
                cloneItemList.add(itemDTO);
            }
        }
        HeaderItemDTO dto = new HeaderItemDTO();
        dto.setField(item.getField());
        dto.setFieldValue(item.getFieldValue());
        cloneItemList.add(dto);
        if (item != null && CollectionUtils.isNotEmpty(item.getChildren())) {
            for (HeaderItemDTO child : item.getChildren()) {
                flatHeaderRows(child, cloneItemList, result);
            }
        } else {
            result.add(cloneItemList);
        }
    }


    /*******/
    private static List<HeaderItemDTO> getHeaderColumns(List<Map<String, Object>> dataList, List<DtsDim> columnDimList) {
        // TODO niexiao 获取列名信息
        //TODO niexiao 考虑合计问题

        List<String> columnDimFieldList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(columnDimList)) {
            for (DtsDim dimItem : columnDimList) {
                columnDimFieldList.add(dimItem.getDimField());
            }
        }
        List<HeaderItemDTO> result = getHeaderColumns(null, dataList, columnDimFieldList, 0);

        return result;
    }

    private static List<HeaderItemDTO> getHeaderColumns(List<HeaderItemDTO> fathers, List<Map<String, Object>> dataList, List<String> columnDimFieldList, int level) {
        List<HeaderItemDTO> result = Lists.newArrayList();
        for (Map<String, Object> map : dataList) {
            for (HeaderItemDTO headerItemDTO : result) {
//                headerItemDTO.setFieldValue();
            }
//            if (map.get(columnDimFieldList.get(level)).toString())
        }
        return result;
    }

    private static HeaderItemDTO getHeaderColumn(List<HeaderItemDTO> fathers, Map<String, Object> map, List<String> columnDimFieldList, int level) {
        HeaderItemDTO headerItemDTO = null;
        if (CollectionUtils.isEmpty(fathers)) {
            fathers = Lists.newLinkedList();
        }
        for (HeaderItemDTO father : fathers) {
            if (father.getFieldValue().equals(columnDimFieldList.get(level))) {
                headerItemDTO = father;
                break;
            }
        }
        if (null == headerItemDTO) {
            headerItemDTO = new HeaderItemDTO();
            headerItemDTO.setFieldValue(map.get(columnDimFieldList.get(level)).toString());
        }
        if (CollectionUtils.isEmpty(headerItemDTO.getChildren())) {
            headerItemDTO.setChildren(Lists.<HeaderItemDTO>newLinkedList());
        }
        if (level < columnDimFieldList.size() - 1) {
//            headerItemDTO.getChildren().addAll(getHeaderColumn(headerItemDTO.getChildren(), map, columnDimFieldList, level + 1));
        }
        return null;
    }
}



class KeyValuleDTO {

    /**
     * 字段的名称(不是注释）
     */
    private String field;
    /**
     * 字段的具体值
     */
    private String fieldValue;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}

class HeaderItemDTO {

    /**
     * 字段的名称(不是注释）
     */
    private String field;
    /**
     * 字段的具体值
     */
    private String fieldValue;

    /**
     * 字段的具体值
     */
    private Integer level;

    /**
     * 字段的具体值
     */
    private String parentPath;
    /**
     * 子节点
     */
    private List<HeaderItemDTO> children;


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public List<HeaderItemDTO> getChildren() {
        return children;
    }

    public void setChildren(List<HeaderItemDTO> children) {
        this.children = children;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}

class DtsDim {

    private int dimId;
    private String dimField;
    private String dimFieldName;
    private int dsId;
    private String dimTable;
    private String dimRelateField;
    private String dimOrderField;
    private String dimShowField;
    private String dimOrder;
    private String memo;
    private Timestamp ctime;
    private Timestamp utime;
    private String userName;
    private Long userId;

    public int getDimId() {
        return dimId;
    }

    public void setDimId(int dimId) {
        this.dimId = dimId;
    }

    public String getDimField() {
        return dimField;
    }

    public void setDimField(String dimField) {
        this.dimField = dimField;
    }

    public String getDimFieldName() {
        return dimFieldName;
    }

    public void setDimFieldName(String dimFieldName) {
        this.dimFieldName = dimFieldName;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public String getDimTable() {
        return dimTable;
    }

    public void setDimTable(String dimTable) {
        this.dimTable = dimTable;
    }

    public String getDimRelateField() {
        return dimRelateField;
    }

    public void setDimRelateField(String dimRelateField) {
        this.dimRelateField = dimRelateField;
    }

    public String getDimOrderField() {
        return dimOrderField;
    }

    public void setDimOrderField(String dimOrderField) {
        this.dimOrderField = dimOrderField;
    }

    public String getDimShowField() {
        return dimShowField;
    }

    public void setDimShowField(String dimShowField) {
        this.dimShowField = dimShowField;
    }

    public String getDimOrder() {
        return dimOrder;
    }

    public void setDimOrder(String dimOrder) {
        this.dimOrder = dimOrder;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DtsDim [dimId=" + dimId + ", dimField=" + dimField + ", dimFieldName=" + dimFieldName + ", dsId=" + dsId
                + ", dimTable=" + dimTable + ", dimRelateField=" + dimRelateField + ", dimOrderField=" + dimOrderField
                + ", dimShowField=" + dimShowField + ", dimOrder=" + dimOrder + ", memo=" + memo + ", ctime=" + ctime
                + ", utime=" + utime + ", userName=" + userName + ", userId=" + userId + "]";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}

class DtsKPI {
    private int kpiId;
    @Min(value = 1, message = "请正确设置指标名称")
    private int staticId;
    private String kpiField;
    private int dsId;
    @NotNull(message = "计算公式不能为空")
    @Size(min = 2, message = "请正确填写计算公式")
    private String kpiFormula;
    private String kpiExtention;
    private int isNumber = 1;
    private String userName;
    private Long userId;
    private Timestamp ctime;
    private Timestamp utime;

    private String KpiName;

    public int getKpiId() {
        return kpiId;
    }

    public void setKpiId(int kpiId) {
        this.kpiId = kpiId;
    }

    public int getStaticId() {
        return staticId;
    }

    public void setStaticId(int staticId) {
        this.staticId = staticId;
    }

    public String getKpiField() {
        return kpiField;
    }

    public void setKpiField(String kpiField) {
        this.kpiField = kpiField;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }

    public String getKpiFormula() {
        return kpiFormula;
    }

    public void setKpiFormula(String kpiFormula) {
        this.kpiFormula = kpiFormula;
    }

    public String getKpiExtention() {
        return kpiExtention;
    }

    public void setKpiExtention(String kpiExtention) {
        this.kpiExtention = kpiExtention;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKpiName() {
        return KpiName;
    }

    public void setKpiName(String kpiName) {
        KpiName = kpiName;
    }

    public int getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(int isNumber) {
        this.isNumber = isNumber;
    }

    @Override
    public String toString() {
        return "DtsKPI [kpiId=" + kpiId + ", staticId=" + staticId + ", kpiField=" + kpiField + ", dsId=" + dsId
                + ", kpiFormula=" + kpiFormula + ", kpiExtention=" + kpiExtention + ", userName=" + userName + "isNumber=" + isNumber
                + ", userId=" + userId + ", ctime=" + ctime + ", utime=" + utime + "]";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

class DtsDataSource {

    private int dsId;
    private String dts;
    private String dsTable;
    private String dsEtl;
    private String etlOwner;
    private String authField;
    private String authAttr;
    private String memo;
    private Timestamp ctime;
    private Timestamp utime;
    private String dsTableName;
    private String userName;
    private Long userId;

    public String getDts() {
        return dts;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }

    public String getDsTable() {
        return dsTable;
    }

    public void setDsTable(String dsTable) {
        this.dsTable = dsTable;
    }

    public String getDsEtl() {
        return dsEtl;
    }

    public void setDsEtl(String dsEtl) {
        this.dsEtl = dsEtl;
    }

    public String getEtlOwner() {
        return etlOwner;
    }

    public void setEtlOwner(String etlOwner) {
        this.etlOwner = etlOwner;
    }

    public String getAuthField() {
        return authField;
    }

    public void setAuthField(String authField) {
        this.authField = authField;
    }

    public String getAuthAttr() {
        return authAttr;
    }

    public void setAuthAttr(String authAttr) {
        this.authAttr = authAttr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public String getDsTableName() {
        return dsTableName;
    }

    public void setDsTableName(String dsTableName) {
        this.dsTableName = dsTableName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DtsDataSource [dsId=" + dsId + ", dts=" + dts + ", dsTable=" + dsTable + ", dsEtl=" + dsEtl
                + ", etlOwner=" + etlOwner + ", authField=" + authField + ", authAttr=" + authAttr + ", memo=" + memo
                + ", ctime=" + ctime + ", utime=" + utime + ", dsTableName=" + dsTableName + ", userName=" + userName
                + ", userId=" + userId + "]";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}