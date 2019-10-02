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

import py.edu.fpune.model.Cliente;
import py.edu.fpune.repository.IClienteDAO;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(ClienteController.CLIENTE)
public class ClienteController {

	public static final String CLIENTE = "/api/v0/cliente";
	public static final String ID = "/{id}";
	public static final String NAME = "/nombre/{nombre}";
	public static final String LASTNAME = "/apellido/{apellido}";
	public static final String NOMAPE = "/completeName/{nomape}";
	public static final String RUC ="/ruc/{ruc}";
	public static final String ESTADO = "/estado/{estado}";
	public static final String STATE = "/state";
	public static final String ALL = "/all";
	
	@Autowired
	private IClienteDAO dao;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ALL)
	List<Cliente> getAllClientes(){
		return dao.findAll();
	}
	@GetMapping(value = NOMAPE)
	List<Cliente> getClientesPorNomApe(@PathVariable String nomape){
		return dao.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(nomape, nomape);
	}
	@GetMapping(value = NAME)
	List<Cliente> getClientesPorNombre(@PathVariable String nombre){
		return dao.findByNombresContainingIgnoreCase(nombre);
	}
	@GetMapping(value = LASTNAME)
	List<Cliente> getClientesPorApellido(@PathVariable String apellido){
		return dao.findByApellidosContainingIgnoreCase(apellido);
	}
	@GetMapping(value = ID)
	Cliente getClientePorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = RUC)
	List<Cliente> getClientesPorRuc(@PathVariable String ruc){
		return dao.findByRucContaining(ruc);
	}
	@GetMapping(value = ESTADO)
	List<Cliente> getClientesPorEstado(@PathVariable String estado){
		return dao.findByEstado(estado);
	}
	
	@DeleteMapping(value = ID)
	void  deleteClientePorId(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping
	Cliente newCliente(@RequestBody Cliente cliente) {
		return dao.save(cliente);
	}
	
	@PutMapping(value = ID)
	Cliente updateClientePorId(@PathVariable Integer id, @RequestBody Cliente cliente) {
		System.out.println("Antes del MAP"+cliente);
		return dao.findById(id).map( cli ->{
			if(cliente.getRuc() != null) cli.setRuc(cliente.getRuc());
			if(cliente.getNombres() != null) cli.setNombres(cliente.getNombres());
			if(cliente.getApellidos() != null) cli.setApellidos(cliente.getApellidos());
			if(cliente.getTelefono1() != null) cli.setTelefono1(cliente.getTelefono1());
			if(cliente.getTelefono2() != null) cli.setTelefono2(cliente.getTelefono2());
			if(cliente.getDireccion() != null) cli.setDireccion(cliente.getDireccion());
			if(cliente.getAniversario() != null) cli.setAniversario(cliente.getAniversario());
			if(cliente.getEstado() != null) cli.setEstado(cliente.getEstado());
			System.out.println("DESPUES del MAP"+cli);
			return dao.save(cli);
		}).orElseGet(()->{
			cliente.setId(id);
			return dao.save(cliente);
		});
	}
}
