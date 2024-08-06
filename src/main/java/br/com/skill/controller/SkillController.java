package br.com.skill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skill.entity.Skill;
import br.com.skill.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/skills")
@Tag(name = "Skills", description = "Controlador da classe Skills")
public class SkillController {
	@Autowired
	private SkillService service;
	
	@GetMapping()
	public List<Skill> getSkills() {
		return service.listarSkills();
	}
	
	@PostMapping("/cadastrar")
	public Skill postMethodName(@RequestBody Skill skill) {
		return service.inserirSkill(skill);
	}
	
	
}
