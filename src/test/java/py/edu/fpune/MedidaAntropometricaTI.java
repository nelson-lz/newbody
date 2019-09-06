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

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.MedidaAntropometrica;
import py.edu.fpune.repository.IClienteDAO;
import py.edu.fpune.repository.IMedidaAntropometricaDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MedidaAntropometricaTI {
	@Autowired
	IMedidaAntropometricaDAO dao;
	@Autowired
	IClienteDAO cliDao;

	@Test
	public void getUltimaCotizacion() throws ParseException {
		Cliente cli = cliDao.findById(1).get();
		Date desde = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
		Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
		List<MedidaAntropometrica> medidas = dao.findByClienteIdAndFechaAfterAndFechaBefore(cli, desde, hasta);
		List<MedidaAntropometrica> medidas2 = dao.findByClienteIdAndFechaAfter(cli, hasta);
		MedidaAntropometrica medida = dao.findFirstByClienteIdOrderByFechaAsc(cli);

		for (MedidaAntropometrica med : medidas2) {
			System.out.println(med.toString());
		}

		System.out.println("Cantidad de Medidas=" + medidas2.size());
		assertTrue(medidas2.size() == 0);
		//assertTrue(medida.getClienteId().getId().equals(1));
	}
}
