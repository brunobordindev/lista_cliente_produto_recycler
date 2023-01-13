package com.example.listacadastroclientesprodutos.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.adapter.ProdutoAdapter;
import com.example.listacadastroclientesprodutos.helper.CadastroProdutoDAO;
import com.example.listacadastroclientesprodutos.helper.RecyclerItemClickListener;
import com.example.listacadastroclientesprodutos.model.CadastroProduto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ProdutoAdapter adapter;
    private List<CadastroProduto> listaprodutos = new ArrayList<>();
    private CadastroProduto cadastroProdutoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        setTitle("Produtos");

        fab = findViewById(R.id.fabProduto);
        recyclerView = findViewById(R.id.recyclerViewProdutos);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarProdutosActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        cadastroProdutoSelecionado = listaprodutos.get(position);

                        Intent intent = new Intent(ProdutoActivity.this, AdicionarProdutosActivity.class);
                        intent.putExtra("produtoSelecionado", cadastroProdutoSelecionado);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        cadastroProdutoSelecionado = listaprodutos.get(position);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(ProdutoActivity.this);
                        dialog.setTitle("Excluir");
                        dialog.setMessage("Você deseja excluir o produto: \n" + cadastroProdutoSelecionado.getNome() + "?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                CadastroProdutoDAO cadastroProdutoDAO = new CadastroProdutoDAO(getApplicationContext());

                                if(cadastroProdutoDAO.deletar(cadastroProdutoSelecionado)){
                                    carregarListaRecyclerViewProdutos();
                                    Toast.makeText(getApplicationContext(), "Sucesso ao deletar produto", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Erro ao deletar produto", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        dialog.setNegativeButton("Não", null);

                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));
    }

    public void carregarListaRecyclerViewProdutos(){

        CadastroProdutoDAO cadastroProdutoDAO = new CadastroProdutoDAO(getApplicationContext());
        listaprodutos = cadastroProdutoDAO.listarProdutos();

        //Config. Adapter
        adapter = new ProdutoAdapter(listaprodutos);

        //Config. RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        carregarListaRecyclerViewProdutos();
        super.onStart();
    }
}