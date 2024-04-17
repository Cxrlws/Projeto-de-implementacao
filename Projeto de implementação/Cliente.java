public class Cliente {
    private String nome;
    private long cpf;
    private Conta conta;

    public Cliente(String nome, long cpf, Conta conta){
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
    }
    
    public String getNome(){
        return nome;
    }

    public long getCpf(){
        return cpf;
    }

    public Conta getConta(){
        return conta;
    }

}
