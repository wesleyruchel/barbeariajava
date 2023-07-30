import java.util.LinkedList;
import java.util.List;

class Barbearia {

    private final int numeroCadeiras, numeroBarbeiros;
    private int barbeirosDisponiveis;
    private List<Cliente> listaClientes;

    public Barbearia(int numeroBarbeiros, int numeroCadeiras) {
        this.numeroCadeiras = numeroCadeiras;
        listaClientes = new LinkedList<>();
        this.numeroBarbeiros = numeroBarbeiros;
        barbeirosDisponiveis = this.numeroBarbeiros;
    }

    public void cortarCabelo(int barbeiroId) {
        Cliente cliente;

        synchronized (listaClientes) {
            while (listaClientes.isEmpty()) {
                System.out.println("O barbeiro " + barbeiroId
                        + " está esperando por clientes.");
                try {
                    listaClientes.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            cliente = (Cliente) ((LinkedList<?>) listaClientes).poll();

            System.out.println("O barbeiro " + barbeiroId
                    + " encontrou o cliente " + cliente.getClienteId()
                    + " na fila.");
        }
        long duracao = 0;
        try {
            barbeirosDisponiveis--;

            System.out.println("O barbeiro " + barbeiroId
                    + " está cortanto o cabelo do cliente "
                    + cliente.getClienteId() + ".");

            duracao = (long) (Math.random() * 1000);
            Thread.sleep(duracao);

            barbeirosDisponiveis++;
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("O barbeiro " + barbeiroId
                + " terminou de cortar o cabelo do cliente "
                + cliente.getClienteId() + " em " + duracao
                + " milissegundos.");
    }

    public void adicionarCliente(Cliente cliente) {
        System.out.println("Cliente " + cliente.getClienteId()
                + " acabou de chegar.");

        synchronized (listaClientes) {
            if (listaClientes.size() == numeroCadeiras) {
                System.out.println("Nenhuma cadeira disponível.");
                System.out.println("O cliente " + cliente.getClienteId()
                        + " deixou a barbearia.");
            } else if (barbeirosDisponiveis > 0) {
                ((LinkedList<Cliente>) listaClientes).offer(cliente);
                listaClientes.notify();
            } else {
                ((LinkedList<Cliente>) listaClientes).offer(cliente);

                System.out.println("Todos os barbeiros estão ocupados."
                        + " O cliente " + cliente.getClienteId()
                        + " sentou-se na sala de espera.");

                if (listaClientes.size() == 1) {
                    listaClientes.notify();
                }
            }
        }
    }
}
