package com.mibombay.mvprecetas.controllers;

import com.mibombay.mvprecetas.models.DTO.RegistroEmpresaDTO;
import com.mibombay.mvprecetas.models.Empresa;
import com.mibombay.mvprecetas.models.Usuario;
import com.mibombay.mvprecetas.repositories.EmpresaRepository;
import com.mibombay.mvprecetas.repositories.UsuarioRepositorio;
import com.mibombay.mvprecetas.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
public class LandingController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("registroDTO", new RegistroEmpresaDTO());
        return "registro-empresa";
    }

    @PostMapping("/registro")
    public String registrarEmpresa(
            @ModelAttribute("registroDTO") RegistroEmpresaDTO dto,
            Model model) {

        // 1. Validar que el subdominio no esté ocupado
        if (!tenantService.isSubdominioDisponible(dto.getSubdominio())) {
            model.addAttribute("error", "El subdominio '" + dto.getSubdominio() + "' ya está ocupado");
            return "registro-empresa";
        }

        // 2. Validar que el email no esté ocupado
        if (empresaRepository.findByEmail(dto.getEmail()).isPresent()) {
            model.addAttribute("error", "El email '" + dto.getEmail() + "' ya está registrado");
            return "registro-empresa";
        }

        // 3. Crear la empresa (con valores por defecto de plan GRATIS)
        Empresa nuevaEmpresa = Empresa.builder()
                .subdominio(dto.getSubdominio().toLowerCase())
                .nombreEmpresa(dto.getNombreEmpresa())
                .email(dto.getEmail())
                .estaActiva(true)
                .tipoDePlan("GRATIS")
                .fechaCreacion(LocalDateTime.now())
                .maxProducto(10) // Límite plan gratis
                .maxUsuario(2) // Límite plan gratis
                .build();

        nuevaEmpresa = empresaRepository.save(nuevaEmpresa);

        // 4. Crear el usuario ADMIN de esa empresa
        Usuario admin = Usuario.builder()
                .nombre(dto.getNombreAdmin())
                .nombreUsuario(dto.getEmail()) // El email es el username
                .contrasenna(passwordEncoder.encode(dto.getPassword()))
                .rol("ADMIN")
                .estaActivo(true)
                .empresa(nuevaEmpresa)
                .build();

        usuarioRepositorio.save(admin);

        // 5. Redirigir al subdominio creado
        return "redirect:http://" + dto.getSubdominio().toLowerCase() + ".localhost:8080";
        // ⚠️ EN PRODUCCIÓN SERÁ: redirect:https://" + dto.getSubdominio() +
        // ".mibombay.com"
    }
}