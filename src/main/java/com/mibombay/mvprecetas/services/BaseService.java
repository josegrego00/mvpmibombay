package com.mibombay.mvprecetas.services;

import com.mibombay.mvprecetas.models.Empresa;
import com.mibombay.mvprecetas.repositories.EmpresaRepository;
import com.mibombay.mvprecetas.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService {

    @Autowired
    protected EmpresaRepository empresaRepository; // protected para que lo vean las hijas

    /**
     * Obtiene el ID de la empresa actual desde el TenantContext
     */
    protected Long getCurrentEmpresaId() {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId == null) {
            throw new RuntimeException("No hay empresa en contexto");
        }
        return Long.parseLong(tenantId);
    }

    /**
     * Obtiene la entidad Empresa completa de la empresa actual
     */
    protected Empresa getCurrentEmpresa() {
        Long id = getCurrentEmpresaId();
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
    }

    /**
     * Verifica si el usuario actual puede crear más productos (límite plan)
     */
    protected boolean canCreateProducto(int cantidadActual) {
        Empresa empresa = getCurrentEmpresa();
        return cantidadActual < empresa.getMaxProducto();
    }

    /**
     * Verifica si el usuario actual puede crear más usuarios (límite plan)
     */
    protected boolean canCreateUsuario(int usuariosActuales) {
        Empresa empresa = getCurrentEmpresa();
        return usuariosActuales < empresa.getMaxUsuario();
    }
}