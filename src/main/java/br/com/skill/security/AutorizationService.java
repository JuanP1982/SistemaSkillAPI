package br.com.skill.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.skill.exceptions.LoginIncorretoException;
import br.com.skill.repository.UsuarioRepository;

@Service
public class AutorizationService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		Optional<UserDetails> user = repository.findByEmailUserDetails(username);
		if(user.isEmpty()) throw new RuntimeException("Usuário não cadastrado!");
		return user.get();
	}

}
