package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.fpune.model.CobroMensualidad;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;

public interface ICobroMensualidadDAO extends JpaRepository<CobroMensualidad, Integer>{

	List<CobroMensualidad> findByEstado(String estado);
	
	List<CobroMensualidad> findByUsuarioId(Usuario usuarioId);
	List<CobroMensualidad> findByInscripcionId(Inscripcion inscripcionId);
}
