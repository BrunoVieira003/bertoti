package desconto;

public class DescontoVIP implements DescontoStrategy{
    @Override
    public double aplicar(double valor) {
        return valor * 0.8;
    }

    @Override
    public String getNomeDesconto() {
        return "VIP";
    }
}
