package py.edu.fpune.rest;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.MedidaAntropometrica;
import py.edu.fpune.repository.IClienteDAO;
import py.edu.fpune.repository.IMedidaAntropometricaDAO;

@RequestMapping(MedidaAntropometricaController.MEDIDA)
@RestController
@PreAuthorize("authenticated")
public class MedidaAntropometricaController {

	public static final String MEDIDA = "/api/v0/medidas";
	public static final String ID = "/{id}";
	public static final String CLIENTE = "/cliente";
	public static final String BETWEEN = "/entre/{desde}/{hasta}";
	public static final String ESTAFECHA = "/estafecha/{estafecha}";
	public static final String STATE = "/state";
	public static final String XUSER = "/usuario";
	
	@Autowired
	IMedidaAntropometricaDAO dao;
	@Autowired
	IClienteDAO daoCliente;
	
	@GetMapping(value = STATE)
	String state() {
		return "{\"state\":\"ok\"}";
	}
	@GetMapping(value = ID)
	MedidaAntropometrica getMedidaPorId(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	@GetMapping(value = CLIENTE + ID)
	List<MedidaAntropometrica> getAllMedidasPorCliente(@PathVariable Integer id){
		Cliente cli = daoCliente.findById(id).get(); 
		return dao.findByClienteIdOrderByFechaAsc(cli);
	}
	@GetMapping(value = CLIENTE + ID + BETWEEN)
	List<MedidaAntropometrica> getMedidasEntreFechasPorCliente(@PathVariable Integer id, 
															   @PathVariable String desde, 
															   @PathVariable String hasta) throws ParseException{
		//TODO ver como sacar el Parse Exception de aqui
		Cliente cli = daoCliente.findById(id).get();
		return dao.findByClienteIdAndFechaAfterAndFechaBefore(cli, 
															  new SimpleDateFormat("yyyy-MM-dd").parse(desde),
															  new SimpleDateFormat("yyyy-MM-dd").parse(hasta));
	}
	@GetMapping(value = CLIENTE + ID + ESTAFECHA)
	List<MedidaAntropometrica> getMedidasPorClienteYDesdeFecha(@PathVariable Integer id, @PathVariable Date estafecha){
		//TODO ver cual es mejor new SimpleDateFormat o import java.sql.Date;
		Cliente cli = daoCliente.findById(id).get();
		return dao.findByClienteIdAndFechaAfter(cli, estafecha);
	}
	
	@PreAuthorize("hasRole('MANAGER') OR hasRole('ADMIN')")
	@DeleteMapping(value = ID)
	void deleteMedidaPorID(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping
	MedidaAntropometrica newMedida(@RequestBody MedidaAntropometrica medidaAntrompometrica) {
		return dao.save(medidaAntrompometrica);
	}
	
	@PutMapping(value = ID)
	MedidaAntropometrica updateMedidaPorId(@PathVariable Integer id, @RequestBody MedidaAntropometrica medidaAntropometrica) {
		return dao.findById(id).map(medida -> {
			if(medidaAntropometrica.getClienteId() != null) medida.setClienteId(medidaAntropometrica.getClienteId());
			if(medidaAntropometrica.getAltura() != 0) medida.setAltura(medidaAntropometrica.getAltura());
			if(medidaAntropometrica.getCintura() != 0) medida.setCintura(medidaAntropometrica.getCintura());
			if(medidaAntropometrica.getPecho() != 0) medida.setPecho(medidaAntropometrica.getPecho());
			if(medidaAntropometrica.getGluteo() != 0) medida.setGluteo(medidaAntropometrica.getGluteo());
			if(medidaAntropometrica.getPeso() != 0) medida.setPeso(medidaAntropometrica.getPeso());
			if(medidaAntropometrica.getGrasa() != 0) medida.setGrasa(medidaAntropometrica.getGrasa());
			if(medidaAntropometrica.getHueso() != 0) medida.setHueso(medidaAntropometrica.getHueso());
			if(medidaAntropometrica.getMusculo() != 0) medida.setMusculo(medidaAntropometrica.getMusculo());
			if(medidaAntropometrica.getFecha() != null) medida.setFecha(medidaAntropometrica.getFecha());
			if(medidaAntropometrica.getUsuarioId() != null) medida.setUsuarioId(medidaAntropometrica.getUsuarioId());
			if(medidaAntropometrica.getFoto1() != null) medida.setFoto1(medidaAntropometrica.getFoto1());
			if(medidaAntropometrica.getFoto2() != null) medida.setFoto2(medidaAntropometrica.getFoto2());
			if(medidaAntropometrica.getFoto3() != null) medida.setFoto3(medidaAntropometrica.getFoto3());
			return dao.save(medida);
		}).orElseGet(()->{
			medidaAntropometrica.setId(id);
			return dao.save(medidaAntropometrica);
		});
	}
	
}
