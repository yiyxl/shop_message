package com.qf.service;

import com.qf.pojo.DtsGoodsProduct;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsProductService
 * @description: TODO
 * @datetime 2022年 07月 22日 17:22
 * @version: 1.0
 */
public interface DtsGoodsProductService {
          Integer selectCount();

          void add(DtsGoodsProduct product);
}
