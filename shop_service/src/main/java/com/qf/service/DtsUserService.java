package com.qf.service;

import com.qf.vo.DayStatis;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsUserService
 * @description: TODO
 * @datetime 2022年 07月 22日 16:57
 * @version: 1.0
 */
public interface DtsUserService {
          Integer selectCount();

          List<DayStatis> findOrderDayNumList(Integer statisDaysRung);
}
