package br.com.skill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.skill.entity.Skill;
import br.com.skill.entity.Usuario;
import br.com.skill.entity.UsuarioSkill;
import br.com.skill.entity.UsuarioSkillPK;
import jakarta.transaction.Transactional;


public interface UsuarioSKillRepository extends JpaRepository<UsuarioSkill, UsuarioSkillPK> {
	@Transactional
    @Modifying
    @Query("DELETE FROM UsuarioSkill us WHERE us.id.skill.id = :skillId AND us.id.usuario.id = :usuarioId")
	void deleteBySkillIdAndUsuarioId(@Param("skillId") Integer skillId, @Param("usuarioId") Integer usuarioId);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM UsuarioSkill us WHERE us.id.skill.id = :skillId")
    void deleteBySkillId(@Param("skillId") Integer skillId);
	
	Optional<UsuarioSkill> findByIdUsuarioAndIdSkill(Usuario usuario, Skill skill);
}
