package br.com.calcard.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.calcard.dto.ClienteDTO;
import br.com.calcard.dto.PropostaResultDTO;
import br.com.calcard.service.AnaliseService;


@Named
@ViewScoped
public class AnaliseController implements Serializable  {
	
	private static final long serialVersionUID = 8061224459722221230L;
	
	@Autowired
	private AnaliseService analise;
	
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
			PropostaResultDTO analiseResult = analise.analisar(cliente);
			currentInstance.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado: "+analiseResult.getResultado() + " - "+analiseResult.getLimite(), ""));
			cliente = new ClienteDTO();
		} catch (Exception e) {
			currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao processar - tente novamente"));
		}
	}

	public void pesquisar() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			PropostaResultDTO analiseResult = analise.consultarProposta(cliente);
			currentInstance.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da pesquisa: "+analiseResult.getResultado() + " - "+analiseResult.getLimite(), ""));
			cliente = new ClienteDTO();
		} catch (Exception e) {
			currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao processar - tente novamente"));
		}
	}
	
}
