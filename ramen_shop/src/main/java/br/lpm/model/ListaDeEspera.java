package br.lpm.model;  

import java.util.ArrayList;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Queue;

import br.lpm.utils.PedidoFormatter;  
import br.lpm.utils.PedidoNotifier;  

public class ListaDeEspera {  
    private static final ListaDeEspera INSTANCE = new ListaDeEspera();  
    private final Queue<Pedido> pedidosEmFila = new LinkedList<>();  
    private final List<Pedido> pedidosConcluidos;  
    private PedidoNotifier notifier = new PedidoNotifier();   
    private int contadorPedidos = 0; 

    private ListaDeEspera() {  
        pedidosConcluidos = new ArrayList<>();  
    }  

    public static ListaDeEspera getInstance() {  
        return INSTANCE;  
    }  

    public void adicionarPedido(Pedido pedido) {  
        contadorPedidos++; 
        System.out.println("Pedido #" + contadorPedidos + " adicionado: " + PedidoFormatter.formatarPedido(pedido));  
        pedidosEmFila.add(pedido);  
    }  

    public void exibirFila() {  
        System.out.println("\n=== Fila de Pedidos ===");  
        if (pedidosEmFila.isEmpty()) {  
            System.out.println("Nenhum pedido na fila.");  
        } else {  
            int numeroPedido = 1; 
            for (Pedido pedido : pedidosEmFila) {  
                System.out.println("Pedido #" + numeroPedido + ": " + 
                PedidoFormatter.formatarPedido(pedido) + " | Preço total: R$ "  
                        + String.format("%.2f", pedido.calcularPrecoTotal()));  
                numeroPedido++;  
            }  
        }  
    }   

    public void processarProximoPedido() {  
        Pedido pedido = pedidosEmFila.poll();  
        if (pedido != null) {  
            pedidosConcluidos.add(pedido);  
            System.out.println("\n=== Preparando o Pedido ===");  
            System.out.println(PedidoFormatter.formatarPedido(pedido));  
            notifier.notificarCliente(pedido); 
        } else {  
            System.out.println("Nenhum pedido na fila.");  
        }  
    }      

    public void marcarPedidoComoRetirado(Pedido pedido) {  
        if (pedidosConcluidos.contains(pedido)) {  
            System.out.println("\nPedido já retirado: " + PedidoFormatter.formatarPedido(pedido));  
        } else if (removerDaFilaParaConcluido(pedido)) {  
            System.out.println("\nPedido marcado como retirado: " + PedidoFormatter.formatarPedido(pedido));  
        } else {  
            System.out.println("Pedido não encontrado na fila de espera.");  
        }  
    }  

    public void processarPedidos() {  
        while (!pedidosEmFila.isEmpty()) {  
            Pedido pedido = pedidosEmFila.poll();  
            pedidosConcluidos.add(pedido);  
            System.out.println("\nPedido processado: " + PedidoFormatter.formatarPedido(pedido));  
        }  
    }  

    private boolean removerDaFilaParaConcluido(Pedido pedido) {  
        if (pedidosEmFila.remove(pedido)) {  
            pedidosConcluidos.add(pedido);  
            return true;  
        }  
        return false;  
    }  

    public Queue<Pedido> getPedidosEmFila() {  
        return pedidosEmFila;  
    }  

    public List<Pedido> getPedidosConcluidos() {  
        return pedidosConcluidos;  
    }  

    public void limparPedidos() {  
        pedidosConcluidos.clear();  
        System.out.println("\n=== Pedidos Limpos ===");  
        System.out.println("Todos os pedidos concluídos foram removidos.");  
    }  
}  