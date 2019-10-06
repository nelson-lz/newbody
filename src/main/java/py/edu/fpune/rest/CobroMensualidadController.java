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

import py.edu.fpune.model.CobroMensualidad;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.ICobroMensualidadDAO;
import py.edu.fpune.repository.IInscripcionDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(CobroMensualidadController.COBRO)
public class CobroMensualidadController {

	public static final String COBRO = "/api/v0/cobroMensualidad";
	public static final String STATE = "/state";
	public static final String ID = "/{id}";
	public static final String INSCRIPCION = "/inscripcion";
	public static final String FECHA = "/fecha";
	public static final String BETWEEN = "/{desde}/{hasta}";
	public static final String ESTADO = "/estado/{estado}";
	public static final String USUARIO = "/usuario";
	
	@Autowired
	ICobroMensualidadDAO dao;
	@Autowired
	IInscripcionDAO daoInscripcion;
	@Autowired
	IUsuarioDAO daoUser;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ID)
	CobroMensualidad getCobroPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = INSCRIPCION + ID)
	List<CobroMensualidad> getCobrosPorInscripcion(@PathVariable Integer id){
		Inscripcion inscr = daoInscripcion.findById(id).get();
		return dao.findByInscripcionId(inscr);
	}
	@GetMapping(value = FECHA + BETWEEN + ESTADO)
	List<CobroMensualidad> getCobrosEntreFechasYEstado(@PathVariable Date desde, 
												@PathVariable Date hasta, 
												@PathVariable String estado){
		return dao.findByEstadoIgnoreCaseAndFechaPagoBetween(estado, desde, hasta);
	}
	@GetMapping(value = USUARIO + ID + BETWEEN + ESTADO)
	List<CobroMensualidad> getCobrosPorUsuarioEntreFecha(@PathVariable Integer id, 
														 @PathVariable Date desde, 
														 @PathVariable Date hasta){
		Usuario user = daoUser.findById(id).get();
		return dao.findByUsuarioIdAndFechaPagoBetween(user, desde, hasta);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = ID)
	void deleteCobroPorId(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping
	CobroMensualidad newCobroMensualidad(@RequestBody CobroMensualidad cobroMensualidad) {
		return dao.save(cobroMensualidad);
	}
	
	@PutMapping(value = ID)
	CobroMensualidad updateCobroMensualidadPorId(@PathVariable Integer id, @RequestBody CobroMensualidad cobroMensualidad) {
		return dao.findById(id).map(cobro -> {
			if(cobroMensualidad.getDescuento() != null) cobro.setDescuento(cobroMensualidad.getDescuento());
			if(cobroMensualidad.getEstado() != null) cobro.setEstado(cobroMensualidad.getEstado());
			if(cobroMensualidad.getFechaPago() != null) cobro.setFechaPago(cobroMensualidad.getFechaPago());
			if(cobroMensualidad.getInscripcionId() != null) cobro.setInscripcionId(cobroMensualidad.getInscripcionId());
			if(cobroMensualidad.getMonedaId() != null) cobro.setMonedaId(cobroMensualidad.getMonedaId());
			if(cobroMensualidad.getMontoPagado() != null) cobro.setMontoPagado(cobroMensualidad.getMontoPagado());
			if(cobroMensualidad.getTotal() != null) cobro.setTotal(cobroMensualidad.getTotal());
			if(cobroMensualidad.getUsuarioId() != null) cobro.setUsuarioId(cobroMensualidad.getUsuarioId());
			return dao.save(cobro);
		}).orElseGet(()->{
			cobroMensualidad.setId(id);
			return dao.save(cobroMensualidad);
		});
	}
}
