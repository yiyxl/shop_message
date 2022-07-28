package com.qf.service;

import com.qf.vo.PermUpdate;

import java.util.List;
import java.util.Set;

/**
 * @author 哇哈哈
 * @ClassName DtsPermissionService
 * @description: TODO
 * @datetime 2022年 07月 26日 11:44
 * @version: 1.0
 */
public interface DtsPermissionService {
          Set<String> findById(Integer roleIds);

          void updateRole(PermUpdate permUpdate);

          List<String> findpermission(Integer[] roleIds);

          Set<String> findPermissionListByRoleIds(Integer[] roleIds);
}
