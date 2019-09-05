package py.edu.fpune;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.IUsuarioDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioTI {
	
	@Autowired
	private IUsuarioDAO dao;
	
	@Test
	public void gerUsuarioPorNombre() {
		Usuario us = dao.findByNombre("nelson-lz");
		
		assertNotNull(us);
	}
	
	@Test
	public void gerUsuariosPorNivelAcceso() {
		List<Usuario> usuarios = dao.findByNivelAccesoIgnoreCase("adm");
		System.out.println("Cantidad de usuarios x Nivel="+usuarios.size());
		assertNotNull(usuarios.size());
	}
	@Test
	public void getUsuarioPorEstado() {
		List<Usuario> usuarios = dao.findByEstadoIgnoreCase("ACt");
		System.out.println("Catidad de usuarios x Estado=" + usuarios.size());
		assertNotNull(usuarios);
	}
}
