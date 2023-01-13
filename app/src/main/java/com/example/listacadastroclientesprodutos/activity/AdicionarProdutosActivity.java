package com.example.listacadastroclientesprodutos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.helper.CadastroProdutoDAO;
import com.example.listacadastroclientesprodutos.model.CadastroProduto;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AdicionarProdutosActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoPreco;
    private CadastroProduto produtoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produtos);

        setTitle("Produtos");

        campoNome = findViewById(R.id.editNomeProduto);
        campoPreco = findViewById(R.id.editPrecoProduto);

        //recuperar produto edicao
        produtoAtual = (CadastroProduto) getIntent().getSerializableExtra("produtoSelecionado");

        if (produtoAtual != null){

            String preco = Double.toString(produtoAtual.getPreco());

            campoNome.setText(produtoAtual.getNome());
            campoPreco.setText(preco);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvarProduto:

                CadastroProdutoDAO cadastroProdutoDAO = new CadastroProdutoDAO(getApplicationContext());


                if (produtoAtual != null){//edicao

                    if (!campoNome.getText().toString().isEmpty()){
                        if (!campoPreco.getText().toString().isEmpty()){

                            String nome = campoNome.getText().toString();
                            double preco = Double.parseDouble(campoPreco.getText().toString());

                            CadastroProduto produto = new CadastroProduto(nome, preco);
                            produto.setId(produtoAtual.getId());

                            if (cadastroProdutoDAO.atualizar(produto)){
                                finish();
                                Toast.makeText(getApplicationContext(), "Sucesso ao atualizar produto", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erro ao atualizar produto", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Preencher valor do produto", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Preencher nome do produto", Toast.LENGTH_SHORT).show();
                    }

                }else{//salvar

                    if (!campoNome.getText().toString().isEmpty()){
                        if (!campoPreco.getText().toString().isEmpty()){

                            String nome = campoNome.getText().toString();
                            double preco = Double.parseDouble(campoPreco.getText().toString());

                            CadastroProduto produto = new CadastroProduto(nome, preco);

                            if (cadastroProdutoDAO.salvar(produto)){
                                finish();
                                Toast.makeText(getApplicationContext(), "Sucesso ao salvar produto", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erro ao salvar produto", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Preencher valor do produto", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Preencher nome do produto", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}