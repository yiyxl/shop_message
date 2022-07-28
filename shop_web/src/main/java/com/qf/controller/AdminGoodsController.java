package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.annotation.RequiresPermissionsDesc;
import com.qf.pojo.DtsAdmin;
import com.qf.service.DtsGoodsService;
import com.qf.util.AuthSupport;
import com.qf.util.ResponseUtil;
import com.qf.vo.GoodsAllinone;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 哇哈哈
 * @ClassName AdminGoodsController
 * @description: TODO
 * @datetime 2022年 07月 24日 11:22
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin/goods")
@CrossOrigin
public class AdminGoodsController {
          @Autowired
          private DtsGoodsService dtsGoodsService;
          @RequiresPermissionsDesc(menu = {"系统管理","商品管理"},button = "查询")
          @RequiresPermissions(value = "admin:goods:list")
          @GetMapping("/list")
          public Object list(Integer page,Integer limit,String sort,String order){
//                    http://localhost:8083/admin/goods/list?page=1&limit=20&sort=add_time&order=desc
                    DtsAdmin dtsAdmin = AuthSupport.currentUser();
                    IPage iPage = dtsGoodsService.selectPage(page,limit,sort,order,dtsAdmin);
                    Map<String, Object> map = new HashMap<>();
                    map.put("total",iPage.getTotal());
                    map.put("items",iPage.getRecords());
                    return ResponseUtil.ok(map);
          }
          @RequiresPermissionsDesc(menu = {"系统管理","商品管理"},button = "商品上架")
          @RequiresPermissions(value = "admin:goods:create")
          @PostMapping("/create")
          public Object create(@RequestBody GoodsAllinone goodsAllinone){
                    String s = JSON.toJSONString(goodsAllinone);
                    return dtsGoodsService.create(goodsAllinone);
          }
          @RequiresPermissionsDesc(menu = {"系统管理","商品管理"},button = "删除")
          @RequiresPermissions(value = "admin:goods:delete")
          @PostMapping("/delete")
          public Object delete(@RequestBody Integer id){
                    return dtsGoodsService.delete(id);
          }
}
