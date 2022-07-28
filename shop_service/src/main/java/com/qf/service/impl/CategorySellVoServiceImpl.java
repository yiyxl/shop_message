package com.qf.service.impl;

import com.qf.mapper.DtsCategoryMapper;
import com.qf.service.CategorySellVoService;
import com.qf.vo.CategorySellAmts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName CategorySellVoServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 20:42
 * @version: 1.0
 */
@Service
public class CategorySellVoServiceImpl implements CategorySellVoService {
          @Autowired
          private DtsCategoryMapper dtsCategoryMapper;
          @Override
          public List<CategorySellAmts> findCategorySellV0() {
                    return dtsCategoryMapper.findCategorySellV0();
          }
}
