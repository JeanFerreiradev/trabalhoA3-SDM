package br.com.instituicao.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDeDados {
    private static Connection connection;

    public BancoDeDados(String host, String user, String password, String database) {
        try {
            String url = "jdbc:mysql://" + host + "/" + database;
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void atualizarNumeroAlunosTurma(int codigo, int incremento) {
        try {
            String sql = "UPDATE Turma SET numAlunos = numAlunos + ? WHERE codigo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, incremento);
            statement.setInt(2, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean adicionarAlunoTurma(Aluno aluno, Turma turma) {
        try {
            String sql = "INSERT INTO Aluno (matricula, nome, sala) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aluno.getMatricula());
            statement.setString(2, aluno.getNome());
            statement.setInt(3, turma.getSala());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                // Incrementar o número de alunos na turma
                atualizarNumeroAlunosTurma(turma.getCodigo(), 1);
            }

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerAlunoTurma(Aluno aluno, Turma turma) {
        try {
            String sql = "DELETE FROM Aluno WHERE matricula = ? AND sala = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aluno.getMatricula());
            statement.setInt(2, turma.getSala());
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                // Decrementar o número de alunos na turma
                atualizarNumeroAlunosTurma(turma.getCodigo(), -1);
            }

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean adicionarProfessorTurma(Professor professor, Turma turma) {
        try {
            // Inserir o professor na tabela Professor
            String sqlInsertProfessor = "INSERT INTO Professor (nome, rg, titulacao, salario) VALUES (?, ?, ?, ?)";
            PreparedStatement statementInsertProfessor = connection.prepareStatement(sqlInsertProfessor);
            statementInsertProfessor.setString(1, professor.getNome());
            statementInsertProfessor.setInt(2, professor.getRg());
            statementInsertProfessor.setString(3, professor.getTitulacao());
            statementInsertProfessor.setDouble(4, professor.getSalario());
            int rowsInsertedProfessor = statementInsertProfessor.executeUpdate();

            // Atualizar a coluna professorRG na tabela Turma
            String sqlUpdateTurma = "UPDATE Turma SET professorRG = ? WHERE sala = ?";
            PreparedStatement statementUpdateTurma = connection.prepareStatement(sqlUpdateTurma);
            statementUpdateTurma.setInt(1, professor.getRg());
            statementUpdateTurma.setInt(2, turma.getSala());
            int rowsUpdatedTurma = statementUpdateTurma.executeUpdate();

            // Verificar se ambas as operações foram bem-sucedidas
            return rowsInsertedProfessor > 0 && rowsUpdatedTurma > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean removerProfessorTurma(Professor professor, Turma turma) {
        try {
            String sql = "UPDATE Turma SET professorRG = NULL WHERE sala = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, turma.getSala());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Turma obterTurma(int codigo) {
        Turma turma = null;

        try {
            // Consultar a turma pelo código
            String query = "SELECT sala, horario, numAlunos, professorRG FROM Turma WHERE codigo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Criar o objeto Turma com os dados do resultado da consulta
                int sala = resultSet.getInt("sala");
                int horario = resultSet.getInt("horario");
                int numAlunos = resultSet.getInt("numAlunos");
                int professorRG = resultSet.getInt("professorRG");
                turma = new Turma(codigo, sala, horario, numAlunos, professorRG);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter turma do banco de dados: " + e.getMessage());
        }

        return turma;
    }

    public void fecharConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
