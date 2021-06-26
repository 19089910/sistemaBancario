package entidades;

public class Conta {

    private Integer identificador;
    private String titular;
    protected Double saldo;

    public Conta() {
    }

    public Conta(Integer identificador, String titular, Double saldo) {
        this.identificador = identificador;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Integer getidentificador() {
        return identificador;
    }

    public void setidentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void saque(double valor) {
        saldo -= valor;
    }

    public void deposito(double valor) {
        saldo += valor;
    }

    @Override
    public String toString() {
        return  identificador +
                ", " + titular +
                ", " + String.format("%.2f",saldo);
    }
}