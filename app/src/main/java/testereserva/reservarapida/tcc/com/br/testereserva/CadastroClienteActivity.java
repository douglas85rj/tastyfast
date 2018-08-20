package testereserva.reservarapida.tcc.com.br.testereserva;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testereserva.reservarapida.tcc.com.br.testereserva.config.RetrofitConfig;
import testereserva.reservarapida.tcc.com.br.testereserva.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    Cliente cliente;
    EditText edtEmail, edtNome;
    Button btGravarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edtEmail = findViewById(R.id.edtCadCliEmail);
        edtNome = findViewById(R.id.edtCadCliNome);
        btGravarCliente = findViewById(R.id.btGravarCliente);

        btGravarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, nome;
                email = edtEmail.getText().toString();
                nome = edtNome.getText().toString();

                if(! (email.equals("") || nome.equals("")) ){
                    cliente = new Cliente();
                    cliente.setNome(nome);
                    cliente.setEmail(email);
                    gravarClienteWS(cliente);
                } else{
                    Toast.makeText(CadastroClienteActivity.this, "Preencha todos os campos...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void limpaCampos(){
        edtNome.setText("");
        edtEmail.setText("");
    }

    public void gravarClienteWS(final Cliente cliente){
        Call<Cliente> call = new RetrofitConfig().getClienteService().cadastrarCliente(cliente);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CadastroClienteActivity.this);
        progressDialog.setMessage("Aguarde...");
        progressDialog.setTitle("Reserva Rápida");
        progressDialog.show();

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                progressDialog.dismiss();
                limpaCampos();
                Toast.makeText(CadastroClienteActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Erro ao recuperar", t.getMessage());
            }
        });
    }
}
