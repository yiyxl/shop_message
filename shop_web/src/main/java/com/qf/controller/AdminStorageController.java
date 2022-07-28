package com.qf.controller;

import com.qf.annotation.RequiresPermissionsDesc;
import com.qf.pojo.DtsStorage;
import com.qf.service.DtsStorageService;
import com.qf.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 哇哈哈
 * @ClassName AdminStorageController
 * @description: TODO
 * @datetime 2022年 07月 25日 21:46
 * @version: 1.0
 */
@RequestMapping("/admin/storage")
@RestController
@CrossOrigin
public class AdminStorageController {
          @Autowired
          private DtsStorageService dtsStorageService;
          @PostMapping("/create")
          @RequiresPermissions("admin:storage:create")
          @RequiresPermissionsDesc(menu = {"角色管理","角色管理"},button = "添加")
          public Object create(@RequestBody MultipartFile file) throws Exception {
//                    获取原始文件名
                    String originalFilename = file.getOriginalFilename();
//                    文件扩展名
                    String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//                    上传保存
                    DtsStorage dtsStorage =  dtsStorageService.add(originalFilename,(int)file.getSize(),substring,file.getBytes());
                    return ResponseUtil.ok(dtsStorage);
          }




}
