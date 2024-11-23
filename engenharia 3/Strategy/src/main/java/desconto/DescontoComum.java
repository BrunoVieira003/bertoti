package desconto;

public class DescontoComum implements DescontoStrategy{
    @Override
    public double aplicar(double valor) {
        return valor;
    }

    @Override
    public String getNome() {
        return "Comum";
    }
}
