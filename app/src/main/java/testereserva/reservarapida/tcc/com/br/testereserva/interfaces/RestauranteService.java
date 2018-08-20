package testereserva.reservarapida.tcc.com.br.testereserva.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Restaurante;

public interface RestauranteService {

    @GET("restaurante")
    Call<List<Restaurante>> buscarRestaurantes();
}
