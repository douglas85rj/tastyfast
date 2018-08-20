package testereserva.reservarapida.tcc.com.br.testereserva.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testereserva.reservarapida.tcc.com.br.testereserva.interfaces.ClienteService;
import testereserva.reservarapida.tcc.com.br.testereserva.interfaces.ReservaService;
import testereserva.reservarapida.tcc.com.br.testereserva.interfaces.RestauranteService;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig (){
        this.retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.6:8080/reservaservice/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    public RestauranteService getRestauranteService(){
        return this.retrofit.create(RestauranteService.class);
    }

    public ClienteService getClienteService(){
        return this.retrofit.create(ClienteService.class);
    }

    public ReservaService getReservaService(){
        return this.retrofit.create(ReservaService.class);
    }
}
