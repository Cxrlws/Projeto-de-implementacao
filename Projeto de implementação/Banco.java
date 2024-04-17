import java.util.ArrayList;

public class Banco {
    private int agencia;
    private int senhaGerente;
    private ArrayList<Cliente> clientes;

    public Banco(int agencia, int senhaGerente){
        //Credenciais da gerencia predefinidas
        this.agencia = 777;
        this.senhaGerente = 123;
        this.clientes = new ArrayList<>();
    }

    //Verifica existencia do cliente no array
    public boolean verificarCliente(int numConta){
        for(Cliente cliente : clientes)
        //Comparação de credenciais
            if(cliente.getConta().getNumero() == numConta){
                return true;
        }
        return false;
    }

    //Verifica as credenciais da gerencia
    public boolean verificarGerente(int identificacao, int senha){
        if(identificacao == agencia && senha == senhaGerente){
            return true;
        }
        return false;
    }
    
    //Adiciona um novo cliente ao array de clientes
    public boolean cadastrarCliente(Cliente novo){
        clientes.add(novo);
        return true;
    }

    //Remove um cliente do array de clientes
    public boolean excluirCliente(long cpf){
         for(Cliente cliente : clientes){
            if(cliente.getCpf() == cpf){
                clientes.remove(cliente);
                return true;
            }
        } 
        return false;
    }

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }   

    public int getAgencia(){
        return agencia;
    }

    public int getTotalClientes(){
        return clientes.size();
    }

    public float getSaldoBanco(){
        float total = 0;
        for(Cliente Cliente : clientes){
            total += Cliente.getConta().getSaldo();
        }
        return total;
    }

}
