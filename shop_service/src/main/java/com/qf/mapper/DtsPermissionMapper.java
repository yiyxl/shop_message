package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsPermission;
import com.qf.vo.PermUpdate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsPermissionMapper extends BaseMapper<DtsPermission> {

          @Select("select permission from dts_permission where role_id = #{role} ")
          Set<String> findById(@Param("role") Integer roleIds);

          @Select("select permission from dts_permission")
          Set<String> findAll();


          void insertBatch(PermUpdate permUpdate);

}
