package aplicativo;

import entidades.Conta;
import entidades.ContaNegocios;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Conta> list = new ArrayList<>();

        System.out.println("    ---SISTEMA BANCARIO---    ");
        System.out.println();
        System.out.println("---REGISTRAR CONTAS NO BANCO---");
        System.out.print("DIGITAR>Quantas contas serão registradas? ");
        int n = sc.nextInt();

        //SET DADOS
        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.println("Conta Bancaria  #" + i + ": ");
            System.out.print("DIGITAR>Numero identificador da Conta: ");
            int identificador = sc.nextInt();
            //filtro de identificador de contas repetidas
            while (contasRegistadas(list, identificador)) {
                System.out.print("DIGITAR>\nEssa conta já esta em uso. Tente novamente: ");
                identificador = sc.nextInt();
            }

            System.out.print("DIGITAR>Nome do Titular: ");
            sc.nextLine();
            String titular = sc.nextLine();
            System.out.print("DIGITAR>Saldo Atual do Titular: ");
            double saldo = sc.nextDouble();
            list.add(new Conta(identificador, titular, saldo));
        }

        System.out.println();
        System.out.println("---SISTEMA BANCARIO---");
        System.out.print("DIGITAR>Sua Conta com Seu Identificador: ");
        int identificador = sc.nextInt();
        Conta conta = list.stream().filter(x -> x.getidentificador() == identificador).findFirst().orElse(null);

        if (conta == null) {
            System.out.println("AVISO>Esta conta  não existe!");
            System.out.println();
            System.out.println("SAINDO DO SISTEMA>");
        } else {
            System.out.println("ID>"+ identificador);
            System.out.println("Opições:\n1.Saque\n2.Deposito\n3.Emprestimo");
            System.out.println("DIGITAR>Escolha Qual Operacao Fazer: ");
            int moviValor = sc.nextInt();
            switch (moviValor) {
                case 1:
                    System.out.print("DIGITAR>Valor a Sacar: ");
                    double valor = sc.nextDouble();
                    conta.saque(valor);
                    break;
                case 2:
                    System.out.print("DIGITAR>Valor a Depositar: ");
                    double valo = sc.nextDouble();
                    conta.deposito(valo);
                    break;
                case 3:
                    ContaNegocios negocios = new ContaNegocios(identificador, conta.getTitular(), conta.getSaldo(), 500.00);
                    System.out.printf("AVISO>Limite:%.2f;\nDIGITAR>Quanto De Empréstimo: ", negocios.getlimiteEmprestimo());
                    double val = sc.nextDouble();
                    negocios.emprestimo(val);
                    break;
            }
        }

        System.out.println();
        System.out.println("---FIM DA OPERACAO--");
        System.out.println("---BANCO DE DADOS--");
        for (Conta obj : list) {
            System.out.println(obj);
        }

        sc.close();
    }

    public static boolean contasRegistadas(List<Conta> list, Integer identificador) {
        Conta conta = list.stream().filter(x -> x.getidentificador() == identificador).findFirst().orElse(null);
        return conta != null;
    }
}
