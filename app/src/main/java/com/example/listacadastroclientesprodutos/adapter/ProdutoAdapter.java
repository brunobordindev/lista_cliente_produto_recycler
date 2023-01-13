package com.example.listacadastroclientesprodutos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listacadastroclientesprodutos.R;
import com.example.listacadastroclientesprodutos.model.CadastroProduto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<CadastroProduto> listaProdutos;

    public ProdutoAdapter(List<CadastroProduto> lista) {
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         CadastroProduto cadastroProduto = listaProdutos.get(position);

         String precoString = Double.toString(cadastroProduto.getPreco());
         holder.nome.setText(cadastroProduto.getNome());
         holder.preco.setText(precoString);

    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNomeProduto);
            preco = itemView.findViewById(R.id.textPrecoProduto);
        }
    }
}
