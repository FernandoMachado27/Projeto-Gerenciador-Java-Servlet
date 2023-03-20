package br.com.fernando.gerenciador.modelo;

public class Usuario {

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean ehIgual(String login, String senha) {
		if (!this.login.equals(login)) { // se não for igual
			return false;
		}

		if (!this.senha.equals(senha)) { // se não for igual
			return false;
		}

		return true;
	}
}