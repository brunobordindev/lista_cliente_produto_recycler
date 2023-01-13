package com.example.listacadastroclientesprodutos.model;

import java.io.Serializable;

public class CadastroProduto implements Serializable {

    private Long id;
    private String nome;
    private Double preco;

    public CadastroProduto() {
    }

    public CadastroProduto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
