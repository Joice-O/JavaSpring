package com.loja.DeptProd.model.db;

import java.util.List;

import com.loja.DeptProd.model.Departamento;

public interface DepartamentoDAO {
	public List<Departamento> listarDepartamentos() throws Exception;
	public Departamento pesquisarDepartamento(int codigoDept) throws Exception;
	public void incluirDepartamento(Departamento departamento) throws Exception;
	public void alterarDepartamento(Departamento departamento) throws Exception;
	public void excluirDepartamento(Departamento departamento) throws Exception;
}
