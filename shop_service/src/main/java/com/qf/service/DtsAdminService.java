package com.qf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qf.pojo.DtsAdmin;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName DtsAdminService
 * @description: TODO
 * @datetime 2022年 07月 25日 14:58
 * @version: 1.0
 */
public interface DtsAdminService {
          IPage<DtsAdmin> findPageByQuery(Integer page, Integer limit, String sort, String order, String username);

          List<DtsAdmin> findAll();

          Integer findCount();

          int updateAdmin(DtsAdmin dtsAdmin);

          int deleteById(DtsAdmin dtsAdmin);

          int insertAdmin(DtsAdmin dtsAdmin);
}
