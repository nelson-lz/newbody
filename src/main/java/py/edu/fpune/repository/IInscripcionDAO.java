package py.edu.fpune.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;

public interface IInscripcionDAO extends JpaRepository<Inscripcion, Integer>{

	List<Inscripcion> findByClienteId(Cliente clienteId);
	
	List<Inscripcion> findByEstado(String estado);
	
	List<Inscripcion> findByFechaVencimientoBetween(Date desde, Date hasta);
	List<Inscripcion> findByFechaInscripcionBetween(Date desde, Date hasta);
	List<Inscripcion> findByFechaVencimientoBetweenAndEstadoLikeIgnoreCase(Date desde, Date hasta, String estado);
	List<Inscripcion> findByFechaInscripcionBetweenAndEstadoLikeIgnoreCase(Date desde, Date hasta, String estado);
	
	List<Inscripcion> findByTipoInscripcionIgnoreCase(String tipoInscripcion);
	
	List<Inscripcion> findByUsuarioIdAndFechaInscripcionBetween(Usuario usuario, Date desde, Date hasta);
}
