package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsUser;
import com.qf.vo.DayStatis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsUserMapper extends BaseMapper<DtsUser> {

          @Select("SELECT\n" +
                  "\tcount( 1 ) cnts,\n" +
                  "\tDATE_FORMAT( add_time, '%Y-%m-%d' ) dayStr \n" +
                  "FROM\n" +
                  "\tdts_user \n" +
                  "WHERE\n" +
                  "\tadd_time > Date_ADD( NOW( ), INTERVAL - #{sdr} DAY ) \n" +
                  "GROUP BY\n" +
                  " dayStr")
          List<DayStatis> findOrderDayNumList(@Param("sdr") Integer statisDaysRung);
}
