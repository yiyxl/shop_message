package com.qf.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mapper.DtsGoodsAttributeMapper;
import com.qf.mapper.DtsGoodsMapper;
import com.qf.pojo.*;
import com.qf.service.*;
import com.qf.util.AdminResponseCode;
import com.qf.util.AdminResponseUtil;
import com.qf.util.ResponseUtil;
import com.qf.vo.GoodsAllinone;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 11:12
 * @version: 1.0
 */
@Service
public class DtsGoodsServiceImpl implements DtsGoodsService {
          private static final String PPGLY = "品牌管理员";
          private static final String CJGLY = "超级管理员";
          @Autowired
          private DtsRoleService dtsRoleService;
          @Autowired
          private DtsGoodsService goodsService;

          @Autowired
          private DtsGoodsSpecificationService specificationService;

          @Autowired
          private DtsGoodsAttributeService attributeService;

          @Autowired
          private DtsGoodsProductService productService;

          @Autowired
          private DtsCategoryService categoryService;

          @Autowired
          private DtsBrandService brandService;

          @Autowired
          private DtsCartService cartService;

          @Autowired
          private DtsOrderGoodsService orderGoodsService;

          @Autowired
          private AdminDataAuthService adminDataAuthService;
          @Autowired
          private DtsGoodsMapper dtsGoodsMapper;
          @Autowired
          private DtsGoodsAttributeMapper dtsGoodsAttributeMapper;
          private static final Logger logger = LoggerFactory.getLogger(AdminGoodsServiceImpl.class);
          @Override
          public Integer selectCount() {
                    return dtsGoodsMapper.selectCount(null);
          }

          @Override
          public List<DtsGoods> findAll() {
                    return dtsGoodsMapper.selectList(null);
          }

          @Override
          public Object create(GoodsAllinone goodsAllinone) {

                    DtsGoodsAttribute[] attributes = goodsAllinone.getAttributes();
                    DtsGoods goods = goodsAllinone.getGoods();
                    DtsGoodsProduct[] products = goodsAllinone.getProducts();
                    DtsGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
                    String name = goods.getName();
                    if (goodsService.checkExistByName(name)) {
                              return AdminResponseUtil.fail(AdminResponseCode.GOODS_NAME_EXIST);
                    }

                    // 商品基本信息表Dts_goods
                    goodsService.add(goods);


                    // 商品规格表Dts_goods_specification
                    for (DtsGoodsSpecification specification : specifications) {
                              specification.setGoodsId(goods.getId());
                              specificationService.add(specification);
                    }

                    // 商品参数表Dts_goods_attribute
                    for (DtsGoodsAttribute attribute : attributes) {
                              attribute.setGoodsId(goods.getId());
                              attributeService.add(attribute);
                    }

                    // 商品货品表Dts_product
                    for (DtsGoodsProduct product : products) {
                              product.setGoodsId(goods.getId());
                              productService.add(product);
                    }
                    return ResponseUtil.ok();
          }

          @Override
          public boolean checkExistByName(String name) {
                    return false;
          }

          @Override
          public void add(DtsGoods goods) {
                    dtsGoodsMapper.insert(goods);
          }

          @Override
          public Object delete(Integer id) {
                    return dtsGoodsMapper.deleteById(id);
          }

          @Override
          public IPage selectPage(Integer page, Integer limit, String sort, String order, DtsAdmin dtsAdmin) {
                    Integer[] roleIds = dtsAdmin.getRoleIds();
                    Set<String> roleByIds = dtsRoleService.findRoleByIds(roleIds);
                    if (CollectionUtils.isEmpty(roleByIds)){
                              return null;
                    }else {
                              if (roleByIds.contains(CJGLY)){
                                        Page<DtsGoods> dtsGoodsPage = dtsGoodsMapper.selectPage(new Page<DtsGoods>(page, limit),
                                                new QueryWrapper<DtsGoods>()
                                                        .orderBy(!StringUtils.isEmpty(sort),
                                                                "ASC".equalsIgnoreCase(order), sort));
                                        return dtsGoodsPage;
                              }
                              if (roleByIds.contains(PPGLY)){
                                        Integer id = dtsAdmin.getId();
                                        Long l =  dtsGoodsMapper.findGoodListCount(id);
                                        Integer startRow = (page - 1) * limit;
                                        List<DtsGoods> dtsGoodsList = dtsGoodsMapper.findGoodPageByAdmin(id,startRow,limit);
                                        Page<DtsGoods> dtsGoodsPage = new Page<>();
                                        dtsGoodsPage.setTotal(l);
                                        dtsGoodsPage.setRecords(dtsGoodsList);
                                        return dtsGoodsPage;
                              }
                    }
                    return null;
          }
}
