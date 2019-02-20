package com.plusmall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TbGoods implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.seller_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String sellerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.goods_name
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String goodsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.default_item_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long defaultItemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.audit_status
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String auditStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.is_marketable
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String isMarketable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.brand_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long brandId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.caption
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String caption;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.category1_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long category1Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.category2_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long category2Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.category3_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long category3Id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.small_pic
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String smallPic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.price
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.type_template_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private Long typeTemplateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.is_enable_spec
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String isEnableSpec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods.is_delete
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    private String isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.id
     *
     * @return the value of tb_goods.id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.id
     *
     * @param id the value for tb_goods.id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.seller_id
     *
     * @return the value of tb_goods.seller_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.seller_id
     *
     * @param sellerId the value for tb_goods.seller_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.goods_name
     *
     * @return the value of tb_goods.goods_name
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.goods_name
     *
     * @param goodsName the value for tb_goods.goods_name
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.default_item_id
     *
     * @return the value of tb_goods.default_item_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getDefaultItemId() {
        return defaultItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.default_item_id
     *
     * @param defaultItemId the value for tb_goods.default_item_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setDefaultItemId(Long defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.audit_status
     *
     * @return the value of tb_goods.audit_status
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.audit_status
     *
     * @param auditStatus the value for tb_goods.audit_status
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.is_marketable
     *
     * @return the value of tb_goods.is_marketable
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getIsMarketable() {
        return isMarketable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.is_marketable
     *
     * @param isMarketable the value for tb_goods.is_marketable
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable == null ? null : isMarketable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.brand_id
     *
     * @return the value of tb_goods.brand_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.brand_id
     *
     * @param brandId the value for tb_goods.brand_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.caption
     *
     * @return the value of tb_goods.caption
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getCaption() {
        return caption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.caption
     *
     * @param caption the value for tb_goods.caption
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.category1_id
     *
     * @return the value of tb_goods.category1_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getCategory1Id() {
        return category1Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.category1_id
     *
     * @param category1Id the value for tb_goods.category1_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.category2_id
     *
     * @return the value of tb_goods.category2_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getCategory2Id() {
        return category2Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.category2_id
     *
     * @param category2Id the value for tb_goods.category2_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.category3_id
     *
     * @return the value of tb_goods.category3_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getCategory3Id() {
        return category3Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.category3_id
     *
     * @param category3Id the value for tb_goods.category3_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setCategory3Id(Long category3Id) {
        this.category3Id = category3Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.small_pic
     *
     * @return the value of tb_goods.small_pic
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getSmallPic() {
        return smallPic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.small_pic
     *
     * @param smallPic the value for tb_goods.small_pic
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.price
     *
     * @return the value of tb_goods.price
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.price
     *
     * @param price the value for tb_goods.price
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.type_template_id
     *
     * @return the value of tb_goods.type_template_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public Long getTypeTemplateId() {
        return typeTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.type_template_id
     *
     * @param typeTemplateId the value for tb_goods.type_template_id
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setTypeTemplateId(Long typeTemplateId) {
        this.typeTemplateId = typeTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.is_enable_spec
     *
     * @return the value of tb_goods.is_enable_spec
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.is_enable_spec
     *
     * @param isEnableSpec the value for tb_goods.is_enable_spec
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec == null ? null : isEnableSpec.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods.is_delete
     *
     * @return the value of tb_goods.is_delete
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods.is_delete
     *
     * @param isDelete the value for tb_goods.is_delete
     *
     * @mbggenerated Wed Feb 20 12:29:57 CST 2019
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}