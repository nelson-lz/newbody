package py.edu.fpune.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CobroMensualidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "inscripcion_id")
	private Inscripcion inscripcionId;
	private Date fechaPago;
	@ManyToOne
	@JoinColumn(name = "moneda_id")
	private Moneda monedaId;
	private Double montoPagado;
	private Double descuento;
	private Double total;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioId;
	private String estado;

	public CobroMensualidad() {
	}

	public CobroMensualidad(Inscripcion inscripcionId, Date fechaPago, Moneda monedaId, Double montoPagado,
			Double descuento, Double total, Usuario usuarioId, String estado) {
		super();
		this.inscripcionId = inscripcionId;
		this.fechaPago = fechaPago;
		this.monedaId = monedaId;
		this.montoPagado = montoPagado;
		this.descuento = descuento;
		this.total = total;
		this.usuarioId = usuarioId;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inscripcion getInscripcionId() {
		return inscripcionId;
	}

	public void setInscripcionId(Inscripcion inscripcionId) {
		this.inscripcionId = inscripcionId;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Moneda getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(Moneda monedaId) {
		this.monedaId = monedaId;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CobroMensualidad [id=" + id + ", inscripcionId=" + inscripcionId + ", fechaPago=" + fechaPago
				+ ", monedaId=" + monedaId + ", montoPagado=" + montoPagado + ", descuento=" + descuento + ", total="
				+ total + ", usuarioId=" + usuarioId + ", estado=" + estado + "]";
	}
}
