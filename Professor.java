package br.com.instituicao.model;

public class Professor {
    private int rg;
    private String nome;
    private String titulacao;
    private double salario;
    private int sala;

    public Professor(int rg, String nome, String titulacao, double salario, int sala) {
        this.rg = rg;
        this.nome = nome;
        this.titulacao = titulacao;
        this.salario = salario;
        this.sala = sala;
    }
    
    public Professor(int rg, String nome, String titulacao, double salario) {
        this.rg = rg;
        this.nome = nome;
        this.titulacao = titulacao;
        this.salario = salario;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
}
