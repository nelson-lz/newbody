package py.edu.fpune.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente clienteId;
	private String tipoInscripcion;
	@Column(name="fecha_inscripcion")
	private Date fechaInscripcion;
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	private Double montoMensualidad;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioId;
	private Date fechaModificacion;
	private String estado;
	@ManyToOne
	@JoinColumn(name = "moneda_id")
	private Moneda monedaId;

	public Inscripcion() {
	}

	public Inscripcion(Cliente clienteId, String tipoInscripcion, Date inicio, Date fin, Double montoMensualidad,
			Usuario usuarioId, Date fechaModificacion, String estado, Moneda monedaId) {
		this.clienteId = clienteId;
		this.tipoInscripcion = tipoInscripcion;
		this.fechaInscripcion = inicio;
		this.fechaVencimiento = fin;
		this.montoMensualidad = montoMensualidad;
		this.usuarioId = usuarioId;
		this.fechaModificacion = fechaModificacion;
		this.estado = estado;
		this.monedaId = monedaId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getMontoMensualidad() {
		return montoMensualidad;
	}

	public void setMontoMensualidad(Double montoMensualidad) {
		this.montoMensualidad = montoMensualidad;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Moneda getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(Moneda monedaId) {
		this.monedaId = monedaId;
	}

	@Override
	public String toString() {
		return "Inscripcion [id=" + id + ", clienteId=" + clienteId.getId() + ", tipoInscripcion=" + tipoInscripcion
				+ ", fechaInscripcion=" + fechaInscripcion + ", fechaVencimiento=" + fechaVencimiento
				+ ", montoMensualidad=" + montoMensualidad + ", usuarioId=" + usuarioId.getNombre() + ", fechaModificacion="
				+ fechaModificacion + ", estado=" + estado + ", monedaId=" + monedaId.getDescripcion() + "]";
	}

}
