package com.mibombay.mvprecetas.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibombay.mvprecetas.models.Empresa;
import com.mibombay.mvprecetas.repositories.EmpresaRepository;

@Service
public class TenantService {

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Obtiene o crea la empresa demo para desarrollo local
     */
    public Empresa crearDemoEmpresa() {
        Empresa demo = empresaRepository.findBySubdominio("demo").orElse(null);

        if (demo == null) {
            demo = Empresa.builder()
                    .subdominio("demo")
                    .nombreEmpresa("Empresa de Demostración")
                    .email("demo@mibombay.com")
                    .estaActiva(true)
                    .tipoDePlan("GRATIS")
                    .fechaCreacion(LocalDateTime.now())
                    .maxProducto(10)
                    .maxUsuario(2)
                    .build();

            demo = empresaRepository.save(demo);

        }

        return demo;
    }

    /**
     * Busca una empresa por su subdominio
     */
    public Empresa findEmpresaBySubdominio(String subdominio) {
        return empresaRepository.findBySubdominio(subdominio).orElse(null);
    }

    /**
     * Verifica si un subdominio está disponible para registro
     */
    public boolean isSubdominioDisponible(String subdominio) {
        return !empresaRepository.findBySubdominio(subdominio).isPresent();
    }

    public String extractSubdomain(String host) {
        if (host.endsWith(".mibombay.com")) {
            String subdominio = host.replace(".mibombay.com", "");
            if (subdominio.startsWith("www.")) {
                subdominio = subdominio.replace("www.", "");
            }
            return subdominio;
        }
        return null;
    }
}