package br.com.instituicao.controller;

import br.com.instituicao.model.Aluno;
import br.com.instituicao.model.BancoDeDados;
import br.com.instituicao.model.Professor;
import br.com.instituicao.model.Turma;

public class InstituicaoController {
    private BancoDeDados bancoDeDados;

    public InstituicaoController(String host, String user, String password, String database) {
        bancoDeDados = new BancoDeDados(host, user, password, database);
    }

    public boolean adicionarAlunoTurma(int matricula, String nome, int sala) {
        Aluno aluno = new Aluno(matricula, nome, sala);
        Turma turma = BancoDeDados.obterTurma(sala);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return false;
        }

        return bancoDeDados.adicionarAlunoTurma(aluno, turma);
    }

    public boolean removerAlunoTurma(int matricula, int sala) {
        Aluno aluno = new Aluno(matricula, "", sala);
        Turma turma = BancoDeDados.obterTurma(sala);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return false;
        }

        return bancoDeDados.removerAlunoTurma(aluno, turma);
    }
    
    public boolean adicionarProfessorTurma(int rg, String nome, String titulacao, double salario, int sala) {
        Professor professor = new Professor(rg, nome, titulacao, salario, sala);
        Turma turma = BancoDeDados.obterTurma(sala);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return false;
        }

        return bancoDeDados.adicionarProfessorTurma(professor, turma);
    }

    public boolean removerProfessorTurma(int sala) {
        Professor professor = new Professor(0, "", "", 0, sala);
        Turma turma = BancoDeDados.obterTurma(sala);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return false;
        }

        return bancoDeDados.removerProfessorTurma(professor, turma);
    }

    public void fecharConexao() {
        bancoDeDados.fecharConexao();
    }
}
