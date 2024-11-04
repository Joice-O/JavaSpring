package com.loja.DeptProd.model.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loja.DeptProd.model.Produto;


@Component
public class ProdutoValidator implements Validator {
	@Override
	public boolean supports(Class <?> clazz) {
		return Produto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Produto produto = (Produto) target;
		
		if(produto.getNome().isEmpty())
			errors.rejectValue ("nome", "nome.vazio");
		
		if (produto.getDataInicio() == null)
			errors.rejectValue("dataInicio", "inicio.nulo");

		}
	}

