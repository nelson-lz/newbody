package py.edu.fpune.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Funcionario;

public interface IFuncionarioDAO extends JpaRepository<Funcionario, Integer>{

	List<Funcionario> findFirst4ByNombresContainingIgnoreCase(String nombre);
	List<Funcionario> findByNombresContainingIgnoreCase(String nombre);
	
	List<Funcionario> findFirst4ByApellidosContainingIgnoreCase(String apellido);
	List<Funcionario> findByApellidosContainingIgnoreCase(String apellido);
	
	List<Funcionario> findByNombresOrApellidosContainingIgnoreCase(String nombre, String apellido);
	
	List<Funcionario> findByCedulaContainingIgnoreCase(String cedula);
	
	List<Funcionario> findByEstadoIgnoreCase(String estado);
	
	List<Funcionario> findByFechaNacimientoBetween(Date desde, Date hasta);
}
