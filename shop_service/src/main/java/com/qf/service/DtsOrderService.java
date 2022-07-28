package com.qf.service;

import com.qf.vo.DayStatis;
import com.qf.vo.UserOrderCntVo;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsOrderService
 * @description: TODO
 * @datetime 2022年 07月 22日 17:24
 * @version: 1.0
 */
public interface DtsOrderService {
          Integer selectCount();

          List<DayStatis> findOrderDayStatisList();

          List<UserOrderCntVo> findUserOrderCntlist();
}
