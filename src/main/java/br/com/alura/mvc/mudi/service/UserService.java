package br.com.alura.mvc.mudi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.dto.UserDto;
import br.com.alura.mvc.mudi.model.Role;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.RoleRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
@Service
public class UserService  {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Optional<Role> maybeRole = this.roleRepository.findById("ROLE_ADMIN");
		user.setRoles(maybeRole.get());
		
		this.userRepository.save(user);
	}
}
