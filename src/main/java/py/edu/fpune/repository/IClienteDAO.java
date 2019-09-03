package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNombres(String nombres);
	List<Cliente> findByApellidos(String apellidos);
	List<Cliente> findByEstado(String estado);
	List<Cliente> findByRuc(String ruc);
	
}
