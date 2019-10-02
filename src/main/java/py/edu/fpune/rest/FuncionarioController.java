package py.edu.fpune.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import py.edu.fpune.model.Funcionario;
import py.edu.fpune.repository.IFuncionarioDAO;

@PreAuthorize("authenticated")//es la seguridad mas básica para todos los recursos
@RestController
@RequestMapping(FuncionarioController.FUNCTIONARY)
public class FuncionarioController {

	public static final String FUNCTIONARY = "/api/v0/funcionario";
	public static final String ALL = "/all";
	public static final String USER = "/user";
	public static final String ADMIN = "/admin";
	public static final String NAME = "/nombre";
	public static final String LASTNAME = "/apellido";
	public static final String NOMAPE = "/nombreapellido";
	public static final String BIRTDAY = "/aniver";
	public static final String ID = "/{id}";
	public static final String CIN = "/cin/{cin}";
	public static final String STATE = "/estado";
	
	@Autowired
	private IFuncionarioDAO dao;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	
	@GetMapping(value = ALL)
	List<Funcionario> all(){
		return dao.findAll();
	}
	@GetMapping(value = ID)
	Optional<Funcionario> getFuncioarioId(@PathVariable Integer id) {
		return dao.findById(id);
	}
	@GetMapping(value = CIN)
	List<Funcionario> getPorCedula(@PathVariable String cin){
		return dao.findByCedulaContainingIgnoreCase(cin);
	}
	@GetMapping(value = NAME +"/{nombre}")
	List<Funcionario> getPorNombre(@PathVariable String nombre){
		System.out.println(nombre);
		return dao.findFirst4ByNombresContainingIgnoreCase(nombre);
	}
	@GetMapping(value = LASTNAME + "/{apellido}")
	List<Funcionario> getPorApellido(@PathVariable String apellido){
		return dao.findFirst4ByApellidosContainingIgnoreCase(apellido);
	}
	@GetMapping(value= NOMAPE + "/{nomApe}")
	List<Funcionario> getPorNombreOApellidoIgnoreCase(@PathVariable String nomApe){
		return dao.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(nomApe, nomApe);
	}
	
	@PreAuthorize("hasRole('USER') OR hasRole('MANAGER') OR hasRole('ADMIN')")
	@GetMapping(value = BIRTDAY + "/{numeroDelMes}")
	List<Funcionario> getAniversariosEntreFechas(@PathVariable Integer numeroDelMes){
		return dao.findByAniversariosDelMes(numeroDelMes);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = ID)
	void deleteFuncionarioById(@PathVariable Integer id) {
		Funcionario fun = dao.findById(id).get();
		dao.delete(fun);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@PostMapping
	Funcionario newFuncionario(@RequestBody Funcionario funcionario) {
		return dao.save(funcionario);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	@PutMapping(value = ID)
	Funcionario updateFuncionario(@PathVariable Integer id, @RequestBody @Valid Funcionario funcionario) {
		
		return dao.findById(id).map(fun -> {
			if(funcionario.getCedula() != null) fun.setCedula(funcionario.getCedula());
			if(funcionario.getNombres() != null) fun.setNombres(funcionario.getNombres());
			if(funcionario.getApellidos() != null) fun.setApellidos(funcionario.getApellidos());
			if(funcionario.getFechaNacimiento() != null) fun.setFechaNacimiento(funcionario.getFechaNacimiento());
			if(funcionario.getTelef1() != null) fun.setTelef1(funcionario.getTelef1());
			if(funcionario.getTelef2() != null) fun.setTelef2(funcionario.getTelef2());
			if(funcionario.getDireccion() != null) fun.setDireccion(funcionario.getDireccion());
			if(funcionario.getEstado() != null) fun.setEstado(funcionario.getEstado());
			if(funcionario.getCajeroTesorero() != null) fun.setCajeroTesorero(funcionario.getCajeroTesorero());
			if(funcionario.getSalario() != null) fun.setSalario(funcionario.getSalario());
			return dao.save(fun);
		}).orElseGet(()-> {
			funcionario.setId(id);
			return dao.save(funcionario);
		});
	}
	
}
