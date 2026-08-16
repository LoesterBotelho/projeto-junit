package br.com.exemplo.rh;

public class Main {
    public static void main(String[] args) {
    	System.out.println("----------------------------------------------------------------");
        System.out.println("RH - FOLHA DE PAGAMENTO");
        System.out.println("----------------------------------------------------------------");
        
        Funcionario func = new Funcionario("Carlos Silva", "12345678901", 5000.00, 1);
        Provento prov = new Provento(500.00, 200.00);
        Desconto desc = new Desconto(100.00, 200.00);
        
        Holerith holerith = new Holerith(func, prov, desc, 150.00);
        
        System.out.println("Funcionário: " + func.getNome());
        System.out.println("Salário Bruto: R$ " + String.format("%.2f", holerith.getSalarioBruto()));
        System.out.println("(-) INSS: R$ " + String.format("%.2f", holerith.getValorINSS()));
        System.out.println("(-) IRRF: R$ " + String.format("%.2f", holerith.getValorIRRF()));
        System.out.println("(-) Vale Transporte: R$ " + String.format("%.2f", holerith.getValorValeTransporte()));
        System.out.println("(-) Outros Descontos: R$ " + String.format("%.2f", desc.getTotalOutrosDescontos()));
        System.out.println("----------------------------------------------------------------");
        System.out.println("Salário Líquido: R$ " + String.format("%.2f", holerith.getSalarioLiquido()));
        System.out.println("----------------------------------------------------------------");
    }
}