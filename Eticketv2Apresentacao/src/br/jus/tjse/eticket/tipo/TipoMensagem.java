package br.jus.tjse.eticket.tipo;

public enum TipoMensagem {
	NEHUMA,
	SUCESSO,
	ERRO,
	ALERTA,
	INFORMACAO;
	
	public String getClasseAlerta() {
		String classe = "hide"; 
		switch (this) {
		case SUCESSO:
			classe = "alert-success";
			break;
		case ERRO:
			classe = "alert-danger";
			break;
		case ALERTA:
			classe = "alert-warning";
			break;
		case INFORMACAO:
			classe = "alert-info";
			break;
		default:
			classe = "hide";
			break;
		}
		return classe;
	}
	
	public String getTituloAlerta() {
		String classe = ""; 
		switch (this) {
		case SUCESSO:
			classe = "Sucesso!";
			break;
		case ERRO:
			classe = "Erro!";
			break;
		case ALERTA:
			classe = "Atenção!";
			break;
		default:
			break;
		}
		return classe;
	}

}
