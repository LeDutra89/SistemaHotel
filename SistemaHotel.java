package com.mycompany.sistemahotel;
import java.util.ArrayList;
import java.time.LocalDate;


public class SistemaHotel {
    
    private static ArrayList<Hospede> hospedes = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("==== Menu Principal ====");
            System.out.println();
            System.out.println("1 - Cadastrar hospede");
            System.out.println("2 - Fazer Reserva");
            System.out.println("3 - Listar Hospedes");
            System.out.println("4 - Listar Reservas");
            System.out.println("5 - Cancelar Reserva");
            System.out.println("6 - Sair");
            System.out.println();
            System.out.println("Escolha uma das opções:");
            var escolha = lerDados.lerInt();

            switch (escolha) {
                case 1:
                    cadastrarHospede();
                    break;
                case 2:
                    suites.fazerReserva();
                    break;
                case 3:
                    listarHospedes();
                    break;
                case 4:
                    suites.lista1();
                    break;
                case 5:
                     suites.cancelarReserva();
                    break;
                 case 6:
                      return;
                default:
                    System.out.println("tente novamente.");
                    break;
            }
        }
    } 
    
    private static void cadastrarHospede() {     
                
        System.out.println("Nome: ");
        String nome = lerDados.lerTexto();

        System.out.println("Data de nascimento: ");
        LocalDate dataDeNascimento = lerDados.lerData();
        
        System.out.println("CPF/Passaporte/RNE (Digite somente números): ");
        int cpfPassaporteRne = lerDados.lerInt();

        System.out.println("Nacionalidade: ");
        String nacionalidade = lerDados.lerTexto();

        System.out.println("Endereço: ");
        String endereco = lerDados.lerTexto();

        System.out.println("Número: ");
        int numeroEndereco = lerDados.lerInt();

        System.out.println("Complemento: ");
        String complementoEndereco = lerDados.lerTexto();

        System.out.println("CEP (Digite somente números): ");
        String cep = lerDados.lerTexto();

        Hospede novoHospede = new Hospede(nome, dataDeNascimento, String.valueOf(cpfPassaporteRne), nacionalidade, endereco, numeroEndereco, complementoEndereco, cep);
        hospedes.add(novoHospede);

        System.out.println("Hospede cadastrado com sucesso!");
    }
    
    public record Hospede (
        String nome,
        LocalDate dataDeNascimento,
        String cpfPassaporteRne,
        String nacionalidade,
        String endereco,
        int numero,
        String complemento,
        String cep
    ){
        public String dataDeNascimentoString() {
            return dataDeNascimento.format(lerDados.DATA);
        }
        
       public String cpfPassaporteRne() {
            // Verifica se é um CPF
            if (cpfPassaporteRne.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) {
                return formatarCPF();
            }
            
            return cpfPassaporteRne; // Retorna o documento sem formatação, se não for CPF
        }
       
        public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); // Remover caracteres não numéricos
        return cpf.length() == 11; // Retorna verdadeiro se o CPF tiver 11 dígitos
    }
        // Método para formatar o CPF
        private String formatarCPF() {
            // Verifica se o CPF possui a máscara padrão
            if (cpfPassaporteRne.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) {
                return cpfPassaporteRne; // Se já estiver formatado, retorna o CPF sem alterações
            } else {
                // Adiciona a máscara padrão ao CPF
                return String.format("%s.%s.%s-%s", 
                                     cpfPassaporteRne.substring(0, 3), 
                                     cpfPassaporteRne.substring(3, 6), 
                                     cpfPassaporteRne.substring(6, 9), 
                                     cpfPassaporteRne.substring(9));
            }
        }
}


    private static void listarHospedes() {
        if (hospedes.isEmpty()) {
            System.out.println("Não há hóspedes cadastrados.");
        } else {
            System.out.println("Lista de Hóspedes:");
            for (Hospede hospede : hospedes) {
                System.out.println("Nome: " + hospede.nome());
                System.out.println("Data de Nascimento: " + hospede.dataDeNascimentoString());
                System.out.println("CPF/Passaporte/RNE: " + hospede.formatarCPF());
                System.out.println("Nacionalidade: " + hospede.nacionalidade());
                System.out.println("Endereço: " + hospede.endereco());
                System.out.println("Número: " + hospede.numero());
                System.out.println("Complemento: " + hospede.complemento());
                System.out.println("CEP: " + hospede.cep());
                System.out.println();
            }
        }
    }
}
