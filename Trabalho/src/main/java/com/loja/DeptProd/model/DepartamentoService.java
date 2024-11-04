package com.loja.DeptProd.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.DeptProd.model.db.DepartamentoDAO;
import com.loja.DeptProd.model.db.ProdutoDAO;




@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoDAO departamentoDAO;
	@Autowired
	private ProdutoDAO produtoDAO;

	public List<Departamento> listarDepartamentos() {
		List<Departamento> lista = null;
		try {
			lista = departamentoDAO.listarDepartamentos();
		} catch (Exception e) {
			System.err.printf("Falha ao listar Departamentos. " + e.getMessage());
		}
		return lista;
	}

	public Departamento pesquisarDepartamento(int codigoDept) {
		Departamento departamento = null;
		try {
			departamento = departamentoDAO.pesquisarDepartamento(codigoDept);
		} catch (Exception e) {
			System.err.printf("Falha ao pesquisar p departamento com o código %d.", 
					codigoDept);
			System.err.println(e.getMessage());
		}
		return departamento;
	}

	public boolean salvarDepartamento(int codigoDept, Departamento departamento) {
		boolean sucesso = false;

		try {
			Departamento encontrado = departamentoDAO.pesquisarDepartamento(codigoDept);
			if (encontrado == null) {
				departamentoDAO.incluirDepartamento(departamento);
				for (Produto p : departamento.getProdutosGrupo()) {
					p.setDepartamento(departamento);
					produtoDAO.alterarProduto(p);
				}
				sucesso = true;
			} else {
				encontrado.setTitulo(departamento.getTitulo());
				encontrado.setProdutosGrupo(departamento.getProdutosGrupo());
				departamentoDAO.alterarDepartamento(encontrado);
				for (Produto p : encontrado.getProdutosGrupo()) {
					p.setDepartamento(encontrado);
					produtoDAO.alterarProduto(p);
				}
				sucesso = true;
			}
		} catch (Exception e) {
			System.err.printf("Falha ao incluir/alterar o Departamento com o código %d.", 
					codigoDept);
			System.err.println(e.getMessage());
		}

		return sucesso;
	}

	public boolean excluirDepartamento(int codigoDept) {
		boolean sucesso = false;
		try {
			Departamento encontrado = departamentoDAO.pesquisarDepartamento(codigoDept);
			if (encontrado != null) {
				departamentoDAO.excluirDepartamento(encontrado);
				sucesso = true;
			} else
				System.err.printf("O departamento com o código %d não pode ser excluído, " 
			+ "pois não foi encontrado.", codigoDept);
		} catch (Exception e) {
			System.err.printf("Falha ao excluir o departamento com o código %d.", codigoDept);
			System.err.println(e.getMessage());
		}
		return sucesso;
	}

}
