package com.loja.DeptProd.model.db;

import java.util.List;

import com.loja.DeptProd.model.Produto;

public interface ProdutoDAO {
	public List<Produto> listarProdutos() throws Exception;
	public Produto pesquisarProduto(int codigo) throws Exception;
	public void incluirProduto(Produto produto) throws Exception;
	public void alterarProduto(Produto produtoo) throws Exception;
	public void excluirProduto(Produto produto) throws Exception;
}
