class Cliente extends Thread {

    private Barbearia barbearia;
    private long clienteId;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    private void irCortarCabelo() {
        barbearia.adicionarCliente(this);
    }

    @Override
    public void run() {
        irCortarCabelo();
    }
}
