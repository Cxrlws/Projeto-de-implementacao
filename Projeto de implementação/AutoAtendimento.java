public class AutoAtendimento{
    private Banco banco;
    private Cliente usuario;

    public AutoAtendimento(Banco banco){
        this.banco = banco;
    }

    //Login do cliente
    public boolean loginCliente(int numConta, int senha){
        //Verifica a existencia do cliente no array
        if(banco.verificarCliente(numConta)){
            for(Cliente Cliente : banco.getClientes()){
                //Compara as credenciais inseridas com as definidas pela gerencia
                if(Cliente.getConta().getNumero() == numConta && Cliente.getConta().getSenha() == senha){
                    //Define o cliente como o usuario ativo
                    usuario = Cliente;
                    return true;
                }
            }
        }
        return false;
    }

    //Login da gerencia
    public boolean loginGerente(int numAgencia, int senha){
        //Verifica credenciais da gerencia
        if(banco.verificarGerente(numAgencia, senha)){
            return true;
        }
        return false;
    }

    //Verifica a existencia do cliente (atravé do CPF)
    public boolean clienteExistente(long cpf){
        for(Cliente Cliente : banco.getClientes()){
            //Compara o CPF inserido com o já definido
            if(Cliente.getCpf() == cpf){
                return true;
            }
        }
        return false;
    }

    //Cadastro de novos clientes
    public void gerenteCadastrarCliente(Cliente cliente){
        banco.cadastrarCliente(cliente);

    }

    //Exclusão de clientes
    public void gerenteExcluirCliente(long cpf){
        banco.excluirCliente(cpf);
    }

    //Verifica credencial do usuário (senha)
    public boolean senhaAcesso(int senha){
        //Verifica a senha inserida com a senha já definida
        if(usuario.getConta().getSenha() == senha){
            return true;
        }
        return false;
    }

    //Permisão de saque
    public boolean clienteSacar(float valor, int senha){
        if(senhaAcesso(senha) && usuario.getConta().sacar(valor)){
            return true;
        }
        return false;
    }

    //Permissão de depósito
    public boolean clienteDepositar(float valor){
        if(usuario.getConta().depositar(valor)){
            return true;
        }
        return false;
    }

    //Verifica saldo
    public float clienteVerificarSaldo(){
            return usuario.getConta().getSaldo();
        
        }

    public String getNomeCliente(){
        return usuario.getNome();
    }





    
}