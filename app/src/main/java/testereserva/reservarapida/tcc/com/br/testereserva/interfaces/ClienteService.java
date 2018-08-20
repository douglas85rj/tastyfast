package testereserva.reservarapida.tcc.com.br.testereserva.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;

public interface ClienteService {

    @GET("cliente")
    Call<List<Cliente>> buscarClientes();

    @GET("cliente/{email}")
    Call<Cliente> login(@Path("email") String email);

    @POST("cliente")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);
}
