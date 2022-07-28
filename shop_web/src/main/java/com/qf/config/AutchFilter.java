package com.qf.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理跨域问题, shiro的拦截会导致跨域
 * @author zhaojian
 */
public class AutchFilter extends FormAuthenticationFilter {


    /**
     * Determines whether the current subject should be allowed to make the current request.
     * <p/>
     * The default implementation returns <code>true</code> if the user is authenticated.  Will also return
     * <code>true</code> if the {@link #isLoginRequest} returns false and the &quot;permissive&quot; flag is set.
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return <code>true</code> if request should be allowed access
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        获取本次请求方式
        String method = httpServletRequest.getMethod();

        if (method.equalsIgnoreCase("options"))
        {
            return true;
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }


    /**
     * 认证失败触发，默认跳转到spring-shiro中配置的loginUrl路径
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        return super.onAccessDenied(request, response);
    }

}
