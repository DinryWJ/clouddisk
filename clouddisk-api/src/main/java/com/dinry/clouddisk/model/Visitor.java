package com.dinry.clouddisk.model;

import java.util.Date;
import javax.persistence.*;

public class Visitor {
    @Id
    private Integer id;

    private String ip;

    private Integer times;

    @Column(name = "last_enter_time")
    private Date lastEnterTime;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return times
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * @param times
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * @return last_enter_time
     */
    public Date getLastEnterTime() {
        return lastEnterTime;
    }

    /**
     * @param lastEnterTime
     */
    public void setLastEnterTime(Date lastEnterTime) {
        this.lastEnterTime = lastEnterTime;
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