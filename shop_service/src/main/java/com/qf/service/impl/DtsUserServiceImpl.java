package com.qf.service.impl;

import com.qf.mapper.DtsUserMapper;
import com.qf.service.DtsUserService;
import com.qf.vo.DayStatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsUserServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 22日 16:57
 * @version: 1.0
 */
@Service
public class DtsUserServiceImpl  implements DtsUserService {

          @Autowired
          private DtsUserMapper dtsUserMapper;
          @Override
          public Integer selectCount() {
                    return dtsUserMapper.selectCount(null);
          }

          @Override
          public List<DayStatis> findOrderDayNumList(Integer statisDaysRung) {
                    return dtsUserMapper.findOrderDayNumList(statisDaysRung);
          }
}
