package com.guigu.mapper;

import com.guigu.pojo.Order;
import com.guigu.pojo.OrderAndMember;
import com.guigu.pojo.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 今日预约数
     * @param today
     * @return
     */
    Integer findOrderCountByDate(String today);

    /**
     * 今日到诊数
     * @param today
     * @return
     */
    Integer findVisitsCountByDate(String today);

    /**
     * 多久之后的到诊数
     * @param
     * @return
     */
    Integer findVisitsCountAfterDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 热门套餐
     * @return
     */
    List<Map<String,Object>> findHotSetmeal();

    /**
     * 统计日期范围内预约数量
     * @param startDate
     * @param endDate
     * @return
     */
    int findOrderCountBetweenDate(@Param("startDate") String startDate, @Param("endDate")String endDate);
    List<OrderAndMember> getAll(String queryStr);

}