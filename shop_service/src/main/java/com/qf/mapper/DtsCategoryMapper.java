package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.DtsCategory;
import com.qf.vo.CategorySellAmts;

import java.util.List;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
public interface DtsCategoryMapper extends BaseMapper<DtsCategory> {

          List<CategorySellAmts> findCategorySellV0();
}
