package com.loja.DeptProd.model.db;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import com.loja.DeptProd.model.Departamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Repository
@Transactional
public class DepartamentoHibernateDAOImpl implements DepartamentoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Departamento> listarDepartamentos() throws Exception {
		return entityManager.createQuery("from Departamento", Departamento.class).getResultList();
		
	}

	@Override
	public Departamento pesquisarDepartamento(int codigoDept) throws Exception {
		return entityManager.find(Departamento.class, codigoDept);
		
	}

	@Override
	public void incluirDepartamento(Departamento departamento) throws Exception {
		entityManager.persist(departamento);
	}

	@Override
	public void alterarDepartamento(Departamento departamento) throws Exception {
		entityManager.merge(departamento);
	}

	@Override
	public void excluirDepartamento(Departamento departamento) throws Exception {
		entityManager.remove(departamento);
	}

}

