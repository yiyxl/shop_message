package com.qf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.pojo.DtsAdmin;
import com.qf.pojo.DtsGoods;
import com.qf.vo.GoodsAllinone;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsService
 * @description: TODO
 * @datetime 2022年 07月 22日 11:12
 * @version: 1.0
 */
public interface DtsGoodsService {
          Integer selectCount();

          List<DtsGoods> findAll();

          Object create(GoodsAllinone goodsAllinone);

          boolean checkExistByName(String name);

          void add(DtsGoods goods);

          Object delete(Integer id);

          IPage selectPage(Integer page, Integer limit, String sort, String order, DtsAdmin dtsAdmin);

}
