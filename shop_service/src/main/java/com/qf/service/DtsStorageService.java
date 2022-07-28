package com.qf.service;

import com.qf.pojo.DtsStorage;

/**
 * @author 哇哈哈
 * @ClassName DtsStorageService
 * @description: TODO
 * @datetime 2022年 07月 25日 21:51
 * @version: 1.0
 */
public interface DtsStorageService {
          DtsStorage add(String originalFilename, int size, String substring, byte[] bytes) throws Exception;
}
