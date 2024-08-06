package br.com.skill.Enums;

public enum UserRole {
	ADMIN("admin"), USER("user");
	
	private String role;
	
	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
