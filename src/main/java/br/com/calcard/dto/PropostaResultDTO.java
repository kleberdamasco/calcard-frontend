package br.com.calcard.dto;

public class PropostaResultDTO {
	
	private String resultado;
	private String limite;
	
	public PropostaResultDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PropostaResultDTO(String resultado, String limite) {
		this.resultado = resultado;
		this.limite = limite;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public String getLimite() {
		return limite;
	}

}
