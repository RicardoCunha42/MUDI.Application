package br.com.alura.mvc.mudi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		return new CustomUserDetails(user);
	}

}
