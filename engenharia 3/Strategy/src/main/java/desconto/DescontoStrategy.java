package desconto;

public interface DescontoStrategy {
    double aplicar(double valor);

    String getNome();
}
