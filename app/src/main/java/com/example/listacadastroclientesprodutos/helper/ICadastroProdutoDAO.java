package com.example.listacadastroclientesprodutos.helper;

import com.example.listacadastroclientesprodutos.model.CadastroProduto;

import java.util.List;

public interface ICadastroProdutoDAO {

    public boolean salvar(CadastroProduto cadastroProduto);
    public boolean atualizar(CadastroProduto cadastroProduto);
    public boolean deletar(CadastroProduto cadastroProduto);
    public List<CadastroProduto> listarProdutos();
}
