package com.mibombay.mvprecetas.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String subdominio;
    @Column(unique = true)
    private String nombreEmpresa;
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean estaActiva;

    private String tipoDePlan;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracionPro;
    private Integer maxProducto;
    private Integer maxUsuario;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Ingrediente> listaIngredientes = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<DetalleReceta> listaDetalleRecetas = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Receta> recetas = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Producto> productos = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<DetalleCompra> detalleCompras = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Cliente> clientes = new ArrayList<>();
}
