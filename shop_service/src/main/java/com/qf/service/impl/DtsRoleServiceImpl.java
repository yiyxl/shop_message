package com.qf.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mapper.DtsAdminMapper;
import com.qf.mapper.DtsRoleMapper;
import com.qf.pojo.DtsAdmin;
import com.qf.pojo.DtsRole;
import com.qf.service.DtsRoleService;
import com.qf.vo.CatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 哇哈哈
 * @ClassName DtsRoleServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 9:27
 * @version: 1.0
 */
@Service
public class DtsRoleServiceImpl implements DtsRoleService {
          @Autowired
          private DtsRoleMapper dtsRoleMapper;
          @Autowired
          private DtsAdminMapper dtsAdminMapper;

          @Override
          public Set<String> findRoleByUid(Integer id) {
                    DtsAdmin dtsAdmin = dtsAdminMapper.selectById(id);
                    Integer[] roleIds = dtsAdmin.getRoleIds();
                    if (roleIds != null && roleIds.length>0){
                              List<DtsRole> dtsRoles = dtsRoleMapper.selectList(new QueryWrapper<DtsRole>()
                                      .select("name")
                                      .in("id", roleIds)
                                      .eq("deleted", false));
                              if (CollectionUtils.isEmpty(dtsRoles)){
                                        return null;
                              }else {
                                        return dtsRoles.stream().map(DtsRole::getName).collect(Collectors.toSet());
                              }
                    }
                    return null;
          }

          @Override
          public Integer findCount() {
                    return dtsRoleMapper.selectCount(null);
          }

          @Override
          public List<DtsRole> findAll() {
                    return dtsRoleMapper.selectList(null);
          }

          @Override
          public List<CatVo> findValueLabel() {
                    return dtsRoleMapper.findValueLabel();
          }

          @Override
          public IPage<DtsRole> list(Integer page, Integer limit, String sort, String order, String rolename) {
                    return dtsRoleMapper.selectPage(new Page<>(page,limit),
                            new UpdateWrapper<DtsRole>()
                                    .like(!StringUtils.isEmpty(rolename),"name",rolename)
                                    .orderBy(!StringUtils.isEmpty(sort),"ASC".equalsIgnoreCase(order),sort));
          }

          @Override
          public void insert(DtsRole dtsRole) {
                    dtsRoleMapper.insert(dtsRole);
          }

          @Override
          public void delete(DtsRole dtsRole) {
                    dtsRoleMapper.deleteById(dtsRole.getId());
          }

          @Override
          public void update(DtsRole dtsRole) {
                    dtsRoleMapper.updateById(dtsRole);
          }

          @Override
          public Set<String> findRoleByIds(Integer[] roleIds) {
                    if (roleIds != null && roleIds.length>0){
                              List<DtsRole> dtsRoles = dtsRoleMapper.selectList(new QueryWrapper<DtsRole>()
                                      .select("name")
                                      .in("id", roleIds)
                                      .eq("deleted", false));
                              if (CollectionUtils.isEmpty(dtsRoles)){
                                        return null;
                              }else {
                                        return dtsRoles.stream().map(DtsRole::getName).collect(Collectors.toSet());
                              }
                    }
                    return null;
          }

}
