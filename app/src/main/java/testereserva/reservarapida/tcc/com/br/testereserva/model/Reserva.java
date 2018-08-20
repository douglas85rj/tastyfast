package testereserva.reservarapida.tcc.com.br.testereserva.model;

import java.io.Serializable;

public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idReserva;
	private Restaurante restaurante;
	private Cliente cliente;
	

	private Mesa mesa;
	
	private String horario;
	
	public Reserva() {
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", restaurante=" + restaurante + ", cliente=" + cliente + ", mesa="
				+ mesa + ", horario=" + horario + "]";
	}

}
