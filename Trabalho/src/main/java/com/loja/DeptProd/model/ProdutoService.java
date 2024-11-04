package com.loja.DeptProd.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.loja.DeptProd.model.db.ProdutoHibernateDAOImpl;

@Service
public class ProdutoService {
	@Autowired
	@Qualifier("hibernate")
	private ProdutoHibernateDAOImpl produtoDAO;
	
	public List<Produto> listarProdutos() {
		List<Produto> lista = null;
		try {
			lista = produtoDAO.listarProdutos();
		} catch (Exception e) {
			System.err.printf("Falha ao listar os produtos. " + e.getMessage());
		}
		return lista;
	}
	
	public Produto pesquisarProduto(int codigo) {
		Produto produto = null;
		try {
			produto = produtoDAO.pesquisarProduto(codigo);
		} catch (Exception e) {
			System.err.printf("Falha ao pesquisar o produto com o código %d.", 
					codigo);
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	public boolean salvarProduto(int codigo, Produto produto) {
		boolean sucesso = false;

		try {
			Produto encontrado = produtoDAO.pesquisarProduto(codigo);

		
			if (encontrado == null) { 
				
				produtoDAO.incluirProduto(produto);
				sucesso = true;
			}
			else { 
				encontrado.setNome(produto.getNome());
				encontrado.setDataInicio(produto.getDataInicio());
			    encontrado.setDepartamento(produto.getDepartamento());
				produtoDAO.alterarProduto(encontrado);
				sucesso = true;
			}
		} catch (Exception e) {
			System.err.printf("Falha ao incluir/alterar o produto com o código %d.", 
					codigo);
			System.err.println(e.getMessage());
		}
		return sucesso;
	}
	
	public boolean excluirProduto(int codigo) {
		boolean sucesso = false;
		
		try {
			Produto encontrado = produtoDAO.pesquisarProduto(codigo);
			if (encontrado != null) { 
				
				produtoDAO.excluirProduto(encontrado);
				sucesso = true;
			}
			else
				System.err.printf("Produto com o código %d não pode ser excluído, "
						+ "pois não foi encontrado.", codigo);
		} catch (Exception e) {
			System.err.printf("Falha ao excluir o produto com o código %d.", codigo);
			System.err.println(e.getMessage());
		}
		return sucesso;
	}
}

