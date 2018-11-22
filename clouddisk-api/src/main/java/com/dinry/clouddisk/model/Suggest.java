package com.dinry.clouddisk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

public class Suggest {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String context;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    private String reply;

    @Column(name = "admin_id")
    private Integer adminId;

    private Integer status;

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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * @param reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * @return admin_id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}