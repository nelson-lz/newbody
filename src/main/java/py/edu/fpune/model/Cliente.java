package py.edu.fpune.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(name="Cliente.findByAniversariosDelMes", 
				  query = "SELECT id, ruc, nombres, apellidos, telefono1, telefono2, direccion, \r\n" + 
				  		  "aniversario, estado FROM cliente WHERE extract(month from aniversario) = ? AND estado = UPPER(?)",
				  resultClass = Cliente.class)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombres;
	private String apellidos;
	private String ruc;
	private String telefono1;
	private String telefono2;
	private String direccion;
	private Date aniversario;
	private String estado;

	public Cliente(String nombres, String apellidos, String ruc, String telefono1, String telefono2, String direccion,
			Date aniversario, String estado) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.ruc = ruc;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.direccion = direccion;
		this.aniversario = aniversario;
		this.estado = estado;
	}

	public Cliente() {

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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ruc=" + ruc
				+ ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", direccion=" + direccion
				+ ", aniversario=" + aniversario + ", estado=" + estado + "]";
	}
	
}
