package com.mibombay.mvprecetas.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mibombay.mvprecetas.models.Usuario;
import com.mibombay.mvprecetas.repositories.UsuarioRepositorio;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepositorio repositorio;

    public CustomUserDetailsService(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repositorio.findByNombreUsuarioByEmpresa_Id(username, 1L) // TODO: empresaId dinámico
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new CustomUserDetails(usuario);
    }

}
