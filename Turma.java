package br.com.instituicao.model;

public class Turma {
    private int codigo;
    private int sala;
    private int horario;
    private int numAlunos;
    private int professorRG;

    public Turma(int codigo, int sala, int horario, int numAlunos, int professorRG) {
        this.codigo = codigo;
        this.sala = sala;
        this.horario = horario;
        this.numAlunos = numAlunos;
        this.professorRG = professorRG;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getNumAlunos() {
        return numAlunos;
    }

    public void setNumAlunos(int numAlunos) {
        this.numAlunos = numAlunos;
    }

    public int getProfessorRG() {
        return professorRG;
    }

    public void setProfessorRG(int professorRG) {
        this.professorRG = professorRG;
    }
}
