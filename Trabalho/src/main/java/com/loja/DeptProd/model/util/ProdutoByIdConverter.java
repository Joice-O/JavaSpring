package com.loja.DeptProd.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.loja.DeptProd.model.Produto;
import com.loja.DeptProd.model.db.ProdutoDAO;


@Component
public class ProdutoByIdConverter implements Converter<String, Produto> {
	@Autowired
	ProdutoDAO produtoDAO;

	@Override
	public Produto convert(String id) {
		Produto convertido = null;
		try {
			convertido = produtoDAO.pesquisarProduto(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertido;
	}
}