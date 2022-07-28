package com.qf.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.annotation.RequiresPermissionsDesc;
import com.qf.pojo.DtsAdmin;
import com.qf.pojo.DtsRole;
import com.qf.service.DtsAdminService;
import com.qf.service.DtsRoleService;
import com.qf.util.ResponseUtil;
import com.qf.util.bcrypt.BCryptPasswordEncoder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 哇哈哈
 * @ClassName AdminAdminController
 * @description: TODO
 * @datetime 2022年 07月 25日 14:51
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin/admin")
@CrossOrigin
public class AdminAdminController {
          @Autowired
          private DtsAdminService dtsAdminService;
          @Autowired
          private DtsRoleService dtsRoleService;
//          管理员列表
          @RequiresPermissionsDesc(menu = {"系统管理","管理员"},button = "查询")
          @RequiresPermissions(value = "admin:admin:list")//权限
          @GetMapping("/list")
          public Object list(Integer page, Integer limit,String sort,String order,String username){
                    IPage<DtsAdmin> iPage =   dtsAdminService.findPageByQuery(page,limit,sort,order,username);
                    Map<String,Object> map = new HashMap<>();
                    map.put("totle",iPage.getTotal());
                    map.put("items",iPage.getRecords());
                    return ResponseUtil.ok(map);
          }

          /**
           * {
           * 	"avatar": "https://juhuixing-public.oss-cn-shenzhen.aliyuncs.com/pi47cqg6ubw47vefxic6.jpg",
           * 	"id": 12,
           * 	"mail": "123456@qq.com",
           * 	"password": "123456",
           * 	"roleIds": [
           * 		25
           * 	],
           * 	"tel": "13452046647",
           * 	"username": "itsource"
           * }
           * @return
           */
          @RequiresPermissionsDesc(menu = {"系统管理","管理员"},button = "修改")
          @RequiresPermissions(value = "admin:admin:update")//权限
          @PostMapping("/update")
          public Object update(@RequestBody DtsAdmin dtsAdmin){

//                    System.out.println(s);
                    String username = dtsAdmin.getUsername();
                    String password = dtsAdmin.getPassword();
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    String encode = encoder.encode(password);
                    dtsAdmin.setPassword(encode);
                    int i = dtsAdminService.updateAdmin(dtsAdmin);
                    List<DtsRole> dtsRoleList = dtsRoleService.findAll();
                    Map<String, Object> map = new HashMap<>();
                    map.put("items",dtsRoleList);
                    return ResponseUtil.ok(map);
          }
          @RequestMapping("/delete")
          public Object delete(@RequestBody DtsAdmin dtsAdmin){
                    int i = dtsAdminService.deleteById(dtsAdmin);
                    return ResponseUtil.ok();
          }
          @RequestMapping("/create")
          public Object create(@RequestBody DtsAdmin dtsAdmin){

//                    System.out.println(s);
                    String password = dtsAdmin.getPassword();
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    String encode = encoder.encode(password);
                    dtsAdmin.setPassword(encode);
                    int i = dtsAdminService.insertAdmin(dtsAdmin);
                    List<DtsRole> dtsRoleList = dtsRoleService.findAll();
                    Map<String, Object> map = new HashMap<>();
                    map.put("items",dtsRoleList);
                    return ResponseUtil.ok(dtsAdmin);
          }
}
