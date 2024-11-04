package com.loja.DeptProd.model.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loja.DeptProd.model.Produto;

@Repository("hibernate")
@Primary
@Transactional
public class ProdutoHibernateDAOImpl implements ProdutoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Produto> listarProdutos() throws Exception {
		return entityManager.createQuery("from Produto", Produto.class).getResultList();
	}

	@Override
	public Produto pesquisarProduto(int codigo) throws Exception {
		return entityManager.find(Produto.class, codigo);
	}

	@Override
	public void incluirProduto(Produto produto) throws Exception {
		entityManager.persist(produto);
	}
	
	@Override
	public void alterarProduto(Produto produto) throws Exception {
		entityManager.merge(produto);
	}

	@Override
	public void excluirProduto(Produto produto) throws Exception {
		entityManager.remove(produto);
	}
}
