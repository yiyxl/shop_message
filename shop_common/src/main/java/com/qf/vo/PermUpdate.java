package com.qf.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 哇哈哈
 * @ClassName PermUpdate
 * @description: TODO
 * @datetime 2022年 07月 26日 23:32
 * @version: 1.0
 */
@Data
public class PermUpdate {
          private Integer roleId;
          private List<String> permissions;
}
