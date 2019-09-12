package py.edu.fpune.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.fpune.model.CobroMensualidad;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;

public interface ICobroMensualidadDAO extends JpaRepository<CobroMensualidad, Integer>{

	List<CobroMensualidad> findByEstadoIgnoreCaseAndFechaPagoBetween(String estado, Date desde, Date hasta);
	
	List<CobroMensualidad> findByUsuarioIdAndFechaPagoBetween(Usuario usuarioId, Date desde, Date hasta);
	List<CobroMensualidad> findByUsuarioIdAndFechaPagoEquals(Usuario usuarioId, Date hoy);
	List<CobroMensualidad> findByFechaPagoEquals(Date hoy);
	List<CobroMensualidad> findByFechaPagoEqualsAndEstadoEqualsIgnoreCase(Date hoy, String estado);
	
	List<CobroMensualidad> findFirstByInscripcionId(Inscripcion inscripcionId);
	
}
