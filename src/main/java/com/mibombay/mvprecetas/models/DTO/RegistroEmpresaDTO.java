package com.mibombay.mvprecetas.models.DTO;

import lombok.Data;

@Data
public class RegistroEmpresaDTO {
    private String nombreEmpresa;
    private String subdominio;
    private String email;
    private String password;
    private String nombreAdmin; // Nombre del administrador
}