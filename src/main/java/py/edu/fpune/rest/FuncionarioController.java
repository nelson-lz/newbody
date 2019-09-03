package py.edu.fpune.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import py.edu.fpune.model.Funcionario;
import py.edu.fpune.repository.IFuncionarioDAO;

@RestController
public class FuncionarioController {

	@Autowired
	private IFuncionarioDAO dao;
	
	@GetMapping("/funcionario")
	List<Funcionario> all(){
		return dao.findAll();
	}
	@GetMapping("/funcionario/{id}")
	Optional<Funcionario> getFuncioarioId(@PathVariable Integer id) {
		return dao.findById(id);
	}
	@GetMapping("/funcionario/nombre/{nombre}")
	List<Funcionario> getPorNombre(@PathVariable String nombre){
		System.out.println(nombre);
		return dao.findFirst4ByNombresContainingIgnoreCase(nombre);
	}
	@GetMapping("/funcionario/apellido/{apellido}")
	List<Funcionario> getPorApellido(@PathVariable String apellido){
		return dao.findFirst4ByApellidosContainingIgnoreCase(apellido);
	}
	@GetMapping("/funcionario/aniver/{desde}/{hasta}")
	List<Funcionario> getAniversariosEntreFechas(@PathVariable Date desde,Date hasta){
		System.out.println(desde + " " +hasta);
		//TODO hay que encotrar la anotacion de conversion de string a date
		return dao.findByFechaNacimientoBetween(desde, hasta);
	}
}
