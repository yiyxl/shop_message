package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.mapper.DtsPermissionMapper;
import com.qf.pojo.DtsPermission;
import com.qf.service.DtsPermissionService;
import com.qf.vo.PermUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 哇哈哈
 * @ClassName DtsPermissionServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 26日 11:44
 * @version: 1.0
 */
@Service
public class DtsPermissionServiceImpl implements DtsPermissionService {
          @Autowired
          private DtsPermissionMapper dtsPermissionMapper;
          @Override
          public Set<String> findById(Integer roleIds) {
                    Set<String> permissionListByRoleId =  dtsPermissionMapper.findById(roleIds);
                    if (permissionListByRoleId.contains("*")){
                              return  dtsPermissionMapper.findAll();
                    }
                    return permissionListByRoleId;
          }

          @Override
          @Transactional
          public void updateRole(PermUpdate permUpdate) {
                    dtsPermissionMapper.deleteById(permUpdate.getRoleId());
                    dtsPermissionMapper.insertBatch(permUpdate);
          }

          @Override
          public List<String> findpermission(Integer[] roleIds) {
                    return null;
          }

          @Override
          public Set<String> findPermissionListByRoleIds(Integer[] roleIds) {
                    List<DtsPermission> roleId = dtsPermissionMapper.selectList(new QueryWrapper<DtsPermission>().in("role_id", roleIds));
                    return roleId.stream().map(DtsPermission::getPermission).collect(Collectors.toSet());
          }


}
