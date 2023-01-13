package com.example.listacadastroclientesprodutos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION=1;
    public static String DB_CADASTRO = "DB_CADASTRO";
    public static String NOME_TABELA_CLIENTE="clientes";
    public static String NOME_TABELA_PRODUTO="produtos";


    public DbHelper(Context context) {
        super(context, DB_CADASTRO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCliente = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_CLIENTE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, endereco TEXT, numero INTEGER, complemento VARCHAR, telefone VARCHAR NOT NULL); ";

        String sqlProduto = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_PRODUTO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, preco DECIMAL(6,2) ); ";

        try {
            db.execSQL(sqlCliente);
            db.execSQL(sqlProduto);
            Log.i("INFO", "Sucesso ao criar tabela");
        }catch (Exception e){
            Log.i("INFO", "Erro ao criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
