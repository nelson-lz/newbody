package py.edu.fpune.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String pass;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionarioId;

	@Column(name = "nivel_acceso")
	private String nivelAcceso;

	private String estado;

	public Usuario() {
	}

	public Usuario(String nombre, String pass, Funcionario funcionario, String nivelAcceso, String estado) {
		this.nombre = nombre;
		this.pass = pass;
		this.funcionarioId = funcionario;
		this.nivelAcceso = nivelAcceso;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Funcionario getFuncionario() {
		return funcionarioId;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionarioId = funcionario;
	}

	public String getNivelAcceso() {
		return nivelAcceso;
	}

	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", funcionarioNombre=" + funcionarioId.getNombres()
				+ ", nivelAcceso=" + nivelAcceso + ", estado=" + estado + "]";
	}
}
