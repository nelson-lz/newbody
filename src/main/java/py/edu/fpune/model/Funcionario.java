package py.edu.fpune.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombres;

	private String apellidos;

	private String cedula;

	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	private Integer salario;

	private String direccion;

	private String telef1;
	private String telef2;

	@Column(name="cajero_tesorero")
	private String cajeroTesorero;
	private String estado;

	public Funcionario() {
	}

	public Funcionario(String nombres, String apellidos, String cedula, Date fechaNacimiento, Integer salario,
			String direccion, String telef1, String telef2, String cajeroTesorero, String estado) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.salario = salario;
		this.direccion = direccion;
		this.telef1 = telef1;
		this.telef2 = telef2;
		this.cajeroTesorero = cajeroTesorero;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getSalario() {
		return salario;
	}

	public void setSalario(Integer salario) {
		this.salario = salario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelef1() {
		return telef1;
	}

	public void setTelef1(String telef1) {
		this.telef1 = telef1;
	}

	public String getTelef2() {
		return telef2;
	}

	public void setTelef2(String telef2) {
		this.telef2 = telef2;
	}

	public String getCajeroTesorero() {
		return cajeroTesorero;
	}

	public void setCajeroTesorero(String cajeroTesorero) {
		this.cajeroTesorero = cajeroTesorero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", cedula=" + cedula
				+ ", fechaNacimiento=" + fechaNacimiento + ", salario=" + salario + ", direccion=" + direccion
				+ ", telef1=" + telef1 + ", telef2=" + telef2 + ", cajeroTesorero=" + cajeroTesorero + ", estado="
				+ estado + "]";
	}

}
