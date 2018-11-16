package com.dinry.clouddisk.model;

import java.util.Date;
import javax.persistence.*;

public class Content {
    @Id
    private Integer id;

    private String name;

    /**
     * 0 目录 1 文件
     */
    private Boolean directory;

    @Column(name = "file_id")
    private Integer fileId;

    private Integer index;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "user_id")
    private Integer userId;

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
     * 获取0 目录 1 文件
     *
     * @return directory - 0 目录 1 文件
     */
    public Boolean getDirectory() {
        return directory;
    }

    /**
     * 设置0 目录 1 文件
     *
     * @param directory 0 目录 1 文件
     */
    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    /**
     * @return file_id
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * @return index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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