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

import py.edu.fpune.model.CobroMensualidad;
import py.edu.fpune.model.Inscripcion;
import py.edu.fpune.model.Usuario;
import py.edu.fpune.repository.ICobroMensualidadDAO;
import py.edu.fpune.repository.IInscripcionDAO;
import py.edu.fpune.repository.IUsuarioDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CobroMensualidadTI {

	@Autowired
	ICobroMensualidadDAO dao;
	@Autowired
	IUsuarioDAO daoUsu;
	@Autowired
	IInscripcionDAO daoIns;
	@Test
	public void getCobrosEntreFechasYEstado() throws ParseException {
		String estado = "pend";
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-30");
		List<CobroMensualidad> lista = dao.findByEstadoIgnoreCaseAndFechaPagoBetween(estado, desde, hasta);
		//for(CobroMensualidad cm : lista) {System.out.println(cm.toString());}
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getCobrosEntreFechasYUsuario() throws ParseException {
		Usuario usu = daoUsu.findById(1).get();
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-30");
		List<CobroMensualidad> lista = dao.findByUsuarioIdAndFechaPagoBetween(usu, desde, hasta);
	//	for(CobroMensualidad cm : lista) {System.out.println(cm.toString());}
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getCobrosEntreFechasYUsuarioHoy() throws ParseException {
		Usuario usu = daoUsu.findById(1).get();
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-10");
		List<CobroMensualidad> lista = dao.findByUsuarioIdAndFechaPagoEquals(usu, desde);
		//for(CobroMensualidad cm : lista) {System.out.println(cm.toString());}
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getCobrosHoyYEstado() throws ParseException {
		String estado = "PAGa";
		Date hoy = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-11");
		List<CobroMensualidad> lista = dao.findByFechaPagoEqualsAndEstadoEqualsIgnoreCase(hoy, estado);
	//	for(CobroMensualidad cm : lista) {System.out.println(cm.toString());}
		assertTrue(lista.size() > 0);
	}
	@Test
	public void getCobrosPorInscripcion() throws ParseException {
		Inscripcion ins = daoIns.findById(1).get();
		CobroMensualidad cobro = dao.findFirstByInscripcionId(ins).get(0);
		System.out.println("###"+cobro.toString());
		assertTrue(cobro.getId().equals(1));
	}
}
