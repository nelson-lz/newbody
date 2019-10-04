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

import py.edu.fpune.model.Moneda;
import py.edu.fpune.repository.IMonedaDAO;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(MonedaController.MENEDA)
public class MonedaController {

	public static final String MENEDA = "/api/v0/moneda";
	public static final String ID = "/{id}";
	public static final String NAME = "/nombre/{nombre}";
	public static final String DATE = "/fecha/{fecha}";
	public static final String STATE = "/state";
	
	@Autowired
	IMonedaDAO dao;
	
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ID)
	Moneda getMonedaPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = NAME)
	List<Moneda> getMonedaPorNombre(@PathVariable String nombre) {
		return dao.findByDescripcionContainingIgnoreCase(nombre);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@DeleteMapping(value = ID)
	void deleteMonedaPorId(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@PostMapping
	Moneda newMoneda(@RequestBody Moneda moneda) {
		return dao.save(moneda);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@PutMapping(value = ID)
	Moneda updateMonedaPorId(@PathVariable Integer id, @RequestBody Moneda moneda) {
		return dao.findById(id).map(money -> {
			if(moneda.getDescripcion() != null) money.setDescripcion(moneda.getDescripcion());
			if(moneda.getEstado() != null) money.setEstado(moneda.getEstado());
			return dao.save(money);
		}).orElseGet(()-> {
			moneda.setId(id);
			return dao.save(moneda);
		});
	}
}
