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

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/26
 */
public class TreeTest1 {


    public static void main(String[] args) {

        List<Map<String, Object>> dataList = getTestData();
        List<DtsDim> columnDimList = getTestColumnDimList();


        List<HeaderItemDTO> getHeaderColumns = getHeaderColumns(dataList, columnDimList);
        System.out.println(JSONObject.toJSONString(getHeaderColumns));
    }

    private static List<Map<String, Object>> getTestData() {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<String> years = Lists.newArrayList("2017", "2018");
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
        return result;
    }

    private static List<HeaderItemDTO> getHeaderColumns(List<Map<String, Object>> dataList, List<DtsDim> columnDimList) {

        List<String> columnDimFieldList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(columnDimList)) {
            for (DtsDim dimItem : columnDimList) {
                columnDimFieldList.add(dimItem.getDimField());
            }
        }

//        List<String[]> codeList = Lists.newArrayList();
//        for (Map<String, Object> map : dataList) {
//            for (String[] item :
//                    codeList) {
//                if (item.getFieldValue().equals(getCode(map, columnDimFieldList, 0))) {
//                 break;
//                }
//
//            }
//            HeaderItemDTO headerItemDTO = new HeaderItemDTO();
//            headerItemDTO.setFieldValue(getCode(map, columnDimFieldList, 0));
//            headerItemDTOS.add(headerItemDTO);
//        }


        List<TreeNode> treeNodes = Lists.newArrayList();
        for (Map<String, Object> map : dataList) {
            int size = columnDimFieldList.size() - 1;
            List<String> fatherIds = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = new TreeNode();
                treeNode.setName(map.get(columnDimFieldList.get(i)).toString());
                if (i == 0) {
                    treeNode.setParentId(null);
                    fatherIds.add(treeNode.getId());
                } else {
                    treeNode.setParentId(fatherIds.get(i));
                }
                if (!treeNodes.contains(treeNode)) {
                    treeNodes.add(treeNode);
                }
            }
        }
//        return getHeaderColumns(treeNodes);
        return null;
    }

    private static HeaderItemDTO getgetHeaderColumn() {
return null;
    }

    private static String getCode(Map<String, Object> map, List<String> columnDimFieldList, int level) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i <= level; i++) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(map.get(columnDimFieldList.get(level)));
            index++;
        }

        return sb.toString();
    }

//
//    private static List<HeaderItemDTO> getHeaderColumns(List<Map<String, Object>> dataList, List<DtsDim> columnDimList) {
//
//        List<String> columnDimFieldList = Lists.newLinkedList();
//        if (CollectionUtils.isNotEmpty(columnDimList)) {
//            for (DtsDim dimItem : columnDimList) {
//                columnDimFieldList.add(dimItem.getDimField());
//            }
//        }
//        List<TreeNode> treeNodes = Lists.newArrayList();
//        for (Map<String, Object> map : dataList) {
//            int size = columnDimFieldList.size() - 1;
//            List<String> fatherIds = Lists.newArrayList();
//            for (int i = 0; i < size; i++) {
//                TreeNode treeNode = new TreeNode();
//                treeNode.setName(map.get(columnDimFieldList.get(i)).toString());
//                if (i == 0) {
//                    treeNode.setParentId(null);
//                    fatherIds.add(treeNode.getId());
//                } else {
//                    treeNode.setParentId(fatherIds.get(i));
//                }
//                if(!treeNodes.contains(treeNode)){
//                    treeNodes.add(treeNode);
//                }
//            }
//        }
//        return getHeaderColumns(treeNodes);
//    }

}

class TreeNode {
    private String id;
    private String name;
    private String parentId;
    private List<TreeNode> children;

    public TreeNode() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}