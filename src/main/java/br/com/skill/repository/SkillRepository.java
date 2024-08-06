package br.com.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skill.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Skill findByNome(String nome);
}
