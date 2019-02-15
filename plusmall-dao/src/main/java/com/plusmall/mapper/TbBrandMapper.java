package com.plusmall.mapper;

import com.plusmall.model.TbBrand;
import com.plusmall.model.TbBrandExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbBrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int countByExample(TbBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int deleteByExample(TbBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int insert(TbBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int insertSelective(TbBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    List<TbBrand> selectByExample(TbBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    TbBrand selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbBrand record, @Param("example") TbBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int updateByExample(@Param("record") TbBrand record, @Param("example") TbBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int updateByPrimaryKeySelective(TbBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_brand
     *
     * @mbggenerated Sun Feb 10 10:57:24 CST 2019
     */
    int updateByPrimaryKey(TbBrand record);

	/**
	 * 返回所有的品牌信息
	 * @return
	 */
	List<Map> selectOptionList();
}