package com.qf.service.impl;

import com.qf.mapper.DtsGoodsSpecificationMapper;
import com.qf.pojo.DtsGoodsSpecification;
import com.qf.service.DtsGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 哇哈哈
 * @ClassName DtsGoodsSpecificationServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 24日 16:00
 * @version: 1.0
 */
@Service
public class DtsGoodsSpecificationServiceImpl implements DtsGoodsSpecificationService {
          @Autowired
          private DtsGoodsSpecificationMapper dtsGoodsSpecificationMapper;
          @Override
          public void add(DtsGoodsSpecification specification) {
                    dtsGoodsSpecificationMapper.insert(specification);
          }
}
