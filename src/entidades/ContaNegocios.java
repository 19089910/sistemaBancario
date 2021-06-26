package entidades;

public class ContaNegocios extends Conta {
    private Double limiteEmprestimo;

    public ContaNegocios() {
        super();
    }

    public ContaNegocios(Integer identificador, String titular, Double saldo, Double limiteEmprestimo) {
        super(identificador, titular, saldo);
        this.limiteEmprestimo = limiteEmprestimo;
    }

    public Double getlimiteEmprestimo() {
        return limiteEmprestimo;
    }

    public void setLoanLimit(Double limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }

    public void emprestimo(double valor) {
        if (valor <= limiteEmprestimo) {
            deposito(valor);
        } else{
            System.out.println("execeu o limite do emprestimo");
        }
    }
}
