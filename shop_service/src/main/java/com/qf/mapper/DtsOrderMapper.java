package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsOrder;
import com.qf.vo.DayStatis;
import com.qf.vo.UserOrderCntVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsOrderMapper extends BaseMapper<DtsOrder> {

          @Select("select\n" +
                  "          count(1) as cnts,\n" +
                  "          date_format(add_time,'%Y-%m-%d') as dayStr,\n" +
                  "          sum(actual_price) as amts\n" +
                  "          from dts_order\n" +
                  "          where DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -30 day),'%Y-%m-%d') < DATE_FORMAT(add_time,'%Y-%m-%d')\n" +
                  "          group by DATE_FORMAT(add_time,'%Y-%m-%d')")
          List<DayStatis> findOrderDayStatisList();

          @Select("select\n" +
                  "          count(1) as cnts,\n" +
                  "          date_format(add_time,'%Y-%m-%d') as dayStr,\n" +
                  "          from dts_order\n" +
                  "          where DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -30 day),'%Y-%m-%d') < DATE_FORMAT(add_time,'%Y-%m-%d')\n" +
                  "          group by DATE_FORMAT(add_time,'%Y-%m-%d')")
          List<DayStatis> findOrderDayNumList();
          List<UserOrderCntVo> findUserOrderCntList();
}
