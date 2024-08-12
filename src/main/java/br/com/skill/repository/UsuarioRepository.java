package br.com.skill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.skill.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Optional<UserDetails> findByEmailUserDetails(@Param("email")String email);
}
