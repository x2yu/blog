package cn.x2yu.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryPictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CategoryPictureExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategory_idIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategory_idIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategory_idEqualTo(Long value) {
            addCriterion("category_id =", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idGreaterThan(Long value) {
            addCriterion("category_id >", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idLessThan(Long value) {
            addCriterion("category_id <", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idIn(List<Long> values) {
            addCriterion("category_id in", values, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "category_id");
            return (Criteria) this;
        }

        public Criteria andCategory_idNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "category_id");
            return (Criteria) this;
        }

        public Criteria andPicture_urlIsNull() {
            addCriterion("picture_url is null");
            return (Criteria) this;
        }

        public Criteria andPicture_urlIsNotNull() {
            addCriterion("picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicture_urlEqualTo(String value) {
            addCriterion("picture_url =", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlNotEqualTo(String value) {
            addCriterion("picture_url <>", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlGreaterThan(String value) {
            addCriterion("picture_url >", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlGreaterThanOrEqualTo(String value) {
            addCriterion("picture_url >=", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlLessThan(String value) {
            addCriterion("picture_url <", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlLessThanOrEqualTo(String value) {
            addCriterion("picture_url <=", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlLike(String value) {
            addCriterion("picture_url like", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlNotLike(String value) {
            addCriterion("picture_url not like", value, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlIn(List<String> values) {
            addCriterion("picture_url in", values, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlNotIn(List<String> values) {
            addCriterion("picture_url not in", values, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlBetween(String value1, String value2) {
            addCriterion("picture_url between", value1, value2, "picture_url");
            return (Criteria) this;
        }

        public Criteria andPicture_urlNotBetween(String value1, String value2) {
            addCriterion("picture_url not between", value1, value2, "picture_url");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_byEqualTo(Date value) {
            addCriterion("create_by =", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotEqualTo(Date value) {
            addCriterion("create_by <>", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThan(Date value) {
            addCriterion("create_by >", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThanOrEqualTo(Date value) {
            addCriterion("create_by >=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThan(Date value) {
            addCriterion("create_by <", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThanOrEqualTo(Date value) {
            addCriterion("create_by <=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byIn(List<Date> values) {
            addCriterion("create_by in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotIn(List<Date> values) {
            addCriterion("create_by not in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byBetween(Date value1, Date value2) {
            addCriterion("create_by between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotBetween(Date value1, Date value2) {
            addCriterion("create_by not between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andModified_byIsNull() {
            addCriterion("modified_by is null");
            return (Criteria) this;
        }

        public Criteria andModified_byIsNotNull() {
            addCriterion("modified_by is not null");
            return (Criteria) this;
        }

        public Criteria andModified_byEqualTo(Date value) {
            addCriterion("modified_by =", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byNotEqualTo(Date value) {
            addCriterion("modified_by <>", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byGreaterThan(Date value) {
            addCriterion("modified_by >", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_by >=", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byLessThan(Date value) {
            addCriterion("modified_by <", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byLessThanOrEqualTo(Date value) {
            addCriterion("modified_by <=", value, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byIn(List<Date> values) {
            addCriterion("modified_by in", values, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byNotIn(List<Date> values) {
            addCriterion("modified_by not in", values, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byBetween(Date value1, Date value2) {
            addCriterion("modified_by between", value1, value2, "modified_by");
            return (Criteria) this;
        }

        public Criteria andModified_byNotBetween(Date value1, Date value2) {
            addCriterion("modified_by not between", value1, value2, "modified_by");
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