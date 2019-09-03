package py.edu.fpune;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

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
public class FuncionarioTI {

		@Autowired
		private IUsuarioDAO dao;
		@Autowired
		private IFuncionarioDAO daoFun;
		@Autowired
		private BCryptPasswordEncoder encoder;
	/*
	 * @Test public void modificarUsuario() { Usuario usu = new Usuario();
	 * usu.setId(1); usu.setNombre("nelson-lz"); usu.setPass(encoder.encode("123"));
	 * usu.setNivelAcceso("ADM"); usu.setEstado("ACT"); Funcionario fun =
	 * daoFun.findById(1).get(); usu.setFuncionario(fun);
	 * 
	 * Usuario retorno = dao.save(usu);
	 * 
	 * assertTrue(retorno.getNombre().equalsIgnoreCase(usu.getNombre())); }
	 */
		
		@Test
		public void getAniversariosEntre() {
			List<Funcionario> funcionarios= daoFun.findByFechaNacimientoBetween(new Date(1988, 01, 01), new Date(1990,01,01));
			for(Funcionario fun: funcionarios) {
				System.out.println(fun.getNombres());
			}
			assertNotNull(funcionarios);
		}

	}
