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

import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Horario;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Restaurante;

public class HorariosActivity extends AppCompatActivity {

    ListView lstHorarios;
    Cliente cliente;
    Restaurante restaurante;
    Intent intent = new Intent();
    SwipeRefreshLayout horariosLayout;
    Horario horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        lstHorarios = findViewById(R.id.lstHorarios);
        intent = getIntent();
        cliente = (Cliente) intent.getSerializableExtra("cliente");
        restaurante = (Restaurante) intent.getSerializableExtra("restaurante");
        horariosLayout = findViewById(R.id.horariosLayout);
        horario = new Horario();

        listaHorariosRestaurante();
        Log.i("Tela de horarios", cliente.toString());

        lstHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                horario = (Horario) parent.getItemAtPosition(position);
                Intent vaiParaTelaDeConfirmacao = new Intent(HorariosActivity.this, ConfirmacaoActivity.class);
                vaiParaTelaDeConfirmacao.putExtra("cliente", cliente);
                vaiParaTelaDeConfirmacao.putExtra("restaurante", restaurante);
                vaiParaTelaDeConfirmacao.putExtra("horario", horario);
                startActivity(vaiParaTelaDeConfirmacao);
            }
        });

        horariosLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listaHorariosRestaurante();
            }
        });

    }

    public void listaHorariosRestaurante(){
        ArrayAdapter<Horario> adapter = new ArrayAdapter<>(HorariosActivity.this, android.R.layout.simple_list_item_1,
                                                            restaurante.getHorarios());
        lstHorarios.setAdapter(adapter);
    }
}
