package com.example.listacadastroclientesprodutos.helper;

import com.example.listacadastroclientesprodutos.model.CadastroCliente;

import java.util.List;

public interface ICadastroClienteDAO {

    public boolean salvar(CadastroCliente cadastroCliente);
    public boolean atualizar(CadastroCliente cadastroCliente);
    public boolean deletar(CadastroCliente cadastroCliente);
    public List<CadastroCliente> listarCliente();
}
