package py.edu.fpune.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="medida_antropometrica")
public class MedidaAtropometrica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente clienteId;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario UsuarioId;
	private float altura;
	private float cintura;
	private float pecho;
	private float gluteo;
	private float peso;
	private float grasa;
	private float hueso;
	private float musculo;
	private Date fecha;
	private byte[] foto1;
	private byte[] foto2;
	private byte[] foto3;
	
	public MedidaAtropometrica() {
	}

	public MedidaAtropometrica(Cliente clienteId, Usuario usuarioId, float altura, float cintura,
			float pecho, float gluteo, float peso, float grasa, float hueso, float musculo, Date fecha, byte[] foto1,
			byte[] foto2, byte[] foto3) {
		this.clienteId = clienteId;
		UsuarioId = usuarioId;
		this.altura = altura;
		this.cintura = cintura;
		this.pecho = pecho;
		this.gluteo = gluteo;
		this.peso = peso;
		this.grasa = grasa;
		this.hueso = hueso;
		this.musculo = musculo;
		this.fecha = fecha;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.foto3 = foto3;
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

	public Usuario getUsuarioId() {
		return UsuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		UsuarioId = usuarioId;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getCintura() {
		return cintura;
	}

	public void setCintura(float cintura) {
		this.cintura = cintura;
	}

	public float getPecho() {
		return pecho;
	}

	public void setPecho(float pecho) {
		this.pecho = pecho;
	}

	public float getGluteo() {
		return gluteo;
	}

	public void setGluteo(float gluteo) {
		this.gluteo = gluteo;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getGrasa() {
		return grasa;
	}

	public void setGrasa(float grasa) {
		this.grasa = grasa;
	}

	public float getHueso() {
		return hueso;
	}

	public void setHueso(float hueso) {
		this.hueso = hueso;
	}

	public float getMusculo() {
		return musculo;
	}

	public void setMusculo(float musculo) {
		this.musculo = musculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public byte[] getFoto1() {
		return foto1;
	}

	public void setFoto1(byte[] foto1) {
		this.foto1 = foto1;
	}

	public byte[] getFoto2() {
		return foto2;
	}

	public void setFoto2(byte[] foto2) {
		this.foto2 = foto2;
	}

	public byte[] getFoto3() {
		return foto3;
	}

	public void setFoto3(byte[] foto3) {
		this.foto3 = foto3;
	}

	@Override
	public String toString() {
		return "MedidaAtropometrica [id=" + id + ", clienteId=" + clienteId + ", UsuarioId=" + UsuarioId + ", altura="
				+ altura + ", cintura=" + cintura + ", pecho=" + pecho + ", gluteo=" + gluteo + ", peso=" + peso
				+ ", grasa=" + grasa + ", hueso=" + hueso + ", musculo=" + musculo + ", fecha=" + fecha + ", foto1="
				+ Arrays.toString(foto1) + ", foto2=" + Arrays.toString(foto2) + ", foto3=" + Arrays.toString(foto3)
				+ "]";
	}
	
}
