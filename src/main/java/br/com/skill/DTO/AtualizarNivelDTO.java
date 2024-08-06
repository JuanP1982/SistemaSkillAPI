package br.com.skill.DTO;

public class AtualizarNivelDTO {
	private Integer usuarioId;
	private Integer skillId;
	private Integer nivel;
	
	public AtualizarNivelDTO() {
		
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	
}
