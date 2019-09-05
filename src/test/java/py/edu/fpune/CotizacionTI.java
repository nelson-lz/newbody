package py.edu.fpune;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import py.edu.fpune.model.Cotizacion;
import py.edu.fpune.model.Moneda;
import py.edu.fpune.repository.ICotizacionDAO;
import py.edu.fpune.repository.IMonedaDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CotizacionTI {

	@Autowired
	ICotizacionDAO dao;
	@Autowired
	IMonedaDAO daoMoneda;
	@Test
	public void getUltimaCotizacion() {
		Moneda moneda = daoMoneda.findById(2).get();
		System.out.println(moneda.toString());
		List<Cotizacion> cotizaciones = dao.findByMonedaIdOrderByFechaAsc(moneda);
		for(Cotizacion co : cotizaciones) {
			System.out.println(co.toString());
		}
		System.out.println("tamaño de cotizaciones="+cotizaciones.size());
		assertNotNull(cotizaciones.size());
	}
}
