package br.com.skill.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.skill.Enums.UserRole;
import br.com.skill.entity.Usuario;

public class UsuarioResponseDTO {
	private Integer id;
	private String email;
	private UserRole role;
	private List<UsuarioSkillResponseDTO> skills = new ArrayList<>();

	public UsuarioResponseDTO() {

	}

	public UsuarioResponseDTO(Usuario u) {
		id = u.getId();
		email = u.getEmail();
		role = u.getRole();
		if (!u.getSkills().isEmpty()) {
			this.skills = u.getSkills().stream().map(s -> new UsuarioSkillResponseDTO(s)).collect(Collectors.toList());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UsuarioSkillResponseDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<UsuarioSkillResponseDTO> skills) {
		this.skills = skills;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
