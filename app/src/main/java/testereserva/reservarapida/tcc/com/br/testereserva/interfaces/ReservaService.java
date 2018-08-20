package testereserva.reservarapida.tcc.com.br.testereserva.interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Reserva;

public interface ReservaService {

    @POST("reserva")
    Call<Reserva> cadastrarReserva(@Body Reserva reserva);
}
