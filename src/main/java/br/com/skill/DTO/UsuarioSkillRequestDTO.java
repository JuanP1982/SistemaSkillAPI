package br.com.skill.DTO;

import java.util.List;

public class UsuarioSkillRequestDTO {
	private Integer usuarioId;
	private List<Integer> skillsId;
	private List<Integer> niveis;

	public UsuarioSkillRequestDTO() {

	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<Integer> getSkillsId() {
		return skillsId;
	}

	public void setSkillsId(List<Integer> skillsId) {
		this.skillsId = skillsId;
	}

	public List<Integer> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<Integer> niveis) {
		this.niveis = niveis;
	}

	
	
	

}
