package py.edu.fpune.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByNombre(String nombre);
	
	Usuario findByNivelAcceso(String nivelAcceso);
}
