package com.loja.DeptProd.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nome;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date dataInicio;
	
    @ManyToOne
    @JoinColumn(name = "departamento_codigoDept")
    private Departamento departamento;
	
    public Produto() {
    	
    }
	public Produto (int codigo, String nome, Date dataInicio) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataInicio = dataInicio;
	}

	public Produto(String nome, Date dataInicio) {
		this.nome = nome;
		this.dataInicio = dataInicio;
	}
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		return getCodigo() + " - " + getNome() + " - " + getDataInicio() + 
				" - " + getDepartamento();
	}
}

