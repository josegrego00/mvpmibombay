package com.mibombay.mvprecetas.config;

import com.mibombay.mvprecetas.tenant.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor)
                .addPathPatterns("/**") // Aplica a TODAS las rutas
                .excludePathPatterns(
                        "/css/**", // Excluye recursos estáticos
                        "/js/**",
                        "/images/**");
    }
}