package com.plusmall.model;

import java.io.Serializable;

public class TbItemCat implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.parent_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.name
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.type_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    private Long typeId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.id
     *
     * @return the value of tb_item_cat.id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.id
     *
     * @param id the value for tb_item_cat.id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.parent_id
     *
     * @return the value of tb_item_cat.parent_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.parent_id
     *
     * @param parentId the value for tb_item_cat.parent_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.name
     *
     * @return the value of tb_item_cat.name
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.name
     *
     * @param name the value for tb_item_cat.name
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.type_id
     *
     * @return the value of tb_item_cat.type_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.type_id
     *
     * @param typeId the value for tb_item_cat.type_id
     *
     * @mbggenerated Tue Feb 19 17:33:25 CST 2019
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}