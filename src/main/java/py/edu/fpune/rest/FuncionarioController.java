package py.edu.fpune.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.fpune.model.Funcionario;
import py.edu.fpune.repository.IFuncionarioDAO;

@PreAuthorize("authenticated")//es la seguridad mas básica para todos los recursos
@RestController
@RequestMapping(FuncionarioController.FUNCTIONARY)
public class FuncionarioController {

	public static final String FUNCTIONARY = "/funcionario";
	public static final String ALL = "/all";
	public static final String USER = "/user";
	public static final String ADMIN = "/admin";
	@Autowired
	private IFuncionarioDAO dao;
	
	@GetMapping(value = ALL)
	List<Funcionario> all(){
		return dao.findAll();
	}
	@GetMapping(value = "/{id}")
	Optional<Funcionario> getFuncioarioId(@PathVariable Integer id) {
		return dao.findById(id);
	}
	@GetMapping(value = "/nombre/{nombre}")
	List<Funcionario> getPorNombre(@PathVariable String nombre){
		System.out.println(nombre);
		return dao.findFirst4ByNombresContainingIgnoreCase(nombre);
	}
	@GetMapping(value = "/apellido/{apellido}")
	List<Funcionario> getPorApellido(@PathVariable String apellido){
		return dao.findFirst4ByApellidosContainingIgnoreCase(apellido);
	}
	
	@PreAuthorize("hasRole('USER') OR hasRole('MANAGER')")
	@GetMapping(value = "/aniver/{numeroDelMes}")
	List<Funcionario> getAniversariosEntreFechas(@PathVariable Integer numeroDelMes){
		return dao.findByAniversariosDelMes(numeroDelMes);
	}
	
}
