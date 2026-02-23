package com.mibombay.mvprecetas.tenant;

import com.mibombay.mvprecetas.models.Empresa;
import com.mibombay.mvprecetas.services.TenantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private TenantService tenantService; // Ahora usamos el service

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String host = request.getServerName();

        // CASO 1: Landing page
        if (host.equals("mibombay.com") || host.equals("www.mibombay.com")) {
            return true;
        }

        // CASO 2: Desarrollo local
        if (host.equals("localhost") || host.equals("127.0.0.1")) {

            // ⭐ Usamos el servicio para obtener/crear la empresa demo
            Empresa demo = tenantService.crearDemoEmpresa();

            TenantContext.setCurrentTenant(demo.getId().toString());
            request.setAttribute("empresaActual", demo);
            return true;
        }

        // CASO 3: Subdominio de cliente
        if (host.endsWith(".mibombay.com")) {
            String subdominio = tenantService.extractSubdomain(host);

            // ⭐ Usamos el servicio para buscar la empresa
            Empresa empresa = tenantService.findEmpresaBySubdominio(subdominio);

            if (empresa == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "La empresa '" + subdominio + "' no existe. " +
                                "<a href='https://mibombay.com'>Registra tu negocio aquí</a>");
                return false;
            }

            TenantContext.setCurrentTenant(empresa.getId().toString());
            request.setAttribute("empresaActual", empresa);
            return true;
        }

        // CASO 4: Cualquier otro dominio
        response.sendRedirect("https://mibombay.com");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            Empresa empresa = (Empresa) request.getAttribute("empresaActual");
            if (empresa != null) {
                modelAndView.addObject("empresa", empresa);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {

        TenantContext.clear();
    }

}