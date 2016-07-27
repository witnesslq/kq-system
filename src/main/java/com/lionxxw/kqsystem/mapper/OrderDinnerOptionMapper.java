package com.lionxxw.kqsystem.mapper;

import com.lionxxw.kqsystem.entity.OrderDinnerOption;
import com.lionxxw.kqsystem.entity.OrderDinnerOptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDinnerOptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int countByExample(OrderDinnerOptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int deleteByExample(OrderDinnerOptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int insert(OrderDinnerOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int insertSelective(OrderDinnerOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    List<OrderDinnerOption> selectByExample(OrderDinnerOptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    OrderDinnerOption selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int updateByExampleSelective(@Param("record") OrderDinnerOption record, @Param("example") OrderDinnerOptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int updateByExample(@Param("record") OrderDinnerOption record, @Param("example") OrderDinnerOptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int updateByPrimaryKeySelective(OrderDinnerOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kq_order_dinner_options
     *
     * @mbggenerated Wed Jul 27 14:34:59 CST 2016
     */
    int updateByPrimaryKey(OrderDinnerOption record);
}