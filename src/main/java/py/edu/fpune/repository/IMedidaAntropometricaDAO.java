package py.edu.fpune.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cliente;
import py.edu.fpune.model.MedidaAntropometrica;

public interface IMedidaAntropometricaDAO extends JpaRepository<MedidaAntropometrica, Integer>{

	List<MedidaAntropometrica> findByClienteIdOrderByFechaAsc(Cliente clienteId);
	
	MedidaAntropometrica findFirstByClienteIdOrderByFechaAsc(Cliente cliente);
	List<MedidaAntropometrica> findByClienteIdAndFechaAfterAndFechaBefore(Cliente cliente, Date desde, Date hasta);
	List<MedidaAntropometrica> findByClienteIdAndFechaAfter(Cliente cliente, Date desde);
}
