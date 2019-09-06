package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nom, String ape);
	List<Cliente> findByNombresContainingOrApellidosContaining(String nom, String ape);
	
	List<Cliente> findByNombresIgnoreCase(String nombres);
	List<Cliente> findByNombresContainingIgnoreCase(String nombres);
	
	List<Cliente> findByApellidosIgnoreCase(String apellidos);
	List<Cliente> findByApellidosContainingIgnoreCase(String apellidos);
	
	List<Cliente> findByRucContaining(String ruc);
	Cliente findByRuc(String ruc);
	
	List<Cliente> findByAniversariosDelMes(Integer numeroDelMes, String estado);
	
	List<Cliente> findByEstado(String estado);
	
}
