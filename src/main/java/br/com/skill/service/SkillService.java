package br.com.skill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.skill.entity.Skill;
import br.com.skill.repository.SkillRepository;

@Service
public class SkillService {
	@Autowired
	private SkillRepository repository;
	
	public List<Skill> listarSkills(){
		return repository.findAll();
	}
	
	public Skill inserirSkill(Skill skill) {
		if(repository.findByNome(skill.getNome()) != null) throw new RuntimeException("Tratar skill já existente");
		return repository.save(skill);
	}
}
