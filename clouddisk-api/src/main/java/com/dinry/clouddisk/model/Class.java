package com.dinry.clouddisk.model;

import javax.persistence.*;

public class Class {
    @Id
    private Integer id;

    private String name;

    @Column(name = "major_id")
    private Integer majorId;

    @Column(name = "college_id")
    private Integer collegeId;

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
     * @return major_id
     */
    public Integer getMajorId() {
        return majorId;
    }

    /**
     * @param majorId
     */
    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    /**
     * @return college_id
     */
    public Integer getCollegeId() {
        return collegeId;
    }

    /**
     * @param collegeId
     */
    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
}