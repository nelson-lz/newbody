package py.edu.fpune.rest;

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

import py.edu.fpune.model.Cotizacion;
import py.edu.fpune.model.Moneda;
import py.edu.fpune.repository.ICotizacionDAO;
import py.edu.fpune.repository.IMonedaDAO;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(CotizacionController.COTIZACION)
public class CotizacionController {

	public static final String COTIZACION = "/api/v0/cotizacion";
	public static final String ID = "/{id}";
	public static final String DATE = "/fecha/{fecha}";
	public static final String MONEY = "/moneda";
	public static final String ESTADO = "/estado/{estado}";
	public static final String STATE = "/state";
	public static final String LAST = "/ultimaCotizacion";
	
	@Autowired
	ICotizacionDAO dao;
	@Autowired
	IMonedaDAO daoMoney;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ID)
	Cotizacion getCotizacionPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = MONEY + ID)
	List<Cotizacion> getCotizacionesPorMoneda(@PathVariable Integer id){
		Moneda money = daoMoney.findById(id).get();
		return dao.findByMonedaIdOrderByFechaAsc(money);
	}
	@GetMapping(value = LAST + ID)
	Cotizacion getUltimaCotizacion(@PathVariable Integer id) {
		Moneda money = daoMoney.findById(id).get();
		return dao.findFirstByMonedaIdOrderByFechaDesc(money);
	}
	@GetMapping(value = ESTADO)
	List<Cotizacion> getCotizacionesPorEstado(@PathVariable String estado){
		return dao.findByEstadoIgnoreCaseOrderByFechaAsc(estado);
	}
	
	@DeleteMapping(value = ID)
	void deleteCotizacionPorId(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping
	Cotizacion newCotizacion(@RequestBody Cotizacion cotizacion) {
		System.out.println("COTIZACION:"+cotizacion);
		//TODO ver por que guarda con un dia menos ej: json {date:"2019-10-04"} y guarda 2019-10-03
		return dao.save(cotizacion);
	}
	
	@PutMapping(value = ID)
	Cotizacion updateCotizacionPorId(@PathVariable Integer id, @RequestBody Cotizacion cotizacion) {
		return dao.findById(id).map( coti -> {
			if(cotizacion.getCotizacion() != 0) coti.setCotizacion(cotizacion.getCotizacion());
			if(cotizacion.getFecha() != null) coti.setFecha(cotizacion.getFecha());
			if(cotizacion.getMonedaId() != null) coti.setMonedaId(cotizacion.getMonedaId());
			if(cotizacion.getEstado() != null) coti.setEstado(cotizacion.getEstado());
			return dao.save(coti);
		}).orElseGet(()-> {
			cotizacion.setId(id);
			return dao.save(cotizacion);
		});
	}
}
