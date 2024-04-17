public class Conta {
    private int numero;
    private float saldo;
    private int senha;

    public Conta(int numero, int senha, float saldo){
        this.numero = numero;
        this.saldo = saldo;
        this.senha = senha;
    }

    public int getSenha(){
        return senha;
    }

    public int getNumero(){
        return numero;
    }

    public float getSaldo(){
        return saldo;
    }
    
    public boolean depositar(float valor){
        if(valor > 0){
            saldo += valor;
            return true;
        }
        return false;
    }

    public boolean sacar(float valor){
        if(saldo >= valor){
            saldo -= valor;
            return true;
        }
        return false;
        
    }
}
