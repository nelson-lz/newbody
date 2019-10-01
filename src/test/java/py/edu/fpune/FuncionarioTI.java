package py.edu.fpune;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import py.edu.fpune.model.Funcionario;
import py.edu.fpune.repository.IFuncionarioDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuncionarioTI {

	@Autowired
	private IFuncionarioDAO daoFun;


	/*
	 * @Test public void getAniversariosEntre() throws ParseException { Date desde =
	 * new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-01"); Date hasta = new
	 * SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
	 * System.out.println("desde=" + desde + ", hasta=" + hasta); List<Funcionario>
	 * funList = daoFun.findByAniversariosDelMes(1); for (Funcionario fun : funList)
	 * { System.out.println(fun.getNombres()); }
	 * System.out.println("Cantidad de funcionarios obtenidos=" + funList.size());
	 * assertTrue(funList.size()==1); }
	 */
	@Test
	public void getPorNombreOApellido() {
		String nomApe = "S";
		List<Funcionario> lista = daoFun.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(nomApe, nomApe);
		System.out.println("TAMAÑO DE LA LISTA: "+lista.size());
		for(Funcionario fun : lista) {
			System.out.println("FUNCIONARIO: "+ fun);
		}
		assertTrue(lista.size() > 0);
	}

}
