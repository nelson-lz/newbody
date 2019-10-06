package py.edu.fpune.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.IUsuarioDAO;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private IUsuarioDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = dao.findByNombre(username);
		String roles = "ADMIN";
		String rol = us.getNivelAcceso();
		//TODO Hay que leer los roles que se desean cargar para el usuario obtenido (NOTE COMPLIQUES solo un rol por usuario!)
		//List<GrantedAuthority> roles = new ArrayList<>();
		//roles.add(new SimpleGrantedAuthority("ADMIN"));
		//UserDetails userDet = new User(us.getNombre(),us.getPass(),roles);
		//return userDet;
		return userBuilder(us.getNombre(), us.getPass(), rol);
	}
	
	private User userBuilder(String username, String password, String... roles) {
		boolean enable = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role: roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		}
		return new User(username, password, enable, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
