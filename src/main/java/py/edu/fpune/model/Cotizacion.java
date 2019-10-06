package py.edu.fpune.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cotizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date fecha;
	@ManyToOne
	@JoinColumn(name = "moneda_id")
	private Moneda monedaId;
	private Double cotizacion;
	private String estado;
	
	public Cotizacion() {
	}

	public Cotizacion(Date fecha, Moneda monedaId, Double cotizacion, String estado) {
		this.fecha = fecha;
		this.monedaId = monedaId;
		this.cotizacion = cotizacion;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Moneda getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(Moneda monedaId) {
		this.monedaId = monedaId;
	}

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cotizacion [id=" + id + ", fecha=" +  new SimpleDateFormat("yyyy-MM-dd").format(fecha) + ", monedaId=" + monedaId.getDescripcion() + ", cotizacion=" + cotizacion
				+ ", estado=" + estado + "]";
	}
}
