package com.loja.DeptProd.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoDept;
	@Pattern (regexp = "^DEPTO:(\\s|[A-Z]|[a-z]){1,30}$",
			message = "valor deve iniciar com \"DEPTO:\" e ter um tamanho máximo de 30")
	private String titulo;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    @Size(min=1, max=20)
    private List<Produto> produtosGrupo = new ArrayList<Produto>();
    
    public Departamento() { 
    	
    }
    
    public Departamento (String titulo, List<Produto> produtosGrupo) {
        this.titulo = titulo;
        this.produtosGrupo = produtosGrupo;
    }
    
    public Departamento(int codigoDept, String titulo, List<Produto> produtosGrupo) {
    	this.codigoDept = codigoDept;
        this.titulo = titulo;
        this.produtosGrupo = produtosGrupo;
    }
    
	public int getCodigoDept() {
		return codigoDept;
	}

	public void setCodigoDept(int codigoDept) {
		this.codigoDept = codigoDept;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public List<Produto> getProdutosGrupo() {
		return produtosGrupo;
	}

	public void setProdutosGrupo(List<Produto> produtosGrupo) {
		this.produtosGrupo = produtosGrupo;
	}
	
	@Override
	public String toString() {
		return "Departamento [codigo=" + codigoDept + ", titulo=" + titulo + ","
				+ ", total produtos Grupo=" + produtosGrupo.size() + "]";
	}
}
