package com.mibombay.mvprecetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mibombay.mvprecetas.models.Empresa;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findBySubdominio(String subdominio);

    boolean existsBySubdominio(String subdominio); // Para registro

    Optional<Empresa> findByEmail(String email);
}