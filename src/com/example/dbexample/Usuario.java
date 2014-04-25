package com.example.dbexample;

public class Usuario {

	private long id;
	private long idade;
	private String nome;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(long id, long idade, String nome) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.idade = idade;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdade() {
		return idade;
	}

	public void setIdade(long idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		// TODO Auto-generated constructor stub
		return nome + ", " + idade;
	}

}
