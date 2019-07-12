package br.com.calcard.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.calcard.dto.ClienteDTO;


@Named
@ViewScoped
public class AnaliseController implements Serializable  {
	
	private static final long serialVersionUID = 8061224459722221230L;
	
	private ClienteDTO cliente;
	
	public ClienteDTO getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	@PostConstruct
	public void criarCliente() {
		cliente = new ClienteDTO();
	}
	
	public void analisar() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
