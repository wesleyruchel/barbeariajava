class GeraClientes extends Thread {

    private Barbearia barbearia;

    public GeraClientes(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = new Cliente(barbearia);
            cliente.start();
            cliente.setClienteId(cliente.getId());

            try {
                // Cria um intervalo na geração (chegada) dos clientes...
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
