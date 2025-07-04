package desarrllo_aplicaciones.tp.model.Cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;
    private String nombre;

    private String password;

    private String avatar;

    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo = TipoUsuario.USUARIO;

    private boolean activado = false;

    // Datos solo para alumnos
    private String numeroTramiteDni;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarjeta_credito_id")
    private TarjetaCredito tarjetaCredito;

    private String fotoDniFrente;

    private String fotoDniDorso;

    private String edad;

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public enum TipoUsuario {
        USUARIO,
        ALUMNO
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // Métodos de conveniencia para el negocio
    public boolean esAlumno() {
        return this.tipo == TipoUsuario.ALUMNO;
    }

    public boolean puedeConvertirseAAlumno() {
        return this.activado && this.tipo == TipoUsuario.USUARIO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.tipo.name()));
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
        return this.activado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String alias) {
        this.username = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public String getNumeroTramiteDni() {
        return numeroTramiteDni;
    }

    public void setNumeroTramiteDni(String numeroTramiteDni) {
        this.numeroTramiteDni = numeroTramiteDni;
    }

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public String getFotoDniFrente() {
        return fotoDniFrente;
    }

    public void setFotoDniFrente(String fotoDniFrente) {
        this.fotoDniFrente = fotoDniFrente;
    }

    public String getFotoDniDorso() {
        return fotoDniDorso;
    }

    public void setFotoDniDorso(String fotoDniDorso) {
        this.fotoDniDorso = fotoDniDorso;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
