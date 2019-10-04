package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Cotizacion;
import py.edu.fpune.model.Moneda;

public interface ICotizacionDAO extends JpaRepository<Cotizacion, Integer>{
	
	List<Cotizacion> findByMonedaIdOrderByFechaAsc(Moneda moneda);
	
	Cotizacion findFirstByMonedaIdOrderByFechaAsc(Moneda moneda);
	Cotizacion findFirstByMonedaIdOrderByFechaDesc(Moneda moneda);
	
	List<Cotizacion> findByEstadoIgnoreCaseOrderByFechaAsc(String estado);
	List<Cotizacion> findByEstadoIgnoreCaseOrderByFechaDesc(String estado);
}
