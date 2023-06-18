package br.com.instituicao.view;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.instituicao.controller.InstituicaoController;

public class InterfaceVisual {
    private static InstituicaoController controller;

    public static void main(String[] args) {
        String host = "127.0.0.1";
        String user = "Jean";
        String password = "j1e2a3n4";
        String database = "Projetomvc";

        controller = new InstituicaoController(host, user, password, database);

        SwingUtilities.invokeLater(() -> exibirMenuPrincipal());
    }

    private static void exibirMenuPrincipal() {
    	boolean sair = false;
    	while (!sair) {
            String[] opcoes = { "Adicionar Aluno em Turma", "Remover Aluno de Turma",
                    "Adicionar Professor em Turma", "Remover Professor de Turma", "Sair" };

            int opcaoSelecionada = JOptionPane.showOptionDialog(null, "Menu Principal",
                    "Instituição - Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcoes, opcoes[0]);

            switch (opcaoSelecionada) {
                case 0:
                    exibirDialogAdicionarAlunoTurma();
                    break;
                case 1:
                    exibirDialogRemoverAlunoTurma();
                    break;
                case 2:
                    exibirDialogAdicionarProfessorTurma();
                    break;
                case 3:
                    exibirDialogRemoverProfessorTurma();
                    break;
                case 4:
                    sair = true;
                    break;
            }
        }

        encerrarPrograma();
    }

    private static void exibirDialogAdicionarAlunoTurma() {
        JTextField matriculaField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField salaField = new JTextField();

        Object[] mensagem = {
                "Matrícula:", matriculaField,
                "Nome:", nomeField,
                "Código da Turma:", salaField
        };

        int resultado = JOptionPane.showConfirmDialog(null, mensagem,
                "Adicionar Aluno em Turma", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            int matricula = Integer.parseInt(matriculaField.getText());
            String nome = nomeField.getText();
            int sala = Integer.parseInt(salaField.getText());

            boolean sucesso = controller.adicionarAlunoTurma(matricula, nome, sala);

            if (sucesso) {
                exibirMensagem("Aluno adicionado à turma com sucesso.");
            } else {
                exibirMensagem("Erro ao adicionar aluno à turma.");
            }
        }
    }

    private static void exibirDialogRemoverAlunoTurma() {
        JTextField matriculaField = new JTextField();
        JTextField salaField = new JTextField();

        Object[] mensagem = {
                "Matrícula:", matriculaField,
                "Código da Turma:", salaField
        };

        int resultado = JOptionPane.showConfirmDialog(null, mensagem,
                "Remover Aluno de Turma", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            int matricula = Integer.parseInt(matriculaField.getText());
            int sala = Integer.parseInt(salaField.getText());

            boolean sucesso = controller.removerAlunoTurma(matricula, sala);

            if (sucesso) {
                exibirMensagem("Aluno removido da turma com sucesso.");
            } else {
                exibirMensagem("Erro ao remover aluno da turma.");
            }
        }
    }

    private static void exibirDialogAdicionarProfessorTurma() {
        JTextField rgField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField titulacaoField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField salaField = new JTextField();

        Object[] mensagem = {
                "RG:", rgField,
                "Nome:", nomeField,
                "Titulação:", titulacaoField,
                "Salário:", salarioField,
                "Código da Turma:", salaField
        };

        int resultado = JOptionPane.showConfirmDialog(null, mensagem,
                "Adicionar Professor em Turma", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            int rg = Integer.parseInt(rgField.getText());
            String nome = nomeField.getText();
            String titulacao = titulacaoField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            int sala = Integer.parseInt(salaField.getText());

            boolean sucesso = controller.adicionarProfessorTurma(rg, nome, titulacao, salario, sala);

            if (sucesso) {
                exibirMensagem("Professor adicionado à turma com sucesso.");
            } else {
                exibirMensagem("Erro ao adicionar professor à turma.");
            }
        }
    }

    private static void exibirDialogRemoverProfessorTurma() {
        JTextField salaField = new JTextField();

        Object[] mensagem = {
                "Código da Turma:", salaField
        };

        int resultado = JOptionPane.showConfirmDialog(null, mensagem,
                "Remover Professor de Turma", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            int sala = Integer.parseInt(salaField.getText());

            boolean sucesso = controller.removerProfessorTurma(sala);

            if (sucesso) {
                exibirMensagem("Professor removido da turma com sucesso.");
            } else {
                exibirMensagem("Erro ao remover professor da turma.");
            }
        }
    }

    private static void encerrarPrograma() {
        controller.fecharConexao();
        System.exit(0);
    }

    private static void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
