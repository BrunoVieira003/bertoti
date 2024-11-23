package desconto;

public class SemDesconto implements DescontoStrategy{
    @Override
    public double aplicar(double valor) {
        return valor;
    }

    @Override
    public String getNomeDesconto() {
        return "Nenhum";
    }
}
