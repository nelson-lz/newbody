package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cotizacion;
import py.edu.fpune.model.Moneda;

public interface ICotizacionDAO extends JpaRepository<Cotizacion, Integer>{
	
	List<Cotizacion> findByMonedaId(Moneda monedaId);
	
	List<Cotizacion> findByEstado(String estado);
}
