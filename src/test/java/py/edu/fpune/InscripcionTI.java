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

import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.IInscripcionDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InscripcionTI {

	@Autowired
	IInscripcionDAO dao;
	@Autowired
	IUsuarioDAO daoUsu;
	
	@Test
	public void getInscriptosEntreFechas() throws ParseException {
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-30");
		List<Inscripcion> lista = dao.findByFechaInscripcionBetween(desde, hasta);
		/*
		 * for(Inscripcion in: lista) { System.out.println(in.toString()); }
		 */
		assertTrue(lista.size() > 0);
	}
	
	@Test
	public void getInscriptosEntreFechasPendiente() throws ParseException {
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-30");
		List<Inscripcion> lista = dao.findByFechaInscripcionBetweenAndEstadoLikeIgnoreCase(desde, hasta, "PENd");
		/*
		 * for(Inscripcion in: lista) { System.out.println(in.toString()); }
		 */
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getInscriptosPorTipo() throws ParseException {
		List<Inscripcion> lista = dao.findByTipoInscripcionIgnoreCase("Mensualida");
		
		//for(Inscripcion in: lista) { System.out.println(in.toString()); }
		 
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getInscriptosPorUsuarioAndFecha() throws ParseException {
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-30");
		Usuario usuario = daoUsu.findById(1).get();
		System.out.println(usuario.toString());
		List<Inscripcion> lista = dao.findByUsuarioIdAndFechaInscripcionBetween(usuario, desde, hasta);
		
		for(Inscripcion in: lista) { System.out.println("POR USUARIO::::###"+in.toString()); }
		 
		assertTrue(lista.size() > 0);
	}
}
