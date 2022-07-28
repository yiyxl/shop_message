package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsAdmin;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsAdminMapper extends BaseMapper<DtsAdmin> {

          List<DtsAdmin> selectValueLabel();
}
