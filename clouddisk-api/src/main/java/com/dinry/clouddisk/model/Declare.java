package com.dinry.clouddisk.model;

import java.util.Date;
import javax.persistence.*;

public class Declare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String context;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 覆盖的范围0 ：所有 1：学院 2：专业 3：班级
     */
    @Column(name = "field_name")
    private Integer fieldName;

    /**
     * 覆盖范围对应的id
     */
    @Column(name = "field_id")
    private Integer fieldId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取覆盖的范围0 ：所有 1：学院 2：专业 3：班级
     *
     * @return field_name - 覆盖的范围0 ：所有 1：学院 2：专业 3：班级
     */
    public Integer getFieldName() {
        return fieldName;
    }

    /**
     * 设置覆盖的范围0 ：所有 1：学院 2：专业 3：班级
     *
     * @param fieldName 覆盖的范围0 ：所有 1：学院 2：专业 3：班级
     */
    public void setFieldName(Integer fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 获取覆盖范围对应的id
     *
     * @return field_id - 覆盖范围对应的id
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * 设置覆盖范围对应的id
     *
     * @param fieldId 覆盖范围对应的id
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }
}