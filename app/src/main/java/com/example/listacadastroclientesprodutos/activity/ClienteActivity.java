package com.example.listacadastroclientesprodutos.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.adapter.ClienteAdapter;
import com.example.listacadastroclientesprodutos.helper.CadastroClienteDAO;
import com.example.listacadastroclientesprodutos.helper.RecyclerItemClickListener;
import com.example.listacadastroclientesprodutos.model.CadastroCliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ClienteAdapter adapter;
    private List<CadastroCliente> listaClientes = new ArrayList<>();
    private CadastroCliente cadastroClienteSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        setTitle("Clientes");

        recyclerView = findViewById(R.id.recyclerViewCliente);
        fab = findViewById(R.id.fabCliente);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarClienteActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        cadastroClienteSelecionado = listaClientes.get(position);

                        Intent intent = new Intent(getApplicationContext(), AdicionarClienteActivity.class);
                        intent.putExtra("clienteSelecionado", cadastroClienteSelecionado);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        cadastroClienteSelecionado = listaClientes.get(position);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(ClienteActivity.this);
                        dialog.setTitle("Excluir");
                        dialog.setMessage("Você deseja excluir cliente: \n" + cadastroClienteSelecionado.getNome() + "?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                CadastroClienteDAO cadastroClienteDAO = new CadastroClienteDAO(getApplicationContext());

                                if (cadastroClienteDAO.deletar(cadastroClienteSelecionado)){
                                    carregarListaRecyclerView();
                                    Toast.makeText(getApplicationContext(), "Sucesso ao deletar cliente!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Erro ao deletar cliente!", Toast.LENGTH_SHORT).show();
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

    public void carregarListaRecyclerView(){

        CadastroClienteDAO cadastroClienteDAO = new CadastroClienteDAO(getApplicationContext());
        listaClientes = cadastroClienteDAO.listarCliente();

        //Config dapter
        adapter = new ClienteAdapter(listaClientes);

        //Config. recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        carregarListaRecyclerView();
        super.onStart();
    }
}