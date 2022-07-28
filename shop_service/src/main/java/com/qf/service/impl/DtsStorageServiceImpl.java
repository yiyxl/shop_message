package com.qf.service.impl;

import com.qf.mapper.DtsStorageMapper;
import com.qf.pojo.DtsStorage;
import com.qf.service.DtsStorageService;
import com.qf.util.AliyunOSSUtil;
import com.qf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 哇哈哈
 * @ClassName DtsStorageServiceImpl
 * @description: TODO
 * @datetime 2022年 07月 25日 21:51
 * @version: 1.0
 */
@Service
public class DtsStorageServiceImpl implements DtsStorageService {
          @Autowired
          private DtsStorageMapper dtsStorageMapper;
          @Override
          public DtsStorage add(String originalFilename, int size, String substring, byte[] bytes) throws Exception {
                    String key = UUID.randomUUID().toString();
                    String url = AliyunOSSUtil.uploadImage("http://oss-cn-beijing.aliyuncs.com", "LTAI5tFNykHcfV2WEimiT53b"
                            , "WvULCC8u8FG7fcjDj7dr4J3vg0hjpv", "gp12-file", originalFilename + substring, bytes);
                    DtsStorage dtsStorage = new DtsStorage();
                    dtsStorage.setDeleted(false);
                    dtsStorage.setKey(key);
                    dtsStorage.setAddTime(new Date());
                    dtsStorage.setName(originalFilename);
                    dtsStorage.setType(substring);
                    dtsStorage.setUrl(url);
                    dtsStorage.setUpdateTime(new Date());
                    dtsStorage.setSize(size);
                    dtsStorageMapper.insert(dtsStorage);
                    return dtsStorage;
          }
}
