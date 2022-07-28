package com.qf.controller;

import com.qf.captcha.CaptchaCodeManager;
import com.qf.pojo.DtsAdmin;
import com.qf.service.DtsPermissionService;
import com.qf.service.DtsRoleService;
import com.qf.util.*;
import com.qf.util.Base64;
import com.qf.util.UUID;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 哇哈哈
 * @ClassName AdminAuthController
 * @description: TODO
 * @datetime 2022年 07月 21日 15:21
 * @version: 1.0
 * 后台管理系统--认证  管理
 * 上行数据：                      本地文件上传到服务器端
 *
 * 下行数据：
 *
 * 1、验证码
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin//跨域
public class AdminAuthController {
          @Autowired
          private DtsPermissionService dtsPermissionService;
          @Autowired(required = false)
          private DtsRoleService dtsRoleService;
          @Autowired
          private ApplicationContext applicationContext;
          /**
           *      验证码                   http://localhost:8083/admin/auth/captchaImage
           *
           *
           * {
           *           "errno":0,
           *         "data":	{
           *           "img":"/9j/4AAQSkZJRlZ/2Q==",
           *                   "uuid":"b5ca22d8b9e14a88b2851da906d19e4f"
           * },
           *           "errmsg":"成功"
           * }
           */

          @GetMapping("/auth/captchaImage")
          public Object captchaImage() throws Exception{//String name : 上行数据：就是形参    入参
                    Map<String, Object> map = new HashMap<>();
//                    验证码生产的图片Base64加密
//                    1.获取了验证码
                    String code = VerifyCodeUtils.generateVerifyCode(6);
                    System.out.println("code = " + code);
//                    一二级缓存 底层map:                k：sql语句 v：值
//                    2.将code保存在缓存map中
                    String uuid = UUID.randomUUID(true).toString();
                    boolean flag = CaptchaCodeManager.addToCache(uuid, code, 1);
                    if (flag){
//                              将上面的code转换成图片
                              int w = 110;
                              int h = 36;
                              ByteArrayOutputStream os = new ByteArrayOutputStream();
                              VerifyCodeUtils.outputImage(110,36,os,code);
                              map.put("img", Base64.encode(os.toByteArray()));
                              map.put("uuid",uuid);
                    }
                    return ResponseUtil.ok(map);
          }

          /**
           *
           * - 登录请求地址 : http://localhost:8083/admin/auth/login
           * - POST请求
           *
           *
           * 上行数据
           * {
           * 	"code": "66e8",
           * 	"password": "123456",
           * 	"username": "qianfeng",
           * 	"uuid": "cab317c6-1bf1-436d-96ac-fda9f8d37dde"
           * }
           *
           * 下行数据
           *
           * {
           *     "errno":0,
           *  	"data":"568f6dd8-97c7-450d-a00b-0a01c3b930c7", //是当前用户Session的ID
           *  	"errmsg":"成功"
           * }
           */
          @PostMapping("/auth/login")
          public Object login(@RequestBody String json){

                    //用户名
                    String username = JacksonUtil.parseString(json, "username");
                    String password = JacksonUtil.parseString(json, "password");
                    String code = JacksonUtil.parseString(json, "code");
                    String uuid = JacksonUtil.parseString(json, "uuid");

//                    1.判断code验证码是否正确
                    String c = CaptchaCodeManager.getCachedCaptcha(uuid);
//                    if (StringUtils.isEmpty(c)){
//                              //验证码已经过期了
//                              return AdminResponseUtil.fail(AdminResponseCode.AUTH_CAPTCHA_EXPIRED);
//                    }
//                    if (!c.equalsIgnoreCase(code)){
////                              验证码错误
//                              return AdminResponseUtil.fail(AdminResponseCode.AUTH_CAPTCHA_ERROR);
//                    }
//                    shiro
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
                    try {
                              subject.login(usernamePasswordToken);
                    }catch (LockedAccountException e){
//                              用户账户锁定
                              return AdminResponseUtil.fail(AdminResponseCode.ADMIN_LOCK_ACCOUNT);
                    } catch (AuthenticationException e) {
//                              用户名密码不正确
                              return AdminResponseUtil.fail(AdminResponseCode.ADMIN_INVALID_ACCOUNT_OR_PASSWORD);
                    }
                    return ResponseUtil.ok(subject.getSession().getId());
          }
          /**
           * {
           *     "errno":0,
           *     "data":{
           *         "roles":["超级管理员"],
           *         "name":"qianfeng",
           *         "perms":["*"],
           *         "avatar":"https://dss1.bdstatic.com/6OF1,499686101&fm=85&app=79&f=JPEG?w=121&h=75&s=81B24C32D40"
           *     },
           *     "errmsg":"成功"
           * }
           */
          @GetMapping("/auth/info")
          public Object info(){

                    Map<String, Object> map = new HashMap<>();

                    Subject subject = SecurityUtils.getSubject();
                    DtsAdmin dtsAdmin = (DtsAdmin) subject.getPrincipal();
                    Set<String> roles = dtsRoleService.findRoleByIds(dtsAdmin.getRoleIds());
                    map.put("roles",roles);
                    map.put("name",dtsAdmin.getUsername());
                    Set<String> permissions =  dtsPermissionService.findPermissionListByRoleIds(dtsAdmin.getRoleIds());
                    map.put("perms", toApi(permissions));
                    map.put("avatar",dtsAdmin.getAvatar());
                    return ResponseUtil.ok(map);
          }

          private Collection toApi(Set<String> permissions) {
                    List<Permission> permissionList = PermissionUtil.listPermission(applicationContext, "com.qf");
//                    List<String> ps = new ArrayList<>();
//                    for (Permission permission : permissionList) {
//                              if (permissions.contains(permission.getRequiresPermissions().value()[0])){
////                                        有权限
//                                        ps.add(permission.getApi());
//                              }
//                    }
//                    return ps;
                    return permissionList.stream().filter(permission -> {
                              if (permissions.contains(permission.getRequiresPermissions().value()[0])){
                                        return true;
                              }
                              return false;
                    }).map(Permission::getApi).collect(Collectors.toSet());
          }

          @RequiresAuthentication
          @PostMapping("/auth/logout")
          public Object logout(){
                    Subject subject = SecurityUtils.getSubject();
                    subject.logout();
                    return ResponseUtil.ok();
          }
//          /**
//           *
//           */
          @GetMapping("/auth/403")
          public Object auth403(){
                    return ResponseUtil.unauthz();
          }
}
