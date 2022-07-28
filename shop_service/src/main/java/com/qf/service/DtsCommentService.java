package com.qf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.pojo.DtsComment;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsCommentService
 * @description: TODO
 * @datetime 2022年 07月 24日 16:32
 * @version: 1.0
 */
public interface DtsCommentService {


          Integer findCount();

          List<DtsComment> findAll();

          IPage<DtsComment> findAllByPage(Integer page, Integer limit, String add_time, String order);
}
