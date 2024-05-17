package com.mycompany.sistemahotel;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
        

public class suites {

    private static Map<Integer, Reserva> reservas = new HashMap<>();

    public static void ReservaDeQuartos() {
        while (true) {
            System.out.println();
            System.out.println("---- Reserva de Quartos ----");
            System.out.println();
            System.out.println("1 - Exibir Quartos Disponiveis.");
            System.out.println("2 - Escolher quarto.");
            System.out.println("3 - Voltar");
            System.out.println();
            var escolha = lerDados.lerInt();

            switch (escolha) {
                case 1:
                    exibirReserva();
                    break;
                case 2:
                    fazerReserva();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Numero invalido. Tente novamente");
            }
        }
    }

    public static void exibirReserva() {
        System.out.println("----- Reservas de Quartos -----");
        for (int i = 1; i <= 40; i++) {
            if (reservas.containsKey(i)) {
                System.out.println("Quarto " + i + ": Ocupado por " + reservas.get(i).nome());
            } else {
                System.out.println("Quarto " + i + ": Disponivel");
            }
        }
    }

    public static void fazerReserva() {
        
        System.out.println("---- Escolha de Quartos ----");
        System.out.println("Qual seu nome?");
        String nome = lerDados.lerTexto();
        System.out.println("Digite seu CPF:");
        var cpf = lerDados.lerTexto();
        System.out.println();

        while (true) {
            System.out.println("Qual quarto deseja reservar?");
            int numeroQuarto = lerDados.lerInt();

            if (numeroQuarto < 1 || numeroQuarto > 40) {
                System.out.println("Numero de quarto invalido. Por favor, digite um numero de 1 a 40.");
            } else if (reservas.containsKey(numeroQuarto)) {
                System.out.println("Quarto ocupado por " + reservas.get(numeroQuarto).nome());
            } else {
                System.out.println();
                System.out.println("Data de Check-in: ");
                LocalDate checkIn = lerDados.lerData();
                System.out.println("Data de Check-out: ");
                LocalDate checkOut = lerDados.lerData();
                System.out.println("---- Quarto Reservado ----");
                System.out.println("Quarto " + numeroQuarto + " reservado para " + nome);
                
                String dataCheckInStr = checkIn.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                String dataCheckOutStr = checkOut.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                
                Reserva reserva = new Reserva(nome, dataCheckInStr, dataCheckOutStr);
                reservas.put(numeroQuarto, reserva);
                break;
            }
        }
    }

   public record Reserva(String nome, String checkIn, String checkOut){}
    
    public static void lista1() {
    System.out.println("----- Reservas de Quartos -----");
     System.out.println();
    for (Map.Entry<Integer, Reserva> reserva : reservas.entrySet()) {
        Reserva reservaEX = reserva.getValue();
        System.out.println("Quarto numero " + reserva.getKey() + " ocupado por " + reservaEX.nome() +
                ", Check-in: " + reservaEX.checkIn() + ", Check-out: " + reservaEX.checkOut());
    }
    }
 public static void cancelarReserva(){
        
        System.out.println("---- Cancelar Reserva ----");
        System.out.println();
        System.out.println("Qual o numero do quarto que deseja cancelar?");
        var numeroQuarto = lerDados.lerInt();
        if (reservas.containsKey(numeroQuarto)) {
        reservas.remove(numeroQuarto);
        System.out.println("---- Reserva Cancelada ----");
    } else {
        System.out.println("Reserva não encontrada.");
    }
}
}
