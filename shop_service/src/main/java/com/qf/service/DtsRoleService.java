package com.qf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.pojo.DtsRole;
import com.qf.vo.CatVo;

import java.util.List;
import java.util.Set;

/**
 * @author 哇哈哈
 * @ClassName DtsRoleServic
 * @description: TODO
 * @datetime 2022年 07月 22日 9:26
 * @version: 1.0
 */
public interface DtsRoleService {
          Set<String> findRoleByUid(Integer id);

          Integer findCount();

          List<DtsRole> findAll();

          List<CatVo> findValueLabel();

          IPage<DtsRole> list(Integer page, Integer limit, String sort, String order, String rolename);

          void insert(DtsRole dtsRole);

          void delete(DtsRole dtsRole);

          void update(DtsRole dtsRole);

          Set<String> findRoleByIds(Integer[] roleIds);
}
