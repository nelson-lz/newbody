package py.edu.fpune;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.repository.IClienteDAO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTI {
	
	@Autowired
	IClienteDAO dao;
	
//	@Test
//	public void getAniversariosDelMes() {
//		List<Cliente> clientes = dao.findByAniversariosDelMes(1, "ina");
//		for(Cliente cli: clientes) {
//			System.out.println(cli.getApellidos());
//		}
//		assertTrue(clientes.size() > 0);
//	}
//	
	/*
	 * @Test public void getPorNombreApellido() { String nom = "e"; String ape =
	 * "sil"; List<Cliente> clientes =
	 * dao.findByNombresContainingOrApellidosContaining(nom, nom); List<Cliente>
	 * clientes3 =
	 * dao.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(nom,
	 * nom); List<Cliente> clientes2 = dao.findByNombresContainingIgnoreCase(nom);
	 * System.out.println("Get por Nombre y Apellido"); for(Cliente cli: clientes3)
	 * { System.out.println(cli.getNombres()); } assertTrue(clientes3.size() > 0); }
	 * 
	 * @Test public void getPorNombre() { String nom = "Ne"; List<Cliente> clientes
	 * = dao.findByNombresContainingIgnoreCase(nom);
	 * System.out.println("Get por Nombre"); for(Cliente cli: clientes) {
	 * System.out.println(cli.getNombres()); } assertTrue(clientes.size() > 0); }
	 * 
	 * @Test public void getPorApellido() { String ape = "siL"; List<Cliente>
	 * clientes = dao.findByApellidosContainingIgnoreCase(ape);
	 * System.out.println("Get por Apellido"); for(Cliente cli: clientes) {
	 * System.out.println(cli.getNombres()); } assertTrue(clientes.size() > 0); }
	 */
	@Test
	public void getPorRuc() {
		String ruc = "4";
		//Cliente cliente = dao.findByRuc(ruc);
		List<Cliente> clientes = dao.findByRucContaining(ruc);
		System.out.println("Get por RUC");
		//System.out.println(cliente.getNombres());
		for(Cliente cli: clientes) {
			System.out.println(cli.getApellidos());
		}
		//assertTrue(clientes.getNombres().equals("Nelson"));
		assertTrue(clientes.size() > 0);
	}
}
