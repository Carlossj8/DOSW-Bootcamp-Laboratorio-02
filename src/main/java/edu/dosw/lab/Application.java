package edu.dosw.lab;

import edu.dosw.lab.creacionales.reto2.SastreAMedida;
import edu.dosw.lab.creacionales.reto3.FabricaDeInstrumentos;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println(" DOSW - Hackathon Laboratorio 02");
        System.out.println("========================================");
        System.out.println("¿Qué reto deseas ejecutar?");
        System.out.println("1. Reto 1 - La Boletería del Cine Astor");
        System.out.println("2. Reto 2 - El Sastre a la Medida");
        System.out.println("3. Reto 3 - La Fábrica de Instrumentos");
        System.out.println("4. Reto 4 - La Balanza Trucada del Mercado");
        System.out.println("5. Reto 5 - La Moto Personalizada");
        System.out.println("6. Reto 6 - Sala de Urgencias");
        System.out.println("7. Reto 7 - El Rover Explorador de Marte");
        System.out.println("8. Reto 8 - La Academia de Fútbol");
        System.out.println("0. Salir");
        System.out.print("Opción: ");

        String opcion = sc.nextLine().trim();

        switch (opcion) {
            case "1":
                // Reto1BoleteriaAstor.ejecutar();
                System.out.println("Reto 1 aún no está conectado aquí.");
                break;
            case "2":
                SastreAMedida.ejecutar();
                break;
            case "3":
                FabricaDeInstrumentos.ejecutar();
                break;
            case "4":
                System.out.println("Reto 4 aún no implementado.");
                break;
            case "5":
                System.out.println("Reto 5 aún no implementado.");
                break;
            case "6":
                System.out.println("Reto 6 aún no implementado.");
                break;
            case "7":
                System.out.println("Reto 7 aún no implementado.");
                break;
            case "8":
                System.out.println("Reto 8 aún no implementado.");
                break;
            case "0":
                System.out.println("Hasta pronto.");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}