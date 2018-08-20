package testereserva.reservarapida.tcc.com.br.testereserva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testereserva.reservarapida.tcc.com.br.testereserva.config.RetrofitConfig;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;

public class ClienteActivity extends AppCompatActivity {

    Button btCadastrar, btEntrar;
    EditText edtEmailLogin, edtSenhaLogin;
    Cliente logado = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btCadastrar = findViewById(R.id.btCadastro);
        btEntrar = findViewById(R.id.btEntrar);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaTelaDeCadastro = new Intent(ClienteActivity.this, CadastroClienteActivity.class);
                startActivity(vaiParaTelaDeCadastro);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edtEmailLogin.getText().toString().equals("") && edtSenhaLogin.getText().toString().equals(""))){
                    Cliente usuario = new Cliente();
                    usuario.setEmail(edtEmailLogin.getText().toString());
                    usuario.setSenha(edtSenhaLogin.getText().toString());
                    fazLogin(usuario);
                } else{
                    Toast.makeText(ClienteActivity.this, "Preencha email e senha...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void mostraRestaurantes(){
        Toast.makeText(this, "Tem que chamar a tela de restaurantes", Toast.LENGTH_SHORT).show();
    }

    public void fazLogin(Cliente usuarioApp){
        Call<Cliente> call = new RetrofitConfig().getClienteService().login(usuarioApp.getEmail());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ClienteActivity.this);
        progressDialog.setMessage("Aguarde...");
        progressDialog.setTitle("Reserva Rápida");
        progressDialog.show();

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.body() != null){
                    progressDialog.dismiss();
                    logado = response.body();
                    Log.i("Tela de Login", logado.toString());
                    Intent vaiParaTelaDeRestaurante = new Intent(ClienteActivity.this, RestaurantesActivity.class);
                    vaiParaTelaDeRestaurante.putExtra("clienteLogado", logado);
                    startActivity(vaiParaTelaDeRestaurante);
                    finish();
                } else{
                    progressDialog.dismiss();
                    Toast.makeText(ClienteActivity.this, "Nenhum usuário localizado...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Erro de login", t.getMessage());
            }
        });


    }

}
