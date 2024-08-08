package br.com.skill.entity;


import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class UsuarioSkillPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	public UsuarioSkillPK() {
		
	}
	
	public UsuarioSkillPK(Skill skill, Usuario usuario) {
		this.skill = skill;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "UsuarioSkillPK [usuario=" + usuario + ", skill=" + skill + "]";
	}
	
	
	
}
