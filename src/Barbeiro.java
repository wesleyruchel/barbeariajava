class Barbeiro extends Thread {

    private Barbearia barbearia;
    private int barbeiroId;

    public Barbeiro(Barbearia barbearia, int barbeiroId) {
        this.barbearia = barbearia;
        this.barbeiroId = barbeiroId;
    }

    @Override
    public void run() {
        try {
            System.out.println("O barbeiro " + barbeiroId
                    + " está dormindo...");
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        while (true) {
            barbearia.cortarCabelo(barbeiroId);
        }
    }
}
