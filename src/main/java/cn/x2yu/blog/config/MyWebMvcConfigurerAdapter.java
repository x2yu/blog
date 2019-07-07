//package cn.x2yu.blog.config;
//
//import cn.x2yu.blog.interceptor.BackInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//
//        registry.addInterceptor(new BackInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/toLogin", "/login");
//        registry.addInterceptor(new BackInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin", "", "/js/**", "/css/**", "/img/**");
//        super.addInterceptors(registry);
//
//    }
//
//
////   /**
////    * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
////    * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面
//// */
////    @Override
////    protected void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/toLogin").setViewName("login/login.html");
////        super.addViewControllers(registry);
////    }
//}
