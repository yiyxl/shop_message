package com.qf.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mapper.DtsAdminMapper;
import com.qf.pojo.DtsAdmin;
import com.qf.service.DtsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsAdminServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 25日 14:58
 * @version: 1.0
 */
@Service
public class DtsAdminServiceImpl implements DtsAdminService {
          @Autowired
          private DtsAdminMapper dtsAdminMapper;
          @Override
          public IPage<DtsAdmin> findPageByQuery(Integer page, Integer limit, String sort, String order, String username) {
                    return dtsAdminMapper.selectPage(new Page<>(page,limit),
                            new QueryWrapper<DtsAdmin>()
                                    .like(!StringUtils.isEmpty(username),"username",username)
                                    .orderBy(!StringUtils.isEmpty(sort),"ASC".equalsIgnoreCase(order),sort));
          }

          @Override
          public List<DtsAdmin> findAll() {
                    return dtsAdminMapper.selectValueLabel();
          }

          @Override
          public Integer findCount() {
                    return dtsAdminMapper.selectCount(null);
          }

          @Override
          public int updateAdmin(DtsAdmin dtsAdmin) {
                    return dtsAdminMapper.updateById(dtsAdmin);
          }

          @Override
          public int deleteById(DtsAdmin dtsAdmin) {
                    return dtsAdminMapper.deleteById(dtsAdmin);
          }

          @Override
          public int insertAdmin(DtsAdmin dtsAdmin) {
                    return dtsAdminMapper.insert(dtsAdmin);
          }
}
