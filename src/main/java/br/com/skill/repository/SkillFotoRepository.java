package br.com.skill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skill.entity.Skill;
import br.com.skill.entity.SkillFoto;

public interface SkillFotoRepository extends JpaRepository<SkillFoto, Integer> {
	Optional<SkillFoto> findBySkill(Skill skill);
}
