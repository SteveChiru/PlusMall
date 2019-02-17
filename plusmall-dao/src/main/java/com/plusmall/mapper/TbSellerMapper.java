package com.plusmall.mapper;

import com.plusmall.model.TbSeller;
import com.plusmall.model.TbSellerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSellerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int countByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int deleteByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int deleteByPrimaryKey(String sellerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int insert(TbSeller record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int insertSelective(TbSeller record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    List<TbSeller> selectByExample(TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    TbSeller selectByPrimaryKey(String sellerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbSeller record, @Param("example") TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int updateByExample(@Param("record") TbSeller record, @Param("example") TbSellerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int updateByPrimaryKeySelective(TbSeller record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seller
     *
     * @mbggenerated Sun Feb 17 20:59:53 CST 2019
     */
    int updateByPrimaryKey(TbSeller record);
}