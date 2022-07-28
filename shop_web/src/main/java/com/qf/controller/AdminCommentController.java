package com.qf.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.annotation.RequiresPermissionsDesc;
import com.qf.pojo.DtsComment;
import com.qf.service.DtsCommentService;
import com.qf.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 哇哈哈
 * @ClassName AdminCommentController
 * @description: TODO
 * @datetime 2022年 07月 24日 16:22
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin/comment")
@CrossOrigin
public class AdminCommentController {
          @Autowired
          private DtsCommentService dtsCommentService;
          @RequiresPermissionsDesc(menu = {"系统管理","商品管理"},button = "评论")
          @RequiresPermissions(value = "admin:comment:list")
          @GetMapping("/list")
          public Object list(Integer page,Integer limit,String add_time,String order){
                    //localhost:8083/admin/comment/list?page=1&limit=20&sort=add_time&order=desc
                    IPage<DtsComment> iPage = dtsCommentService.findAllByPage(page,limit,add_time,order);
                    Map<String,Object> map = new HashMap<>();
                    map.put("total",iPage.getTotal());
                    map.put("items",iPage.getRecords());
                    return ResponseUtil.ok(map);

          }
}
