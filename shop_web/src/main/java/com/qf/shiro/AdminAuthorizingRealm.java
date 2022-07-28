package com.qf.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.mapper.DtsAdminMapper;
import com.qf.mapper.DtsPermissionMapper;
import com.qf.mapper.DtsRoleMapper;
import com.qf.pojo.DtsAdmin;
import com.qf.pojo.DtsPermission;
import com.qf.pojo.DtsRole;
import com.qf.util.bcrypt.BCryptPasswordEncoder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 哇哈哈
 * @ClassName AdminAuthorizingRealm
 * @description: TODO
 * @datetime 2022年 07月 21日 15:03
 * @version: 1.0
 * 授权
 *        RBAC模式
 *                  基于角色验证
 *
 */
public class AdminAuthorizingRealm extends AuthorizingRealm {
          @Autowired
          private DtsAdminMapper dtsAdminMapper;
          @Autowired
          private DtsRoleMapper dtsRoleMapper;
          @Autowired
          private DtsPermissionMapper dtsPermissionMapper;

//          授权
          @Override
          protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                    DtsAdmin dtsAdmin = (DtsAdmin) principalCollection.getPrimaryPrincipal();
                    Integer[] id = dtsAdmin.getRoleIds();
                    List<DtsRole> dtsRoles = dtsRoleMapper.selectBatchIds(Arrays.asList(id));
                    Set<String> collect = dtsRoles.stream().map(DtsRole::getName).collect(Collectors.toSet());
                    List<DtsPermission> roleId = dtsPermissionMapper.selectList(new QueryWrapper<DtsPermission>().in("role_id", id));
                    Set<String> set = roleId.stream().map(DtsPermission::getPermission).collect(Collectors.toSet());
                    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                    authorizationInfo.setRoles(collect);
                    authorizationInfo.setStringPermissions(set);
                    return authorizationInfo;
          }
//          认证
          @Override
          protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                    UsernamePasswordToken usernamePassword = (UsernamePasswordToken) authenticationToken;
                    String username = usernamePassword.getUsername();
                    String password = new String(usernamePassword.getPassword());
//                    用户名
//                    加密后密码
                    DtsAdmin dtsAdmin = dtsAdminMapper.selectOne(new QueryWrapper<DtsAdmin>().eq("username", username));
                    if (dtsAdmin == null){
                              throw new UnknownAccountException();
                    }
                    if (!new BCryptPasswordEncoder().matches(password,dtsAdmin.getPassword())){
                              throw new IncorrectCredentialsException();
                    }
//                    逻辑删除 0：用户正常 1：用户被锁定 2：删除
                    if (dtsAdmin.getDeleted()){
                              throw new AuthenticationException();
                    }
                    return new SimpleAuthenticationInfo(dtsAdmin,password,this.getName()) ;
          }
}
