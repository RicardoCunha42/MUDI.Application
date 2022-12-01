package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.mvc.mudi.model.User;

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

	public String getUsername() {
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

	public User toUser() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setUsername(this.getUsername());
		user.setPassword(encoder.encode(this.getPassword()));
		return user;
	}

}
