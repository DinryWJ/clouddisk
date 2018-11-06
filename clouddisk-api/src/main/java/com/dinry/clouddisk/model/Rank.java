package com.dinry.clouddisk.model;

import java.util.Date;
import javax.persistence.*;

public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String level;

    @Column(name = "rank_low")
    private String rankLow;

    @Column(name = "rank_high")
    private String rankHigh;

    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return rank_low
     */
    public String getRankLow() {
        return rankLow;
    }

    /**
     * @param rankLow
     */
    public void setRankLow(String rankLow) {
        this.rankLow = rankLow;
    }

    /**
     * @return rank_high
     */
    public String getRankHigh() {
        return rankHigh;
    }

    /**
     * @param rankHigh
     */
    public void setRankHigh(String rankHigh) {
        this.rankHigh = rankHigh;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
}