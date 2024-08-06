package br.com.skill.DTO;

import java.util.List;

import br.com.skill.Enums.UserRole;


public class UsuarioRequestDTO {
	private String email;
	private String senha;
	private UserRole role;
	
	
	public UsuarioRequestDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}



	

	

	
	
	
}
