package com.qf.service.impl;

import com.qf.mapper.DtsOrderMapper;
import com.qf.service.DtsOrderService;
import com.qf.vo.DayStatis;
import com.qf.vo.UserOrderCntVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsOrderServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 17:24
 * @version: 1.0
 */
@Service
public class DtsOrderServiceImpl implements DtsOrderService {
          @Autowired
          private DtsOrderMapper dtsOrderMapper;
          @Override
          public Integer selectCount() {
                    return dtsOrderMapper.selectCount(null);
          }

          @Override
          public List<DayStatis> findOrderDayStatisList() {
                    return dtsOrderMapper.findOrderDayStatisList();
          }

          @Override
          public List<UserOrderCntVo> findUserOrderCntlist() {
                    return dtsOrderMapper.findUserOrderCntList();
          }
}
