package com.qf.service.impl;

import com.qf.mapper.DtsGoodsAttributeMapper;
import com.qf.pojo.DtsGoodsAttribute;
import com.qf.service.DtsGoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsAttributeServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 24日 16:00
 * @version: 1.0
 */
@Service
public class DtsGoodsAttributeServiceImpl implements DtsGoodsAttributeService {
          @Autowired
          private DtsGoodsAttributeMapper dtsGoodsAttributeMapper;
          @Override
          public void add(DtsGoodsAttribute attribute) {
                    dtsGoodsAttributeMapper.insert(attribute);
          }
}
