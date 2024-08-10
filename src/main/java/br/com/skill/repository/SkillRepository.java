package br.com.skill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.skill.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Skill findByNome(String nome);
	
//	@Query("SELECT s FROM Skill s WHERE s.id NOT (SELECT us.skill.id FROM UsuarioSkill us WHERE us.usuario.id = :usuarioId)")
//    List<Skill> findByDisponivel(@Param("usuarioId") Integer usuarioId);
}
