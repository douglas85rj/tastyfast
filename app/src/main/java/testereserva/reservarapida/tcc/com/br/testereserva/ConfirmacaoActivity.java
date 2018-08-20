package testereserva.reservarapida.tcc.com.br.testereserva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testereserva.reservarapida.tcc.com.br.testereserva.config.RetrofitConfig;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Horario;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Reserva;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Restaurante;

public class ConfirmacaoActivity extends AppCompatActivity {

    TextView txtRestaurante, txtHorario, txtCliente;
    Button btConfirmar;
    Intent intent = new Intent();
    Cliente cliente;
    Restaurante restaurante;
    Horario horario;
    Reserva reserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);

        txtRestaurante = findViewById(R.id.txtRestauranteConf);
        txtHorario = findViewById(R.id.txtHorarioConf);
        txtCliente = findViewById(R.id.txtClienteConf);
        btConfirmar = findViewById(R.id.btConfirmar);
        intent = getIntent();
        cliente = (Cliente) intent.getSerializableExtra("cliente");
        restaurante = (Restaurante) intent.getSerializableExtra("restaurante");
        horario = (Horario) intent.getSerializableExtra("horario");

        txtRestaurante.setText(restaurante.getNome());
        txtHorario.setText(horario.getHorario());
        txtCliente.setText(cliente.getNome());

        Log.i("Tela de Confirmação", cliente.toString());

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserva = new Reserva();
                reserva.setCliente(cliente);
                reserva.setHorario(horario.getHorario());
                reserva.setRestaurante(restaurante);
                confirmaReserva(reserva);
            }
        });
    }

    public void confirmaReserva(Reserva reserva){
        Call<Reserva> call = new RetrofitConfig().getReservaService().cadastrarReserva(reserva);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ConfirmacaoActivity.this);
        progressDialog.setMessage("Aguarde...");
        progressDialog.setTitle("Reserva Rápida");
        progressDialog.show();

        call.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                progressDialog.dismiss();
                Log.i("resposta da gravacao", response.body().toString());
                Intent vaiParaTelaDeRestaurante = new Intent(ConfirmacaoActivity.this, RestaurantesActivity.class);
                vaiParaTelaDeRestaurante.putExtra("clienteLogado", cliente);
                startActivity(vaiParaTelaDeRestaurante);
                finish();
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Erro gravação", t.getMessage());
                Toast.makeText(ConfirmacaoActivity.this, "Problemas ao gravar reserva...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
