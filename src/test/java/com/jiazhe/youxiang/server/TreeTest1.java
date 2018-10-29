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
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Map;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/26
 */
public class TreeTest1 {


    public static void main(String[] args) {

        List<Map<String, Object>> dataList = getTestData();
        List<DtsDim> rowDimList = getTestColumnDimList();


        List<HeaderItemDTO> getHeaderColumns = getColumnValues(dataList, rowDimList);
        System.out.println(JSONObject.toJSONString(getHeaderColumns));
    }

    private static List<Map<String, Object>> getTestData() {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<String> years = Lists.newArrayList("2017", "2018","2019");
        List<String> quarters = Lists.newArrayList("Q1", "Q2");
        List<String> months = Lists.newArrayList("1", "2");
        for (String year : years) {
            for (String quarter : quarters) {
                for (String month : months) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("year", year);
                    map.put("quarter", quarter);
                    map.put("month", month);
                    result.add(map);
                    if(quarter.equals("Q1")){
                        Map<String, Object> map1 = Maps.newHashMap();
                        map1.put("year", year);
                        map1.put("quarter", quarter);
                        map1.put("month", "3");
                        result.add(map1);
                    }
                }
            }
        }
        return result;
    }


    private static List<DtsDim> getTestColumnDimList() {
        List<DtsDim> result = Lists.newLinkedList();
        DtsDim dtsDim = new DtsDim();
        dtsDim.setDimField("year");
        result.add(dtsDim);
        DtsDim dtsDim1 = new DtsDim();
        dtsDim1.setDimField("quarter");
        result.add(dtsDim1);
        DtsDim dtsDim2 = new DtsDim();
        dtsDim2.setDimField("month");
        result.add(dtsDim2);
        return result;
    }

    /**
     * 获得列值信息
     * @param dataList 查询得到的数据（包括行维度）
     * @param rowDimList 行维度列表
     * @return
     */
    private static List<HeaderItemDTO> getColumnValues(List<Map<String, Object>> dataList, List<DtsDim> rowDimList) {

        //获得行维度字段名集合
        List<String> columnDimFieldList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(rowDimList)) {
            for (DtsDim dimItem : rowDimList) {
                columnDimFieldList.add(dimItem.getDimField());
            }
        }

        //遍历查询得到的数据，获得HeaderItemDTO的map key是HeaderItemDTO所处的路径
        //map中vaule的数据量和查询得到的数据行数保持一直（不包含合计的情况下）
        Map<String,HeaderItemDTO> flatHeaderItemMap = Maps.newLinkedHashMap();
        for (Map<String, Object> map : dataList) {
            String path = "";
            for (DtsDim dim : rowDimList) {
                HeaderItemDTO item = new HeaderItemDTO();
                item.setFieldValue(map.get(dim.getDimField()).toString());
                item.setParentPath(path);
                path += "," + item.getFieldValue();
                flatHeaderItemMap.put(path,item);
            }
        }
        //遍历刚刚得到的map，利用path将其拼接树形结构
        List<HeaderItemDTO> result = Lists.newLinkedList();
        for (HeaderItemDTO itemDTO : flatHeaderItemMap.values()) {
           if(Strings.isBlank(itemDTO.getParentPath())){
               result.add(itemDTO);
           }else {
               HeaderItemDTO parent = flatHeaderItemMap.get(itemDTO.getParentPath());
               if (parent == null) {
                   System.out.println("有问题：" + JSONObject.toJSONString(itemDTO.getParentPath()));
               }
               if (CollectionUtils.isEmpty(parent.getChildren())) {
                   parent.setChildren(Lists.newLinkedList());
               }
               parent.getChildren().add(itemDTO);
           }
        }
        return result;
    }
}

class TreeNode {
    private String path;
    private String parentpath;
    private String name;
    private List<TreeNode> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentpath() {
        return parentpath;
    }

    public void setParentpath(String parentpath) {
        this.parentpath = parentpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}