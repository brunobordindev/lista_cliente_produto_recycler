package com.example.listacadastroclientesprodutos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listacadastroclientesprodutos.model.CadastroProduto;

import java.util.ArrayList;
import java.util.List;

public class CadastroProdutoDAO implements ICadastroProdutoDAO{


    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public CadastroProdutoDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(CadastroProduto cadastroProduto) {

        ContentValues cv = new ContentValues();
        cv.put("nome", cadastroProduto.getNome());
        cv.put("preco", cadastroProduto.getPreco());

        try {
            escreve.insert(DbHelper.NOME_TABELA_PRODUTO, null, cv);
            Log.i("INFO", "Produto salvo com sucesso");

        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar produto" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean atualizar(CadastroProduto cadastroProduto) {

        ContentValues cv = new ContentValues();
        cv.put("nome", cadastroProduto.getNome());
        cv.put("preco", cadastroProduto.getPreco());

        String args[] = {cadastroProduto.getId().toString()};

        try {
            escreve.update(DbHelper.NOME_TABELA_PRODUTO, cv, "id=?", args);
            Log.i("INFO", "Produto salvo com sucesso");

        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar produto" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(CadastroProduto cadastroProduto) {

        String args[] = {cadastroProduto.getId().toString()};

        try {
            escreve.delete(DbHelper.NOME_TABELA_PRODUTO, "id=?", args);
            Log.i("INFO", "Produto salvo com sucesso");

        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar produto" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<CadastroProduto> listarProdutos() {

        List<CadastroProduto> listaProdutos = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.NOME_TABELA_PRODUTO + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){

            CadastroProduto produto = new CadastroProduto();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            double preco = c.getDouble(c.getColumnIndex("preco"));

            produto.setId(id);
            produto.setNome(nome);
            produto.setPreco(preco);

            listaProdutos.add(produto);

        }


        return listaProdutos;
    }
}
