package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.Inscripcion;

public interface IInscripcionDAO extends JpaRepository<Inscripcion, Integer>{

	List<Inscripcion> findByClienteId(Cliente clienteId);
	
	List<Inscripcion> findByEstado(String estado);
}
