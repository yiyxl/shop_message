package com.qf.service.impl;

import com.qf.mapper.DtsGoodsProductMapper;
import com.qf.pojo.DtsGoodsProduct;
import com.qf.service.DtsGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsProductServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 17:22
 * @version: 1.0
 */
@Service
public class DtsGoodsProductServiceImpl implements DtsGoodsProductService {
          @Autowired
          private DtsGoodsProductMapper dtsGoodsProductMapper;
          @Override
          public Integer selectCount() {
                    return dtsGoodsProductMapper.selectCount(null);
          }

          @Override
          public void add(DtsGoodsProduct product) {
                    dtsGoodsProductMapper.insert(product);
          }
}
