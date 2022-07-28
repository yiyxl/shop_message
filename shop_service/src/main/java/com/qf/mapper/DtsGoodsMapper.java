package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品基本信息表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsGoodsMapper extends BaseMapper<DtsGoods> {

          @Select("SELECT\n" +
                  "\tcount( 1 ) \n" +
                  "FROM\n" +
                  "\tdts_goods dg\n" +
                  "\tINNER JOIN dts_brand db ON db.id = dg.brand_id \n" +
                  "WHERE\n" +
                  "\tdb.admin_id=#{id}")
          Long findGoodListCount(Integer id);
          @Select("SELECT\n" +
                  "\tdg.* \n" +
                  "FROM\n" +
                  "\tdts_goods dg\n" +
                  "\tINNER JOIN dts_brand db ON db.id = dg.brand_id \n" +
                  "WHERE\n" +
                  "\tdb.admin_id = #{id}\n" +
                  "\tLIMIT #{startRow},\n" +
                  "\t#{limit}")
          List<DtsGoods> findGoodPageByAdmin(@Param("id") Integer id,@Param("startRow") Integer startRow,@Param("limit") Integer limit);
}
