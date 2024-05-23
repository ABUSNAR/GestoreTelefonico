import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GestoreTelefonico gestore = new GestoreTelefonico(10);
        Scanner ali = new Scanner(System.in);

// Per prima cosa tramite il metodo SetPassword impostiamo la password scelta e tramite essa faremo le ulterwiori verifiche

        System.out.print("Imposta una password per accedere ai contatti nascosti: ");
        String password = ali.nextLine();
        gestore.setPassword(password);
        // elenchiamo tutte le opzioni, invocando adf ognuna il proprio metodo dalla classe "GestoreTelefonico"

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Aggiungi nuovo contatto");
            System.out.println("2. Visualizza contatti");
            System.out.println("3. Sposta contatto nella sezione nascosta");
            System.out.println("4. Elimina contatto");
            System.out.println("5. Visualizza contatti nascosti");
            System.out.println("6. Aggiungi chiamata recente");
            System.out.println("7. Visualizza chiamate recenti");
            System.out.println("8. Gestisci chiamata recente");
            System.out.println("9. Visualizza chiamate nascoste");
            System.out.println("10. Riporta contatto nascosto in rubrica");
            System.out.println("11. Riporta chiamata nascosta nelle recenti");
            System.out.println("12. Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = ali.nextInt();
            ali.nextLine();

            switch (scelta) {

                case 1:
                    System.out.print("Inserisci nome: ");
                    String nome = ali.nextLine();
                    System.out.print("Inserisci cognome: ");
                    String cognome = ali.nextLine();
                    System.out.print("Inserisci numero telefonico: ");
                    String numeroTelefonico = ali.nextLine();
                    gestore.aggiungiContatto(nome, cognome, numeroTelefonico);
                    break;
                // in caso se non ci sono contatti, il programma non eseguirà nessuna delle seguenti opzioni
                case 2:
                    System.out.println("\nContatti in rubrica:");
                    gestore.visualizzaContatti();
                    break;
                case 3:
                    System.out.print("Inserisci il numero telefonico del contatto da spostare nella sezione nascosta: ");
                    numeroTelefonico = ali.nextLine();
                    System.out.print("Inserisci la password: ");
                    String inputPassword = ali.nextLine();
                    gestore.spostaInNascosti(numeroTelefonico, inputPassword);
                    break;
                case 4:
                    System.out.print("Inserisci il numero telefonico del contatto da eliminare: ");
                    numeroTelefonico = ali.nextLine();
                    gestore.eliminaContatto(numeroTelefonico);
                    break;
                case 5:
                    System.out.print("Inserisci la password per visualizzare i contatti nascosti: ");
                    inputPassword = ali.nextLine();
                    System.out.println("\nContatti nascosti:");
                    gestore.visualizzaContattiNascosti(inputPassword);
                    break;
                case 6:
                    System.out.print("Inserisci il numero telefonico della chiamata recente: ");
                    numeroTelefonico = ali.nextLine();
                    gestore.aggiungiChiamataRecente(numeroTelefonico);
                    break;
                case 7:
                    System.out.println("\nChiamate recenti:");
                    gestore.visualizzaChiamateRecenti();
                    break;
                case 8:
                    System.out.print("Inserisci l'indice della chiamata recente da gestire: ");
                    int indice = ali.nextInt();
                    ali.nextLine();  // Consuma il newline rimasto
                    System.out.print("Inserisci l'azione (nascondi/cancella): ");
                    String azione = ali.nextLine();
                    System.out.print("Inserisci la password: ");
                    inputPassword = ali.nextLine();
                    gestore.gestisciChiamateRecenti(azione, indice, inputPassword);
                    break;
                case 9:
                    System.out.print("Inserisci la password per visualizzare le chiamate nascoste: ");
                    inputPassword = ali.nextLine();
                    System.out.println("\nChiamate nascoste:");
                    gestore.visualizzaChiamateNascoste(inputPassword);
                    break;
                case 10:
                    System.out.print("Inserisci il numero telefonico del contatto nascosto da riportare in rubrica: ");
                    numeroTelefonico = ali.nextLine();
                    System.out.print("Inserisci la password: ");
                    inputPassword = ali.nextLine();
                    gestore.riportaInRubrica(numeroTelefonico, inputPassword);
                    break;
                case 11:
                    System.out.print("Inserisci il numero telefonico della chiamata nascosta da riportare nelle recenti: ");
                    numeroTelefonico = ali.nextLine();
                    System.out.print("Inserisci la password: ");
                    inputPassword = ali.nextLine();
                    gestore.riportaChiamataInRecenti(numeroTelefonico, inputPassword);
                    break;
                case 12:
                    System.out.println("Uscita...");
                    
                    return;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }
}
