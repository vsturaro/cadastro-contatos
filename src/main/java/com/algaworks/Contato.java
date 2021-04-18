package com.algaworks;

public class Contato {
	
	private String nome;
	private String telefone;
	private String id;
	
	public Contato() { //só precisa criar esse construtor quando se tem um especifico e em algum momento irá usar o padrão tambem
		
	}
	
	public Contato(String id, String nome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public boolean isNovo() {
		return id == null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	

}
