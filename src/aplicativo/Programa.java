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
        //--DECLARACAO DA LISTA <CONTA>--
        List<Conta> list = new ArrayList<>();

        System.out.println("   ---SISTEMA BANCARIO---   ");
        System.out.println();
        System.out.println("---REGISTRAR CONTAS NO BANCO---");
        System.out.print("DIGITAR>Quantas contas serão registradas?: ");
        int n = sc.nextInt();

        //--SET.DADOS--
        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.println("Conta Bancaria  #" + i + ": ");
            System.out.print("DIGITAR>Numero identificador da Conta: ");
            int identificador = sc.nextInt();

            //--FILTRO IDENTIFICADOR DE CONTAS REPETIDAS--
            while (contasRegistadas(list, identificador)) {
                System.out.print("DIGITAR>Essa conta já esta em uso. Tente novamente: ");
                identificador = sc.nextInt();
            }

            System.out.print("DIGITAR>Nome do Titular: ");
            sc.nextLine();
            String titular = sc.nextLine();
            System.out.print("DIGITAR>Saldo Atual do Titular: ");
            double saldo = sc.nextDouble();
            list.add(new Conta(identificador, titular, saldo));
        }

        char resp;
        do {
            System.out.println();
            System.out.println("---SISTEMA BANCARIO---");
            System.out.print("DIGITAR>Sua Conta com Seu Identificador: ");
            int identificador = sc.nextInt();

            /*
            ---VALIDACAO DE BUSCA SE EXISTE OU NAO NA LISTA---
            BUSCANDO NA LISTA A PRIMEIRA OCORRENCIA DE UMA CONTA QUE TENHA O IDENTIFICADOR DIGIRADO
            EXPRESSAO LAMBIDA(LISTA=STREAM LER FILTRO(SO AS CONTAS QUE TENHA IDENTIFICADOR).=1ªEXMPLO.SE NULL RETORNE NULL)
            */
            Conta conta = list.stream().filter(x -> x.getidentificador() == identificador).findFirst().orElse(null);
            if (conta == null) {
                System.out.println("AVISO>Esta conta  não existe!");
                System.out.println();
                System.out.println("SAINDO DO SISTEMA>");
            } else {
                //OPERADORES BANCARIOS

                System.out.printf("ID>%d%n", identificador);
                System.out.println("Opções:\n1.Saque\n2.Deposito\n3.Emprestimo");
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
            System.out.println("Quer Continuar a Modificar Algum Dado?(s/n)");
            resp = sc.next().charAt(0);
        } while (resp != n);

        //--BANCO DE DADOS DA LISTA BANCARIA--
        System.out.println();
        System.out.println("---FIM DA OPERAÇÂO--");
        System.out.println("---BANCO DE DADOS--");
        for (Conta obj : list) {
            System.out.println(obj);
        }

        sc.close();
    }

    /*
    ---VALIDACAO DE BUSCA SE EXISTE OU NAO NA LISTA FORA DA MAIN---
    BUSCANDO NA LISTA A PRIMEIRA OCORRENCIA DE UMA CONTA QUE TENHA O IDENTIFICADOR DIGIRADO;
    EXPRESSAO LAMBIDA(LISTA=STREAM LER FILTRO(SO AS CONTAS QUE TENHA IDENTIFICADOR).=1ªEXMPLO.SE NULL RETORNE NULL);
    */
    public static boolean contasRegistadas(List<Conta> list, Integer identificador) {
        Conta conta = list.stream().filter(x -> x.getidentificador() == identificador).findFirst().orElse(null);
        return conta != null;//RETURN DIFERENTE DE NULL
    }
}
