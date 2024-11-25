package br.lpm.utils;


import br.lpm.core.Pedido;

public class PedidoFormatter {
    public static String formatarPedido(Pedido pedido) {
        String detalhes = pedido.exibirDetalhes();
        double precoTotal = pedido.calcularPrecoTotal();
        return String.format(" %s| Preço total: R$ %.2f", detalhes, precoTotal);
    }
}