//package cn.x2yu.blog.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//public class CORSConfiguration extends WebMvcConfigurationSupport {
//
//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**");
//        registry.addMapping("/admin/**");
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
//                .allowCredentials(false).maxAge(3600);
//        super.addCorsMappings(registry);
//    }
//}
