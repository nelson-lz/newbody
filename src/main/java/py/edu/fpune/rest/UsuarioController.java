package py.edu.fpune.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.fpune.model.Funcionario;
import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.IFuncionarioDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(UsuarioController.USER)
public class UsuarioController {

	public static final String USER = "/api/v0/usuario";
	public static final String ALL = "/all";
	public static final String ID = "/{id}";
	public static final String ESTADO = "/estado/{estado}";
	public static final String FUNCIONARIO = "/funcionario";
	public static final String ROLE = "/tipoAcceso/{rol}";
	public static final String NAME = "/nombre/{nombre}";
	public static final String STATE = "/state";
	@Autowired
	private IUsuarioDAO dao;
	@Autowired
	private IFuncionarioDAO funDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	
	@GetMapping(value = ALL)
	List<Usuario> getAllUser(){
		return dao.findAll();
	}
	@GetMapping(value = ID)
	Usuario getUsuarioPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = NAME)
	List<Usuario> getUsuarioPorNombre(@PathVariable String nombre){
		return dao.findFirst5ByNombreContainingIgnoreCase(nombre);
	}
	@GetMapping(value = FUNCIONARIO + ID)
	Usuario getUsuarioPorIdFuncionario(@PathVariable Integer id) {
		Funcionario fun = funDao.findById(id).get();
		return dao.findByFuncionarioId(fun);
	}
	@GetMapping(value = ESTADO)
	List<Usuario> getUsuariosPorEstado(@PathVariable String estado){
		return dao.findByEstadoIgnoreCase(estado);
	}
	
	@DeleteMapping(value = ID)
	void deteleUsuario(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	@PostMapping
	Usuario insertNewUsuario(@RequestBody Usuario usuario) {
		usuario.setPass(encoder.encode(usuario.getPass()));
		return dao.save(usuario);
	}
	@PutMapping(value = ID)
	Usuario updateUsuarioPorId(@PathVariable Integer id, @RequestBody @Valid Usuario usuario) {
		//System.out.println("Usuario ID:"+id);
		//System.out.println("Usuario::"+usuario);
		Funcionario fun = funDao.findById(usuario.getFuncionario().getId()).get();
		return dao.findById(id).map(usu -> {
			usu.setNombre(usuario.getNombre());
			usu.setPass(usuario.getPass());
			usu.setNivelAcceso(usuario.getNivelAcceso());
			usu.setFuncionario(fun);
			usu.setEstado(usuario.getEstado());
			return dao.save(usu);
		}).orElseGet(()->{
			usuario.setId(id);
			return dao.save(usuario);
		});
	}
	
	
	
}
