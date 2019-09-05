package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByNombre(String nombre);
	
	List<Usuario> findByNivelAccesoIgnoreCase(String nivelAcceso);
	
	List<Usuario> findByEstadoIgnoreCase(String estado);
}
