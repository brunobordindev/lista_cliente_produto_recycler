package com.example.listacadastroclientesprodutos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.model.CadastroCliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder> {

    private List<CadastroCliente> listaClientes;

    public ClienteAdapter(List<CadastroCliente> lista) {
        this.listaClientes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CadastroCliente cadastroCliente = listaClientes.get(position);

        holder.nome.setText(cadastroCliente.getNome());
        holder.endereco.setText(cadastroCliente.getEndereco() + " ," + cadastroCliente.getNumero() + " " + cadastroCliente.getComplemento());
        holder.telefone.setText(cadastroCliente.getTelefone());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, endereco, telefone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            endereco = itemView.findViewById(R.id.textEndereco);
            telefone = itemView.findViewById(R.id.textTelefone);
        }
    }
}
