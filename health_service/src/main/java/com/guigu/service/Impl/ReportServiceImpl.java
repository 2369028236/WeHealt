package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.MemberMapper;
import com.guigu.mapper.OrderMapper;
import com.guigu.utils.DateUtils;
import com.guigu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> getBusinessReport()  {
        Map<String,Object> reportData=new HashMap<String,Object>();
        Date today=new Date();
        //日期转换格式
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //星期一
        String  monday=sdf.format(DateUtils.getMonday());
        //星期天
        String sunday=sdf.format(DateUtils.getSunday());
        //本月1号
        String firstDayOfThisMonth=sdf.format(DateUtils.getFirstDayofThisMonth());
        //本月最后一天
        String lastDayOfThisMonth=sdf.format(DateUtils.getFirstDayofThisMonth());
        //当前天
        String reportDate=sdf.format(today);
        //------------会员数量--------------
        //todayNewMember今日新增会员
        int todayNewMember=memberMapper.findMemberCountByDate(reportDate);
        //totalMember 会员总数量
        int totalMember=memberMapper.findMemberTotalCount();
        //thisWeekNewMember 本周新增会员数
        int thisWeekNewMember=memberMapper.findMemberCountAfterDate(monday);
        //thisMonthNewMember 本月新增会员数
        int thisMonthNewMember=memberMapper.findMemberCountAfterDate(firstDayOfThisMonth);


        //-------------订单统计--------------
        //todayOrderNumber 今日预约数
        int todayOrderNumber=orderMapper.findOrderCountByDate(reportDate);
        //todayVisitsNumber 今日到诊数
        int todayVisitsNumber=orderMapper.findVisitsCountByDate(reportDate);
        //thisWeekOrderNumber 本周预约数
        int thisWeekOrderNumber=orderMapper.findOrderCountBetweenDate(monday,sunday);
        //thisWeekVisitsNumber 本周到诊数
        int thisWeekVisitsNumber=orderMapper.findVisitsCountAfterDate(monday,sunday);
        //thisMonthOrderNumber 本月预约数
        int thisMonthOrderNumber=orderMapper.findOrderCountBetweenDate(firstDayOfThisMonth,lastDayOfThisMonth);
        //thisMonthVisitsNumber 本月到诊数
        int thisMonthVisitsNumber=orderMapper.findVisitsCountAfterDate(firstDayOfThisMonth,lastDayOfThisMonth);

        //========================== 热门套餐========================
        // hotSetmeal
        List<Map<String,Object>> hotSetmeal=orderMapper.findHotSetmeal();

        reportData.put("reportDate",reportDate);
        reportData.put("todayNewMember",todayNewMember);
        reportData.put("totalMember",totalMember);
        reportData.put("thisWeekNewMember",thisWeekNewMember);
        reportData.put("thisMonthNewMember",thisMonthNewMember);
        reportData.put("todayOrderNumber",todayOrderNumber);
        reportData.put("todayVisitsNumber",todayVisitsNumber);
        reportData.put("thisWeekOrderNumber",thisWeekOrderNumber);
        reportData.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        reportData.put("thisMonthOrderNumber",thisMonthOrderNumber);
        reportData.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        reportData.put("hotSetmeal",hotSetmeal);
        return reportData;
    }
}
