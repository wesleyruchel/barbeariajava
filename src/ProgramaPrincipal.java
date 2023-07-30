class ProgramaPrincipal {

    public static void main(String[] args) {

        int numeroBarbeiros, numeroCadeiras;

        numeroBarbeiros = 2;
        numeroCadeiras = 7;

        Barbearia barbearia = new Barbearia(numeroBarbeiros, numeroCadeiras);

        for (int i = 1; i <= numeroBarbeiros; i++) {
            Barbeiro barbeiro = new Barbeiro(barbearia, i);
            barbeiro.start();
        }

        GeraClientes clientes = new GeraClientes(barbearia);
        clientes.start();
    }
}