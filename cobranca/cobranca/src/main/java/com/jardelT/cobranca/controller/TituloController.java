package com.jardelT.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jardelT.cobranca.model.StatusTitulo;
import com.jardelT.cobranca.model.Titulo;
import com.jardelT.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("todosStatusTitulo", StatusTitulo.values());
		mv.addObject(new Titulo());
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {

		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (errors.hasErrors()) {
			return mv;
		}

		titulos.save(titulo);
		ModelAndView mv2 = new ModelAndView("redirect:/titulos/novo");
		attributes.addFlashAttribute("mensagem", "Título inserido com sucesso!");
		mv.addObject(new Titulo());
		return mv2;

	}

	@RequestMapping("/pesquisa")
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {

		return Arrays.asList(StatusTitulo.values());

	}

}
