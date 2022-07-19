package com.thiago.chamado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.repository.ColaboradorRepository;
import com.thiago.chamado.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ColaboradorRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Colaborador> col = repo.findByEmail(email);
		if (col == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(col.get().getId(), col.get().getEmail(), col.get().getSenha(), col.get().getPerfis());
	}
	
	
	/*@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Colaborador col = repo.findByEmail(email);
		if (col == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(col.getId(), col.getEmail(), col.getSenha(), col.getPerfis());
	}*/
}
