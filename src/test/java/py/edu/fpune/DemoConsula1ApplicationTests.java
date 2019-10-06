package py.edu.fpune;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import py.edu.fpune.model.Funcionario;
import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.IFuncionarioDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoConsula1ApplicationTests {

	@Autowired
	private IUsuarioDAO dao;
	@Autowired
	private IFuncionarioDAO daoFun;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Test
	public void modificarUsuario() {
		Usuario usu = new Usuario();
		usu.setId(null);
		usu.setNombre("angeles");
		usu.setPass(encoder.encode("123"));
		usu.setNivelAcceso("ADM");
		usu.setEstado("ACT");
		Funcionario fun = daoFun.findById(7).get();
		usu.setFuncionario(fun);
		
		Usuario retorno = dao.save(usu);
		
		assertTrue(retorno.getNombre().equalsIgnoreCase(usu.getNombre()));
	}

}
