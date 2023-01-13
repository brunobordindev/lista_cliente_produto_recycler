package com.example.listacadastroclientesprodutos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listacadastroclientesprodutos.model.CadastroCliente;

import java.util.ArrayList;
import java.util.List;

public class CadastroClienteDAO implements ICadastroClienteDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public CadastroClienteDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(CadastroCliente cadastroCliente) {

        ContentValues cv = new ContentValues();
        cv.put("nome", cadastroCliente.getNome());
        cv.put("endereco", cadastroCliente.getEndereco());
        cv.put("numero", cadastroCliente.getNumero());
        cv.put("complemento", cadastroCliente.getComplemento());
        cv.put("telefone", cadastroCliente.getTelefone());

        try {
            escreve.insert(DbHelper.NOME_TABELA_CLIENTE, null, cv);
            Log.i("INFO", "Sucesso ao salvar cadastro");
        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar cadastro" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean atualizar(CadastroCliente cadastroCliente) {

        ContentValues cv = new ContentValues();
        cv.put("nome", cadastroCliente.getNome());
        cv.put("endereco", cadastroCliente.getEndereco());
        cv.put("numero", cadastroCliente.getNumero());
        cv.put("complemento", cadastroCliente.getComplemento());
        cv.put("telefone", cadastroCliente.getTelefone());

        String args[]= {cadastroCliente.getId().toString()};
        try {
            escreve.update(DbHelper.NOME_TABELA_CLIENTE,cv ,"id=?", args);
            Log.i("INFO", "Sucesso ao salvar cadastro");
        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar cadastro" + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean deletar(CadastroCliente cadastroCliente) {

        String args[]= {cadastroCliente.getId().toString()};
        try {
            escreve.delete(DbHelper.NOME_TABELA_CLIENTE, "id=?" , args);
            Log.i("INFO", "Sucesso ao deletar cliente");
        }catch (Exception e){
            Log.i("INFO", "Erro ao deletar cliente" + e.getMessage());
        }

        return true;
    }

    @Override
    public List<CadastroCliente> listarCliente() {

        List<CadastroCliente> listaCLientes = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.NOME_TABELA_CLIENTE + " ;";
        Cursor cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()){

            CadastroCliente cadastroCliente = new CadastroCliente();

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            int numero = cursor.getInt(cursor.getColumnIndex("numero"));
            String complemento = cursor.getString(cursor.getColumnIndex("complemento"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));

            cadastroCliente.setId(id);
            cadastroCliente.setNome(nome);
            cadastroCliente.setEndereco(endereco);
            cadastroCliente.setNumero(numero);
            cadastroCliente.setComplemento(complemento);
            cadastroCliente.setTelefone(telefone);

            listaCLientes.add(cadastroCliente);
        }

        return listaCLientes;
    }
}
