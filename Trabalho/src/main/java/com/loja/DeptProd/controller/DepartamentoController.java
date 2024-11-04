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
import com.loja.DeptProd.model.Departamento;
import com.loja.DeptProd.model.DepartamentoService;
import com.loja.DeptProd.model.Produto;
import com.loja.DeptProd.model.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({"listaProdutos", "listaDepartamentos"})
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private ProdutoService produtoService;


	@GetMapping("/listarDepartamentos")
	public ModelAndView listarDepartamentos() {
		ModelAndView modelAndView = new ModelAndView("departamento");
		modelAndView.addObject("listaDepartamentos", departamentoService.listarDepartamentos());
		modelAndView.addObject("departamento", new Departamento());
		modelAndView.addObject("listaProdutos", produtoService.listarProdutos());
		return modelAndView;
	}

	@RequestMapping("/pesquisarDepartamento")
	@ResponseBody
	public String pesquisarDepartamento(@RequestParam(value = "codigo") int codigoDept) {
		String retorno = "";

		Departamento departamento = departamentoService.pesquisarDepartamento(codigoDept);
		retorno += " => " + departamento.toString();

		return retorno;
	}

	@PostMapping("/salvarDepartamento")
	@Transactional
	public String salvarDepartamento(@Valid @ModelAttribute("departamento") Departamento pDepartamento, Errors errors) throws ParseException{
		
		if (errors.hasErrors())
			return "departamento";
		
		Departamento departamento = new Departamento(pDepartamento.getTitulo(), pDepartamento.getProdutosGrupo());
		for (Produto p : departamento.getProdutosGrupo()) {
			p.setDepartamento(departamento);
			produtoService.salvarProduto(p.getCodigo(), p);
		}
		departamentoService.salvarDepartamento(departamento.getCodigoDept(), departamento);
		return "redirect:/listarDepartamentos";
	}

	@RequestMapping("/excluirDepartamento")
	@ResponseBody
	public String excluirDepartamento(@RequestParam(value = "codigoDept") int codigoDept) {
		String retorno = "";

		boolean ok = departamentoService.excluirDepartamento(codigoDept);
		retorno += " => Departamento excluído: " + ok;

		return retorno;
	}
	
}
