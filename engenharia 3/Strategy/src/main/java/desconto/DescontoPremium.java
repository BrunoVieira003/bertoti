package desconto;

public class DescontoPremium implements DescontoStrategy{
    @Override
    public double aplicar(double valor) {
        return valor * 0.7;
    }

    @Override
    public String getNomeDesconto() {
        return "Premium";
    }
}
