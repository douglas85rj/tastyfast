package testereserva.reservarapida.tcc.com.br.testereserva.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurante implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idRestaurante;
	private String nome;
	private String email;
	private String senha;
	

	private Endereco endereco;
	private List<Horario> horarios;
	private List<Cardapio> cardapios; 
	
	public Restaurante() {
	}

	public Restaurante(Integer idRestaurante, String nome, String email, String senha) {
		super();
		this.idRestaurante = idRestaurante;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}


	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}

	public void addHorario(Horario horario){
		if(horarios == null){
			horarios = new ArrayList<Horario>();
		}
		horarios.add(horario);
	}
	
	public void addCardapio(Cardapio cardapio){
		if(cardapios == null){
			cardapios = new ArrayList<Cardapio>();
		}
		cardapios.add(cardapio);
	}

	@Override
	public String toString() {
		return idRestaurante + " - " + nome;
	}

}
