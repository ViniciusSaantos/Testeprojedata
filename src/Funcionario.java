import java.math.BigDecimal;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aplicarAumento(BigDecimal aumentoPercentual) {
        BigDecimal multiplicador = BigDecimal.ONE.add(aumentoPercentual.divide(BigDecimal.valueOf(100)));
        this.salario = this.salario.multiply(multiplicador);
    }

    public String getSalarioFormatado() {
        return String.format("%.2f", salario);
    }
}
