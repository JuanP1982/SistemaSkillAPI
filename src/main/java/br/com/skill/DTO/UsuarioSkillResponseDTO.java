package br.com.skill.DTO;

import br.com.skill.entity.Skill;
import br.com.skill.entity.UsuarioSkill;

public class UsuarioSkillResponseDTO {
	private Skill skill;
	private Integer nivel;
	
	public UsuarioSkillResponseDTO(UsuarioSkill user) {
		this.nivel = user.getNivel();
		this.skill = user.getId().getSkill();
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	
}
