package com.mibombay.mvprecetas.security;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mibombay.mvprecetas.models.Usuario;

public class CustomUserDetails implements UserDetails {

    private String nombreUsuario;
    private Long id;
    private String contrasenna;
    private String rol;
    private Long empresaId;

    public CustomUserDetails(Usuario usuario) {
        this.nombreUsuario = usuario.getNombre();
        this.id = usuario.getId();
        this.contrasenna = usuario.getContrasenna();
        this.rol = usuario.getRol();
        this.empresaId = usuario.getEmpresa().getId();
    }

    public Long getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return contrasenna;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}
