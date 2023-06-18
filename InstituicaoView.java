package br.com.instituicao.view;

import br.com.instituicao.controller.InstituicaoController;
import java.util.Scanner;

public class InstituicaoView {
    private static InstituicaoController controller;
    private static Scanner scanner;

    public static void main(String[] args) {
        String host = "127.0.0.1";
        String user = "Jean";
        String password = "j1e2a3n4";
        String database = "Projetomvc";

        controller = new InstituicaoController(host, user, password, database);
        scanner = new Scanner(System.in);

        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        boolean sair = false;

        while (!sair) {
            System.out.println("----- Menu Principal -----");
            System.out.println("1. Adicionar Aluno em Turma");
            System.out.println("2. Remover Aluno de Turma");
            System.out.println("3. Adicionar Professor em Turma");
            System.out.println("4. Remover Professor de Turma");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    exibirMenuAdicionarAlunoTurma();
                    break;
                case 2:
                    exibirMenuRemoverAlunoTurma();
                    break;
                case 3:
                    exibirMenuAdicionarProfessorTurma();
                    break;
                case 4:
                    exibirMenuRemoverProfessorTurma();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }

        controller.fecharConexao();
        scanner.close();
    }

    private static void exibirMenuAdicionarAlunoTurma() {
        System.out.println("----- Adicionar Aluno em Turma -----");
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o código da turma: ");
        int sala = scanner.nextInt();

        boolean sucesso = controller.adicionarAlunoTurma(matricula, nome, sala);

        if (sucesso) {
            System.out.println("Aluno adicionado à turma com sucesso.");
        } else {
            System.out.println("Erro ao adicionar aluno à turma.");
        }
    }

    private static void exibirMenuRemoverAlunoTurma() {
        System.out.println("----- Remover Aluno de Turma -----");
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("Digite a codigo da turma: ");
        int sala = scanner.nextInt();

        boolean sucesso = controller.removerAlunoTurma(matricula, sala);

        if (sucesso) {
            System.out.println("Aluno removido da turma com sucesso.");
        } else {
            System.out.println("Erro ao remover aluno da turma.");
        }
    }
    
    private static void exibirMenuAdicionarProfessorTurma() {
        System.out.println("----- Adicionar Professor em Turma -----");
        System.out.print("Digite o RG do professor: ");
        int rg = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a titulação do professor: ");
        String titulacao = scanner.nextLine();
        System.out.print("Digite o salário do professor: ");
        double salario = scanner.nextDouble();
        System.out.print("Digite o codigo da turma para esse professor: ");
        int sala = scanner.nextInt();

        boolean sucesso = controller.adicionarProfessorTurma(rg, nome, titulacao, salario, sala);

        if (sucesso) {
            System.out.println("Professor adicionado à turma com sucesso.");
        } else {
            System.out.println("Erro ao adicionar professor à turma.");
        }
    }

    private static void exibirMenuRemoverProfessorTurma() {
        System.out.println("----- Remover Professor de Turma -----");
        System.out.print("Digite o codigo da turma: ");
        int sala = scanner.nextInt();

        boolean sucesso = controller.removerProfessorTurma(sala);

        if (sucesso) {
            System.out.println("Professor removido da turma com sucesso.");
        } else {
            System.out.println("Erro ao remover professor da turma.");
        }
    }
}
