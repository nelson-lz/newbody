package py.edu.fpune.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.repository.IClienteDAO;
import py.edu.fpune.repository.IInscripcionDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(InscripcionController.INSCRIPCION)
public class InscripcionController {
	
	public static final String INSCRIPCION = "/api/v0/inscripcion";
	public static final String STATE = "/state";
	public static final String ID = "/{id}";
	public static final String CLIENTE = "/cliente";
	public static final String TIPO = "/tipo/{tipo}";
	public static final String ESTADO = "/estado/{estado}";
	public static final String USUARIO = "/usuario";
	public static final String BETWEEN = "/{desde}/{hasta}";
	public static final String VENCI = "/venceEntre";
	public static final String INSCRI = "/inscriEntre";
	
	@Autowired
	IInscripcionDAO dao;
	@Autowired
	IClienteDAO daoCli;
	@Autowired
	IUsuarioDAO daoUser;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ID)
	Inscripcion getInscripcionPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = CLIENTE + ID)
	List<Inscripcion> getInscripcionesPorCliente(@PathVariable Integer id){
		Cliente cli = daoCli.findById(id).get();
		return dao.findByClienteId(cli);
	}
	@GetMapping(value = VENCI + BETWEEN)
	List<Inscripcion> getInscripcionesVencimientoEntre(@PathVariable Date desde, @PathVariable Date hasta){
		return dao.findByFechaVencimientoBetween(desde, hasta);
	}
	@GetMapping(value = INSCRI + BETWEEN)
	List<Inscripcion> getInscripcionesRegistradasEntre(@PathVariable Date desde, @PathVariable Date hasta){
		return dao.findByFechaInscripcionBetween(desde, hasta);
	}
	@GetMapping(value = CLIENTE + ID + BETWEEN)
	List<Inscripcion> getInscripcionesPorClienteInscripcionEntre(@PathVariable Integer id, 
																 @PathVariable Date desde, 
																 @PathVariable Date hasta){
		Cliente cli = daoCli.findById(id).get();
		return dao.findByClienteIdAndFechaInscripcionBetween(cli, desde, hasta);
	}
	@GetMapping(value = ESTADO + BETWEEN)
	List<Inscripcion> getInscripcionesPorEstadoVenciEntre(@PathVariable String estado, 
														  @PathVariable Date desde, 
														  @PathVariable Date hasta){
		return dao.findByFechaVencimientoBetweenAndEstadoLikeIgnoreCase(desde, hasta, estado);
	}
	
	@DeleteMapping(value = ID)
	void deleteInscripcionPorId(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping
	Inscripcion newInscripcion(@RequestBody Inscripcion inscripcion) {
		return dao.save(inscripcion);
	}
	
	@PutMapping(value = ID)
	Inscripcion updateInscripcionPorId(@PathVariable Integer id, @RequestBody Inscripcion inscripcion) {
		return dao.findById(id).map(inscr -> {
			if(inscripcion.getClienteId()!=null) inscr.setClienteId(inscripcion.getClienteId());
			if(inscripcion.getEstado()!=null) inscr.setEstado(inscripcion.getEstado());
			if(inscripcion.getFechaInscripcion()!=null) inscr.setFechaInscripcion(inscripcion.getFechaInscripcion());
			if(inscripcion.getFechaModificacion()!=null) inscr.setFechaModificacion(inscripcion.getFechaModificacion());
			if(inscripcion.getFechaVencimiento()!=null) inscr.setFechaVencimiento(inscripcion.getFechaVencimiento());
			if(inscripcion.getMonedaId()!=null) inscr.setMonedaId(inscripcion.getMonedaId());
			if(inscripcion.getMontoMensualidad()!=null) inscr.setMontoMensualidad(inscripcion.getMontoMensualidad());
			if(inscripcion.getTipoInscripcion()!=null) inscr.setTipoInscripcion(inscripcion.getTipoInscripcion());
			if(inscripcion.getUsuarioId()!=null) inscr.setUsuarioId(inscripcion.getUsuarioId());
			return dao.save(inscr);
		}).orElseGet(()->{
			inscripcion.setId(id);
			return dao.save(inscripcion);
		});
	}
}
