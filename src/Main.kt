import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 08), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 09, 02), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com suas informações formatadas
        System.out.println("Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                funcionario.getNome(),
                funcionario.getDataNascimentoFormatada(),
                funcionario.getSalarioFormatado(),
                funcionario.getFuncao());
        });

        // 3.4 - Aplicar aumento de 10% no salário dos funcionários
        BigDecimal aumentoPercentual = new BigDecimal("10.00");
        funcionarios.forEach(funcionario -> funcionario.aplicarAumento(aumentoPercentual));

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        funcionarios.forEach(funcionario -> {
            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), key -> new ArrayList<>()).add(funcionario);
        });

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> {
            System.out.printf("Nome: %s, Salário: %s%n", f.getNome(), f.getSalarioFormatado());
        });
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("Funcionários que fazem aniversário em outubro (10) e dezembro (12):");
        funcionarios.stream()
            .filter(funcionario ->
        funcionario.getDataNascimento().getMonthValue() == 10 ||
                funcionario.getDataNascimento().getMonthValue() == 12)
        .forEach(funcionario ->
        System.out.printf("Nome: %s, Data de Nascimento: %s%n",
            funcionario.getNome(), funcionario.getDataNascimentoFormatada()));

        // 3.9 - Encontrar o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
            .min((f1, f2) ->
        f1.getDataNascimento().compareTo(f2.getDataNascimento()))
        .orElse(null);
        if (funcionarioMaisVelho != null) {
            LocalDate hoje = LocalDate.now();
            int idade = hoje.getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
            System.out.printf("Funcionário mais velho: %s, Idade: %d anos%n",
                funcionarioMaisVelho.getNome(), idade);
        }

        // 3.10 - Ordenar os funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
            .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
        .collect(Collectors.toList());
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionariosOrdenados.forEach(funcionario ->
        System.out.printf("Nome: %s, Salário: %s%n",
            funcionario.getNome(), funcionario.getSalarioFormatado()));

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários: %.2f%n", totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em múltiplos de salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal multiploSalarioMinimo = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.printf("Nome: %s, Salário: %s, Salários Mínimos: %.2f%n",
                funcionario.getNome(), funcionario.getSalarioFormatado(), multiploSalarioMinimo.doubleValue());
        });
    }
}
