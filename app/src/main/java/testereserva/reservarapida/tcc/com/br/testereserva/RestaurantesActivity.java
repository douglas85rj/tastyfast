package testereserva.reservarapida.tcc.com.br.testereserva;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testereserva.reservarapida.tcc.com.br.testereserva.config.RetrofitConfig;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Restaurante;

public class RestaurantesActivity extends AppCompatActivity {

    ListView lstRestaurantes;
    Restaurante restaurante;
    List<Restaurante> restaurantes;
    SwipeRefreshLayout layout;
    Cliente cliente = new Cliente();
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);

        lstRestaurantes = findViewById(R.id.lstRestaurantes);
        restaurantes = new ArrayList<>();
        layout = findViewById(R.id.refreshContainer);
        intent = getIntent();
        cliente = (Cliente) intent.getSerializableExtra("clienteLogado");
        recuperaRestaurante();

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recuperaRestaurante();
            }
        });

        lstRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurante = (Restaurante) parent.getItemAtPosition(position);
                Intent vaiParaTelaDeHorarios = new Intent(RestaurantesActivity.this, HorariosActivity.class);
                vaiParaTelaDeHorarios.putExtra("cliente", cliente);
                vaiParaTelaDeHorarios.putExtra("restaurante", restaurante);
                startActivity(vaiParaTelaDeHorarios);
            }
        });
    }

    public void recuperaRestaurante(){
        Call<List<Restaurante>> call = new RetrofitConfig().getRestauranteService().buscarRestaurantes();

        call.enqueue(new Callback<List<Restaurante>>(){

            @Override
            public void onResponse(Call<List<Restaurante>> call, Response<List<Restaurante>> response) {
                restaurantes = response.body();
                ArrayAdapter<Restaurante> adapter = new ArrayAdapter<>(RestaurantesActivity.this,
                                                                                 android.R.layout.simple_list_item_1,
                                                                                 restaurantes);
                lstRestaurantes.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Restaurante>> call, Throwable t) {
                Log.e("Erro ao receber: ", "Problemas ao recuperar dados do WS:\n" + t.getMessage());
            }
        });

        layout.setRefreshing(false);
        Log.i("Tela de restaurantes", cliente.toString());
    }
}
