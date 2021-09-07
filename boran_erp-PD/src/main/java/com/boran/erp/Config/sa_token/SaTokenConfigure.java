package com.boran.erp.Config.sa_token;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @Author LT
 * @create 2021-06-17 10:21
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册sa-token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {

            // 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
            //SaRouterUtil.match("/**", "/userinfo/login", () -> StpUtil.checkLogin());
           /* System.out.println(request.getRequestPath());*/
            // 登录验证 -- 排除多个路径
            SaRouter.match(Arrays.asList("/**"), Arrays.asList(
                    "/userinfo/login",
                    //接口文档排除
                    "/doc.html/**",
                    "/webjars/js/**",
                    "/webjars/css/**",
                    "/v2/api-docs/**",
                    "/swagger-ui.html/**",
                    "/favicon.ico",
                    "/swagger-resources/**"
            ), () -> StpUtil.checkLogin());
            //继承maven的aop注解鉴权
            // 匹配 restful 风格路由
            SaRouter.match("/article/get/{id}", () -> StpUtil.checkPermission("article"));

            // 检查请求方式
            SaRouter.match("/notice/**", () -> {
                if (SpringMVCUtil.getRequest().equals(HttpMethod.GET.toString())) {
                    StpUtil.checkPermission("notice");
                }
            });

            // 在多账号模式下，可以使用任意StpUtil进行校验
            SaRouter.match("/user/**", () -> StpUtil.checkLogin());

        })).addPathPatterns("/**");
        /* registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");*/
    }
}

