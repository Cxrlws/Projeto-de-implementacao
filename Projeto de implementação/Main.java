
 /* Linguagem de Programação II
 * I Trabalho de implementação 
 * DISCENTES: Carlos Vinicius Lima Pereira e  Roberth Kaua Diniz Costa
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        int agencia = 0;
        int senhaLoginGerente = 0;

        Banco bancoLogin = new Banco(agencia, senhaLoginGerente);
        AutoAtendimento atendimento = new AutoAtendimento(bancoLogin);

        while(true){
            //Menu principal
            System.out.println("====== BANCO ======");
            System.out.printf("1 - SOU GERENTE\n" +
            "2 - SOU CLIENTE\n" +
            "3 - SAIR\n===================\n--> ");

            int opc = input.nextInt();

            //Verifica se existe usuario logado
            boolean usuarioSelecionado = true;
            
            switch (opc) {
                case 1:
                    if(!usuarioSelecionado){
                        break;
                    }

                    //Login do gerente
                    System.out.print("\033[H\033[2J");
                    System.out.println("====== BANCO ======");
                    System.out.println("AGENCIA: ");
                    agencia = input.nextInt();
                    System.out.println("SENHA: ");
                    senhaLoginGerente = input.nextInt();
                    System.out.print("\033[H\033[2J");

        
                    if(atendimento.loginGerente(agencia, senhaLoginGerente)){
                        while (usuarioSelecionado) {

                            //Menu do gerente
                            System.out.println("====== BEM VINDO, GERENTE ======");
                            System.out.println("1 - CADASTRAR CLIENTE: ");
                            System.out.println("2 - EXCLUIR CLIENTE: ");
                            System.out.println("3 - GERAR RELATÓRIO: ");
                            System.out.printf("4 - SAIR\n--> ");

                            int opcGerente = input.nextInt();
                            
                            switch(opcGerente){
                                case 1:
                                    //Cadastro de cliente
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("====== CADASTRO DE CLIENTE ======");

                                    System.out.printf("NOME:\n--> ");
                                    String nome = input.next();

                                    System.out.printf("CPF:\n--> ");
                                    long cpf = input.nextLong();

                                    System.out.printf("NUMERO CONTA:\n-->");
                                    int numConta = input.nextInt();

                                    System.out.printf("CRIE UMA SENHA:\n-->");
                                    int senhaCliente = input.nextInt();

                                    //Verifica se o cliente ja existe
                                    if(!atendimento.clienteExistente(cpf)){

                                        Conta clienteConta = new Conta(numConta, senhaCliente, 0);
                                        Cliente cliente = new Cliente(nome, cpf, clienteConta);
                                        atendimento.gerenteCadastrarCliente(cliente);

                                        System.out.print("\033[H\033[2J");
                                        System.out.println("CLIENTE CADASTRADO COM SUCESSSO!\n");
                                        break;
                                    }
                                    
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("CLIENTE EXISTENTE!\n");
                                    break;

                                case 2:
                                    //Exclusão de cliente
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("====== REMOVER CLIENTE ======");
                                    System.out.printf("CPF DO CLIENTE:\n--> ");
                                    cpf = input.nextLong();

                                    //Verifica a existencia do cliente
                                    if(atendimento.clienteExistente(cpf)){
                                        atendimento.gerenteExcluirCliente(cpf);
                                        System.out.print("\033[H\033[2J");
                                        System.out.println("CLIENTE EXCLUIDO COM SUCESSO!\n");

                                    }else{
                                        System.out.print("\033[H\033[2J");
                                        System.out.println("CLIENTE NÃO EXISTENTE!\n");
                                        break;
                                    }
                                    break;
                                case 3:
                                    
                                    //Relatório do banco
                                    System.out.print("\033[H\033[2J");
                                    System.out.printf("===== RELATÓRIO DA AGÊNCIA %d DO BANCO =====\n\n", bancoLogin.getAgencia());
                                    for(Cliente Cliente : bancoLogin.getClientes()){
                                        System.out.printf("%s R$ %.2f\n\n", Cliente.getNome(), Cliente.getConta().getSaldo());
                                    }
                                    System.out.printf("Total de clientes: %d\n", bancoLogin.getTotalClientes());
                                    System.out.printf("Saldo total: %.2f\n\n", bancoLogin.getSaldoBanco());
                                
                                    break;
                                case 4:
                                    //Retorno ao menu principal
                                    usuarioSelecionado = false;
                                    System.out.print("\033[H\033[2J");
                                    break;
                                default:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!\n");
                                    break;
                                }
                        
                        }

                    } else {
                        System.out.println("VOCê NÃO É UM GERENTE!\n");
                        break;
                    }
                    break;
            
                case 2:
                    if(!usuarioSelecionado){
                        break;
                    }
                    //Menu cliene
                    System.out.print("\033[H\033[2J");
                    System.out.println("====== FAÇA SEU LOGIN ======");

                    System.out.println("CONTA: ");
                    int contaCliente = input.nextInt();

                    System.out.println("SENHA: ");
                    int senhaLoginCliente = input.nextInt();

                    System.out.print("\033[H\033[2J");

                    //Verifica credenciais do cliente
                    if(atendimento.loginCliente(contaCliente, senhaLoginCliente)){
                        while(usuarioSelecionado){
                            
                            //Menu do cliente
                            System.out.printf("====== BEM VINDO, %s ======\n",atendimento.getNomeCliente());
                            System.out.println("1 - SACAR: ");
                            System.out.println("2 - DEPOSITAR: ");
                            System.out.println("3 - VERIFICAR SALDO: ");
                            System.out.println("4 - SAIR ");

                            int opcCliente = input.nextInt();

                            switch(opcCliente){
                                case 1:
                                    //Menu de saque
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("========== SAQUE ==========");
                                    System.out.printf("Digite o valor: R$ ");
                                    float valorSaque = input.nextFloat();
                                    System.out.printf("Digite sua senha: ");
                                    int senhaSaque = input.nextInt();

                                    //Verificação de credencial do cliente
                                    if(atendimento.senhaAcesso(senhaSaque)){
                                         if(atendimento.clienteSacar(valorSaque, senhaSaque)){
                                            System.out.print("\033[H\033[2J");
                                            System.out.printf("Saque realizado!\nSaldo restante: R$ %.2f\n",atendimento.clienteVerificarSaldo());
                                            break;
                                         } else {
                                            System.out.print("\033[H\033[2J");
                                            System.out.println("Fundos insuficientes!\n");
                                            break;
                                         }
                                    } else {
                                        System.out.print("\033[H\033[2J");
                                        System.out.println("Senha incorreta!\n");
                                        break;
                                    }
                                case 2:
                                    //Menu de depósito
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("========== DEPÓSITO ==========");
                                    System.out.printf("Digite o valor: R$ ");
                                    float valorDeposito = input.nextFloat();

                                    //Verifiação de valores
                                    if(atendimento.clienteDepositar(valorDeposito)){
                                        System.out.print("\033[H\033[2J");
                                        System.out.printf("Você depositou R$ %.2f\n",valorDeposito);
                                        break;
                                    } else {
                                        System.out.print("\033[H\033[2J");
                                        System.out.printf("O valor deve ser superior a R$ 0,00\n");
                                        break;

                                    }



                                case 3:
                                    //Menu de consulta ao saldo
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("========== SALDO ==========");
                                    System.out.printf("Insira sua senha: ");
                                    int senhaConsulta = input.nextInt();

                                    //Verificação de credencial
                                    if(atendimento.senhaAcesso(senhaConsulta)){
                                        System.out.print("\033[H\033[2J");
                                        System.out.printf("Seu saldo total é: R$ %.2f\n", atendimento.clienteVerificarSaldo());
                                        break;
                                    } else {
                                        System.out.print("\033[H\033[2J");
                                        System.out.println("Senha incorreta!\n");
                                    break;
                                    }
                                    
                                case 4:
                                    //Retorno ao menu principal
                                    usuarioSelecionado = false;
                                    System.out.print("\033[H\033[2J");
                                    break;
                                default:

                                    System.out.print("\033[H\033[2J");
                                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!\n");
                                    break;
                            }
                        }

                    }else{

                        System.out.println("VOCÊ NÃO ESTÁ CADASTRADO, CONSULTE O GERENTE!\n");
                        break;
                    }
                    
                    break;
                case 3:
                    System.exit(1);
                default:

                    System.out.print("\033[H\033[2J");
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!\n");
            }
        }


    }

}
