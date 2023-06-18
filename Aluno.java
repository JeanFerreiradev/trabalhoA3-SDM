package br.com.instituicao.model;

public class Aluno {
    private int matricula;
    private String nome;
    private int sala;

    public Aluno(int matricula, String nome, int sala) {
        this.matricula = matricula;
        this.nome = nome;
        this.sala = sala;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
}
