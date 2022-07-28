package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsRole;
import com.qf.vo.CatVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsRoleMapper extends BaseMapper<DtsRole> {

          @Select("select id value,name label from dts_role")
          List<CatVo> findValueLabel();

}
