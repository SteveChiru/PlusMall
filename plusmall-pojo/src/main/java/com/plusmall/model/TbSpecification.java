package com.plusmall.model;

import java.io.Serializable;

public class TbSpecification implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_specification.id
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_specification.spec_name
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    private String specName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_specification.id
     *
     * @return the value of tb_specification.id
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_specification.id
     *
     * @param id the value for tb_specification.id
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_specification.spec_name
     *
     * @return the value of tb_specification.spec_name
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_specification.spec_name
     *
     * @param specName the value for tb_specification.spec_name
     *
     * @mbggenerated Tue Feb 12 21:01:48 CST 2019
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
}