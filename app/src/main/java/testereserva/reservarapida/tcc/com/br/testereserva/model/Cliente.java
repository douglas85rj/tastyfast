package testereserva.reservarapida.tcc.com.br.testereserva.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idCliente;
	private String nome;
	private String email;
	private String senha;
	
	public Cliente() {
	}

	public Cliente(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"idCliente=" + idCliente +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
