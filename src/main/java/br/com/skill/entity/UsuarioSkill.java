package br.com.skill.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioSkill {
	
	@EmbeddedId
	private UsuarioSkillPK id = new UsuarioSkillPK();
	
	private Integer nivel;
	
	public UsuarioSkill() {
		
	}
	
	public UsuarioSkill(Usuario usuario, Skill skill) {
		this.id.setSkill(skill);
		this.id.setUsuario(usuario);
	}

	public UsuarioSkillPK getId() {
		return id;
	}

	public void setId(UsuarioSkillPK id) {
		this.id = id;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "UsuarioSkill [id=" + id + ", nivel=" + nivel + "]";
	}
	
	
	
}
