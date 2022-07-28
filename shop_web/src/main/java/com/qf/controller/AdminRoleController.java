package com.qf.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.annotation.RequiresPermissionsDesc;
import com.qf.pojo.DtsRole;
import com.qf.service.DtsAdminService;
import com.qf.service.DtsPermissionService;
import com.qf.service.DtsRoleService;
import com.qf.util.Permission;
import com.qf.util.PermissionUtil;
import com.qf.util.ResponseUtil;
import com.qf.vo.CatVo;
import com.qf.vo.PermUpdate;
import com.qf.vo.PermVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author 哇哈哈
 * @ClassName AdminRoleController
 * @description: TODO
 * @datetime 2022年 07月 24日 10:38
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin/role")
@CrossOrigin
public class AdminRoleController {
          @Autowired
          private DtsRoleService dtsRoleService;
          @Autowired
          private ApplicationContext applicationContext;
          @Autowired
          private DtsPermissionService dtsPermissionService;
          @Autowired
          private DtsAdminService dtsAdminService;
//          @GetMapping("/list")
//          public Object list(){
//                    Integer totle =  dtsRoleService.findCount();
//                    List<DtsRole> dtsRoleList = dtsRoleService.findAll();
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("totle",totle);
//                    map.put("items",dtsRoleList);
//                    return ResponseUtil.ok(map);
//          }
          @GetMapping("options")
          public Object options(){
                    List<CatVo> data = dtsRoleService.findValueLabel();
                    return ResponseUtil.ok(data);
          }

          /**
           * 角色管理分页查询
           */
          @GetMapping("/list")
          public Object list(Integer page,Integer limit,String sort,String order,String rolename){
                    IPage<DtsRole> roleIPage =  dtsRoleService.list( page, limit, sort, order, rolename);
                    Map<String,Object> map = new HashMap<>();
                    map.put("total",roleIPage.getTotal());
                    map.put("items",roleIPage.getRecords());
                    return ResponseUtil.ok(map);
          }
//          授权列表
          @RequiresPermissionsDesc(menu = {"角色管理","角色管理"},button = "查询")
          @RequiresPermissions("admin:role:perm:list")
          @GetMapping("/permissions")
          public Object permissions(Integer roleId){
//                    显示当前系统中已存在的权限模块
                    List<PermVo> permVoList = findpermVoList();
                    Set<String> set =  dtsPermissionService.findById(roleId);
                    Map<String,Object> map = new HashMap<>();
                    map.put("systemPermissions",permVoList);
                    map.put("assignedPermissions",set);
                    return ResponseUtil.ok(map);
          }

//          查询当前系统中所有模块的列表
          private List<PermVo> findpermVoList() {
                    List<Permission> permissionList = PermissionUtil.listPermission(applicationContext, "com.qf");
                    if (!CollectionUtils.isEmpty(permissionList)){
                              return PermissionUtil.listPermVo(permissionList);
                    }else {
                              return new ArrayList<PermVo>();
                    }
          }
          @RequiresPermissionsDesc(menu = {"角色管理","角色管理"},button = "授权")
          @RequiresPermissions("admin:role:perm:update")
          @PostMapping("/permissions")
          public Object permissions(@RequestBody PermUpdate permUpdate){
                     dtsPermissionService.updateRole(permUpdate);
                    return ResponseUtil.ok();
          }
          @RequiresPermissionsDesc(menu = {"角色管理","管理"},button = "添加")
          @RequiresPermissions("admin:role:perm:insert")
          @PostMapping("/create")
          public Object create(@RequestBody DtsRole dtsRole){
                    dtsRoleService.insert(dtsRole);
                    return ResponseUtil.ok(dtsRole);
          }
          @RequiresPermissionsDesc(menu = {"角色管理","管理"},button = "删除")
          @RequiresPermissions("admin:role:perm:delete")
          @PostMapping("/delete")
          public Object delete(@RequestBody DtsRole dtsRole){
                    dtsRoleService.delete(dtsRole);
                    return ResponseUtil.ok();
          }
          @RequiresPermissionsDesc(menu = {"角色管理","管理"},button = "修改")
          @RequiresPermissions("admin:role:perm:updaterole")
          @PostMapping("/update")
          public Object update(@RequestBody DtsRole dtsRole){
                    dtsRoleService.update(dtsRole);
                    return ResponseUtil.ok();
          }
}
