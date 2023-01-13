package com.example.listacadastroclientesprodutos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.helper.CadastroClienteDAO;
import com.example.listacadastroclientesprodutos.model.CadastroCliente;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarClienteActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEndereco, campoNumero, campoComplemento, campoTelefone;
    private CadastroCliente clienteAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cliente);

        setTitle("Cadastro cliente");

        campoNome = findViewById(R.id.editNome);
        campoEndereco = findViewById(R.id.editEndereco);
        campoNumero = findViewById(R.id.editNumero);
        campoComplemento = findViewById(R.id.editComplemento);
        campoTelefone = findViewById(R.id.editTelefone);

        clienteAtual = (CadastroCliente) getIntent().getSerializableExtra("clienteSelecionado");

        if (clienteAtual != null){

            String numero = Integer.toString(clienteAtual.getNumero());

            campoNome.setText(clienteAtual.getNome());
            campoEndereco.setText(clienteAtual.getEndereco());
            campoNumero.setText(numero);
            campoComplemento.setText(clienteAtual.getComplemento());
            campoTelefone.setText(clienteAtual.getTelefone());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:


                CadastroClienteDAO cadastroClienteDAO = new CadastroClienteDAO(getApplicationContext());

                if (clienteAtual != null){//edicao
                    if (!campoNome.getText().toString().isEmpty()){
                        if (!campoTelefone.getText().toString().isEmpty()){

                            String nome = campoNome.getText().toString();
                            String endereco = campoEndereco.getText().toString();
                            int numero = Integer.parseInt(campoNumero.getText().toString());
                            String complemento = campoComplemento.getText().toString();
                            String telefone = campoTelefone.getText().toString();

                            CadastroCliente cadastroCliente = new CadastroCliente(nome, endereco, numero, complemento, telefone);
                            cadastroCliente.setId(clienteAtual.getId());

                            if (cadastroClienteDAO.atualizar(cadastroCliente)){
                                finish();
                                Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                            }



                        }else{
                            Toast.makeText(getApplicationContext(), "Preencha o telefone", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_SHORT).show();
                    }

                }else{//salvar
                    if (!campoNome.getText().toString().isEmpty()){
                        if (!campoTelefone.getText().toString().isEmpty()){

                            String nome = campoNome.getText().toString();
                            String endereco = campoEndereco.getText().toString();
                            int numero = Integer.parseInt(campoNumero.getText().toString());
                            String complemento = campoComplemento.getText().toString();
                            String telefone = campoTelefone.getText().toString();

                            CadastroCliente cadastroCliente = new CadastroCliente(nome, endereco, numero, complemento, telefone);

                            if (cadastroClienteDAO.salvar(cadastroCliente)){
                                finish();
                                Toast.makeText(getApplicationContext(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erro ao salvar", Toast.LENGTH_SHORT).show();
                            }



                        }else{
                            Toast.makeText(getApplicationContext(), "Preencha o telefone", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }
}