package com.loja.DeptProd.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.loja.DeptProd.model.Produto;
import com.loja.DeptProd.model.ProdutoService;
import com.loja.DeptProd.model.validador.ProdutoValidator;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("listaDeProdutos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoValidator produtoValidator;
	

@RequestMapping("/listarProdutos")
	public ModelAndView listarProdutos(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("listaDeProdutos", produtoService.listarProdutos() );
		return modelAndView;
	}
	
	@RequestMapping("/pesquisarProduto")
	@ResponseBody
	public String pesquisarProduto(@RequestParam(value = "codigo") int codigo) {
		String retorno = "";
		
		Produto produto = produtoService.pesquisarProduto(codigo);
		retorno += " => "+ produto.toString();

		return retorno;
	}
	
	@PostMapping("/salvarProduto")
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, Errors errors) throws ParseException{
   produtoValidator.validate(produto, errors);
		if (errors.hasErrors())
			return "produto";
		
		int codigo = produto.getCodigo();
		produtoService.salvarProduto(codigo, produto);
		return "redirect:/listarProdutos";
	}
	
	@GetMapping("/excluirProduto")
	@ResponseBody
	public String excluirProduto(@RequestParam(value = "codigo") int codigo) {
	produtoService.excluirProduto(codigo);
	

		return "redirect:/listarProdutos";
	}
	
	@GetMapping("/selecionarProduto")
	public ModelAndView selecionarProduto(@RequestParam("codigo") int codigo) {
		Produto produto = produtoService.pesquisarProduto(codigo);
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("listaDeProdutos", produtoService.listarProdutos());
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
}
