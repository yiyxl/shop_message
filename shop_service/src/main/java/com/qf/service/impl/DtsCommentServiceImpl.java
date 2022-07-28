package com.qf.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mapper.DtsCommentMapper;
import com.qf.pojo.DtsComment;
import com.qf.service.DtsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsCommentServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 24日 16:32
 * @version: 1.0
 */
@Service
public class DtsCommentServiceImpl implements DtsCommentService {
          @Autowired
          private DtsCommentMapper dtsCommentMapper;
          @Override
          public Integer findCount() {
                    return dtsCommentMapper.selectCount(null);
          }

          @Override
          public List<DtsComment> findAll() {
                    return dtsCommentMapper.selectList(null);
          }

          @Override
          public IPage<DtsComment> findAllByPage(Integer page, Integer limit, String add_time, String order) {
                    return dtsCommentMapper.selectPage(new Page<>(page,limit),
                            new QueryWrapper<DtsComment>().orderBy(!StringUtils.isEmpty(add_time),"ASC".equalsIgnoreCase(order),add_time));
          }
}
