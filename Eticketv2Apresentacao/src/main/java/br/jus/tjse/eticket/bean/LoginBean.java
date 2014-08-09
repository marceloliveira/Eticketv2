package br.jus.tjse.eticket.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {
	
	private String txLogin;
	private String txSenha;
	public String getTxLogin() {
		return txLogin;
	}
	public void setTxLogin(String txLogin) {
		this.txLogin = txLogin;
	}
	public String getTxSenha() {
		return txSenha;
	}
	public void setTxSenha(String txSenha) {
		this.txSenha = txSenha;
	}

}
