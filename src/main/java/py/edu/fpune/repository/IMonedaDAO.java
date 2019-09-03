package py.edu.fpune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.fpune.model.Moneda;

public interface IMonedaDAO extends JpaRepository<Moneda, Integer>{
	
	List<Moneda> findByDescripcion(String descripcion);
}
