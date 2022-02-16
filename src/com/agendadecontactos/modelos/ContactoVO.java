package com.agendadecontactos.modelos;

public class ContactoVO implements Comparable<ContactoVO>{
	
	private Integer idContacto;
	private String nombreContacto;
	private String apellidoContacto;
	private Integer numeroContacto;
	private String emailContacto;
	private String infoContacto;
	
	public ContactoVO(String nombreContacto, String apellidoContacto, Integer numeroContacto, String emailContacto) {
		this.nombreContacto = nombreContacto;
		this.apellidoContacto = apellidoContacto;
		this.numeroContacto = numeroContacto;
		this.emailContacto = emailContacto;
	}

	//Constructor que usamos para agregar un contacto nuevo
	public ContactoVO(String nombreContacto, String apellidoContacto, Integer numeroContacto, String emailContacto,
			String infoContacto) {
		this.nombreContacto = nombreContacto;
		this.apellidoContacto = apellidoContacto;
		this.numeroContacto = numeroContacto;
		this.emailContacto = emailContacto;
		this.infoContacto = infoContacto;
	}
	
	public ContactoVO(String nombreContacto, String apellidoContacto, Integer numeroContacto, String emailContacto,
			String infoContacto, Integer idContacto) {
		this.nombreContacto = nombreContacto;
		this.apellidoContacto = apellidoContacto;
		this.numeroContacto = numeroContacto;
		this.emailContacto = emailContacto;
		this.infoContacto = infoContacto;
		this.idContacto = idContacto;
	}

	//Constructor vacio
	public ContactoVO() {
	}

	
	//GETTERS Y SETTERS
	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getApellidoContacto() {
		return apellidoContacto;
	}

	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}

	public int getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(Integer numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getInfoContacto() {
		return infoContacto;
	}

	public void setInfoContacto(String infoContacto) {
		this.infoContacto = infoContacto;
	}

	@Override
	public String toString() {
		return nombreContacto + " " + apellidoContacto
				+ " " + numeroContacto + " " + emailContacto;
	}


	@Override
	public int compareTo(ContactoVO contacto) {
		return nombreContacto.compareTo(contacto.getNombreContacto());
	}

}
