package com.algaworks;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatosControle {
	
	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>(); //não precisa informar o tamanho
	
	//private static final Contato[ LISTA_CONTATOS2 = new Contato[10]; // tipo array necessita informar a capacidade
	
	static {
		LISTA_CONTATOS.add(new Contato ("1", "Vander", "+55 67 99122 0489"));
		LISTA_CONTATOS.add(new Contato ("2", "Paulo", "+55 67 99122 0000"));
		LISTA_CONTATOS.add(new Contato ("3", "Giovana", "+55 67 99122 0001"));
		LISTA_CONTATOS.add(new Contato ("4", "Isaac", "+55 67 99122 0002"));
		LISTA_CONTATOS.add(new Contato ("5", "Isadora", "+55 67 99122 0003"));


	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar"); 
		modelAndView.addObject("contatos",LISTA_CONTATOS);
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("formulario"); 
		modelAndView.addObject("contato", new Contato());
		return modelAndView;
	}
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato) {
		String id = UUID.randomUUID().toString(); //gerar id randonicamente
		contato.setId(id);
		
		LISTA_CONTATOS.add(contato);
		
		return "redirect:/contatos"; //redireciona para listagem
	}
	
	@GetMapping("contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) { //indica ao MVC que o parametro esta na url
		ModelAndView modelAndView = new ModelAndView("formulario"); 
		
		Contato contato = procurarContato(id); //procura o contato e ja traz preenchido
		
		modelAndView.addObject("contato", contato);
		return modelAndView;
	}
	
	@PutMapping("contatos/{id}")
	public String atualizar(Contato contato) {
		Integer indice = procurarIndiceContato(contato.getId());
		
		Contato contatoAntigo = LISTA_CONTATOS.get(indice);
		
		LISTA_CONTATOS.remove(contatoAntigo);
		
		LISTA_CONTATOS.add(indice, contato);
		
		return "redirect:/contatos";
	}
	
	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable String id) {
		Contato contato = procurarContato(id);
		
		LISTA_CONTATOS.remove(contato);
		
		return "redirect:/contatos";
		
	}
	
	//------------------Métodos Auxiliares--------------
	
	
	private Contato procurarContato(String id) {
	Integer indice = procurarIndiceContato(id);
	
	if(indice != null) {
		Contato contato = LISTA_CONTATOS.get(indice);
		return contato;
	}
		return null;
	}
	
	private Integer procurarIndiceContato(String id) {
		for(int i = 0; i < LISTA_CONTATOS.size(); i++) {
			Contato contato = LISTA_CONTATOS.get(i);
			
			if(contato.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}
	

}
