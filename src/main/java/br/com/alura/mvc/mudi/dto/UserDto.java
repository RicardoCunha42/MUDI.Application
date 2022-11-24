package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotEmpty;

public class UserDto {
	@NotEmpty(message = "O nome de usuário não pode estar vazio")
	private String username;
	@NotEmpty(message = "A senha não pode estar vazia")
	private String password;
	
	
	public UserDto() {
	}

	public UserDto(@NotEmpty String name, @NotEmpty String password) {
		this.username = name;
		this.password = password;
	}

	public String getUserame() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
