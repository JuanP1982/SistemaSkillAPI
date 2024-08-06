package br.com.skill.DTO;

import br.com.skill.entity.Usuario;

public class LoginResponseDTO {
	private String token;
	private UsuarioResponseDTO usuario;
	
	public LoginResponseDTO() {
		
	}
	
	public LoginResponseDTO(String tokenReceive, Usuario usuario) {
		this.token = tokenReceive;
		this.usuario = new UsuarioResponseDTO(usuario);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioResponseDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResponseDTO usuario) {
		this.usuario = usuario;
	}
	
	
}
