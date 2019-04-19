package com.jiazhe.youxiang.server.domain.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageTemplatePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageTemplatePOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParamCountIsNull() {
            addCriterion("param_count is null");
            return (Criteria) this;
        }

        public Criteria andParamCountIsNotNull() {
            addCriterion("param_count is not null");
            return (Criteria) this;
        }

        public Criteria andParamCountEqualTo(Integer value) {
            addCriterion("param_count =", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountNotEqualTo(Integer value) {
            addCriterion("param_count <>", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountGreaterThan(Integer value) {
            addCriterion("param_count >", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("param_count >=", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountLessThan(Integer value) {
            addCriterion("param_count <", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountLessThanOrEqualTo(Integer value) {
            addCriterion("param_count <=", value, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountIn(List<Integer> values) {
            addCriterion("param_count in", values, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountNotIn(List<Integer> values) {
            addCriterion("param_count not in", values, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountBetween(Integer value1, Integer value2) {
            addCriterion("param_count between", value1, value2, "paramCount");
            return (Criteria) this;
        }

        public Criteria andParamCountNotBetween(Integer value1, Integer value2) {
            addCriterion("param_count not between", value1, value2, "paramCount");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeIsNull() {
            addCriterion("ali_template_code is null");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeIsNotNull() {
            addCriterion("ali_template_code is not null");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeEqualTo(String value) {
            addCriterion("ali_template_code =", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeNotEqualTo(String value) {
            addCriterion("ali_template_code <>", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeGreaterThan(String value) {
            addCriterion("ali_template_code >", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ali_template_code >=", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeLessThan(String value) {
            addCriterion("ali_template_code <", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeLessThanOrEqualTo(String value) {
            addCriterion("ali_template_code <=", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeLike(String value) {
            addCriterion("ali_template_code like", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeNotLike(String value) {
            addCriterion("ali_template_code not like", value, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeIn(List<String> values) {
            addCriterion("ali_template_code in", values, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeNotIn(List<String> values) {
            addCriterion("ali_template_code not in", values, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeBetween(String value1, String value2) {
            addCriterion("ali_template_code between", value1, value2, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateCodeNotBetween(String value1, String value2) {
            addCriterion("ali_template_code not between", value1, value2, "aliTemplateCode");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentIsNull() {
            addCriterion("ali_template_content is null");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentIsNotNull() {
            addCriterion("ali_template_content is not null");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentEqualTo(String value) {
            addCriterion("ali_template_content =", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentNotEqualTo(String value) {
            addCriterion("ali_template_content <>", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentGreaterThan(String value) {
            addCriterion("ali_template_content >", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentGreaterThanOrEqualTo(String value) {
            addCriterion("ali_template_content >=", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentLessThan(String value) {
            addCriterion("ali_template_content <", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentLessThanOrEqualTo(String value) {
            addCriterion("ali_template_content <=", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentLike(String value) {
            addCriterion("ali_template_content like", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentNotLike(String value) {
            addCriterion("ali_template_content not like", value, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentIn(List<String> values) {
            addCriterion("ali_template_content in", values, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentNotIn(List<String> values) {
            addCriterion("ali_template_content not in", values, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentBetween(String value1, String value2) {
            addCriterion("ali_template_content between", value1, value2, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andAliTemplateContentNotBetween(String value1, String value2) {
            addCriterion("ali_template_content not between", value1, value2, "aliTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdIsNull() {
            addCriterion("tencent_template_id is null");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdIsNotNull() {
            addCriterion("tencent_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdEqualTo(Integer value) {
            addCriterion("tencent_template_id =", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdNotEqualTo(Integer value) {
            addCriterion("tencent_template_id <>", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdGreaterThan(Integer value) {
            addCriterion("tencent_template_id >", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tencent_template_id >=", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdLessThan(Integer value) {
            addCriterion("tencent_template_id <", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("tencent_template_id <=", value, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdIn(List<Integer> values) {
            addCriterion("tencent_template_id in", values, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdNotIn(List<Integer> values) {
            addCriterion("tencent_template_id not in", values, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("tencent_template_id between", value1, value2, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tencent_template_id not between", value1, value2, "tencentTemplateId");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentIsNull() {
            addCriterion("tencent_template_content is null");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentIsNotNull() {
            addCriterion("tencent_template_content is not null");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentEqualTo(String value) {
            addCriterion("tencent_template_content =", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentNotEqualTo(String value) {
            addCriterion("tencent_template_content <>", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentGreaterThan(String value) {
            addCriterion("tencent_template_content >", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentGreaterThanOrEqualTo(String value) {
            addCriterion("tencent_template_content >=", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentLessThan(String value) {
            addCriterion("tencent_template_content <", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentLessThanOrEqualTo(String value) {
            addCriterion("tencent_template_content <=", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentLike(String value) {
            addCriterion("tencent_template_content like", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentNotLike(String value) {
            addCriterion("tencent_template_content not like", value, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentIn(List<String> values) {
            addCriterion("tencent_template_content in", values, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentNotIn(List<String> values) {
            addCriterion("tencent_template_content not in", values, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentBetween(String value1, String value2) {
            addCriterion("tencent_template_content between", value1, value2, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andTencentTemplateContentNotBetween(String value1, String value2) {
            addCriterion("tencent_template_content not between", value1, value2, "tencentTemplateContent");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateIsNull() {
            addCriterion("excel_template is null");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateIsNotNull() {
            addCriterion("excel_template is not null");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateEqualTo(String value) {
            addCriterion("excel_template =", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateNotEqualTo(String value) {
            addCriterion("excel_template <>", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateGreaterThan(String value) {
            addCriterion("excel_template >", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("excel_template >=", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateLessThan(String value) {
            addCriterion("excel_template <", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateLessThanOrEqualTo(String value) {
            addCriterion("excel_template <=", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateLike(String value) {
            addCriterion("excel_template like", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateNotLike(String value) {
            addCriterion("excel_template not like", value, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateIn(List<String> values) {
            addCriterion("excel_template in", values, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateNotIn(List<String> values) {
            addCriterion("excel_template not in", values, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateBetween(String value1, String value2) {
            addCriterion("excel_template between", value1, value2, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andExcelTemplateNotBetween(String value1, String value2) {
            addCriterion("excel_template not between", value1, value2, "excelTemplate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andExtInfoIsNull() {
            addCriterion("ext_info is null");
            return (Criteria) this;
        }

        public Criteria andExtInfoIsNotNull() {
            addCriterion("ext_info is not null");
            return (Criteria) this;
        }

        public Criteria andExtInfoEqualTo(String value) {
            addCriterion("ext_info =", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotEqualTo(String value) {
            addCriterion("ext_info <>", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoGreaterThan(String value) {
            addCriterion("ext_info >", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoGreaterThanOrEqualTo(String value) {
            addCriterion("ext_info >=", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLessThan(String value) {
            addCriterion("ext_info <", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLessThanOrEqualTo(String value) {
            addCriterion("ext_info <=", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLike(String value) {
            addCriterion("ext_info like", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotLike(String value) {
            addCriterion("ext_info not like", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoIn(List<String> values) {
            addCriterion("ext_info in", values, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotIn(List<String> values) {
            addCriterion("ext_info not in", values, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoBetween(String value1, String value2) {
            addCriterion("ext_info between", value1, value2, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotBetween(String value1, String value2) {
            addCriterion("ext_info not between", value1, value2, "extInfo");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNull() {
            addCriterion("mod_time is null");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNotNull() {
            addCriterion("mod_time is not null");
            return (Criteria) this;
        }

        public Criteria andModTimeEqualTo(Date value) {
            addCriterion("mod_time =", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotEqualTo(Date value) {
            addCriterion("mod_time <>", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThan(Date value) {
            addCriterion("mod_time >", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mod_time >=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThan(Date value) {
            addCriterion("mod_time <", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThanOrEqualTo(Date value) {
            addCriterion("mod_time <=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIn(List<Date> values) {
            addCriterion("mod_time in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotIn(List<Date> values) {
            addCriterion("mod_time not in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeBetween(Date value1, Date value2) {
            addCriterion("mod_time between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotBetween(Date value1, Date value2) {
            addCriterion("mod_time not between", value1, value2, "modTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}