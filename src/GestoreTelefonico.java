public class GestoreTelefonico {
    public CreaContatto[] contatti;
    public CreaContatto[] contattiNascosti;
    public CreaContatto[] chiamateRecenti;
    public CreaContatto[] chiamateNascoste;
    public String password;
    public int contatore;
    public int contatoreNascosti;
    public int contatoreChiamateRecenti;
    public int contatoreChiamateNascoste;

    public GestoreTelefonico(int dimensione) {
        contatti = new CreaContatto[dimensione];
        contattiNascosti = new CreaContatto[dimensione];
        chiamateRecenti = new CreaContatto[dimensione];
        chiamateNascoste = new CreaContatto[dimensione];
        contatore = 0;
        contatoreNascosti = 0;
        contatoreChiamateRecenti = 0;
        contatoreChiamateNascoste = 0;
    }

    public void aggiungiContatto(String nome, String cognome, String numeroTelefonico) {
        if (contatore < contatti.length) {
            contatti[contatore++] = new CreaContatto(nome, cognome, numeroTelefonico);
            System.out.println("Contatto aggiunto con successo.");
        } else {
            System.out.println("Rubrica piena!");
        }
    }

    public void spostaInNascosti(String numeroTelefonico, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        for (int i = 0; i < contatore; i++) {
            if (contatti[i].numeroTelefonico.equals(numeroTelefonico)) {
                if (contatoreNascosti < contattiNascosti.length) {
                    contattiNascosti[contatoreNascosti++] = contatti[i];
                    rimuoviContatto(numeroTelefonico);
                    System.out.println("Contatto spostato nella sezione nascosta.");
                } else {
                    System.out.println("Sezione nascosta piena!");
                }
                return;
            }
        }
        System.out.println("Contatto non trovato!");
    }

    public void riportaInRubrica(String numeroTelefonico, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        for (int i = 0; i < contatoreNascosti; i++) {
            if (contattiNascosti[i].numeroTelefonico.equals(numeroTelefonico)) {
                if (contatore < contatti.length) {
                    contatti[contatore++] = contattiNascosti[i];
                    rimuoviContattoNascosto(numeroTelefonico);
                    System.out.println("Contatto riportato nella rubrica.");
                } else {
                    System.out.println("Rubrica piena!");
                }
                return;
            }
        }
        System.out.println("Contatto nascosto non trovato!");
    }

    public void setPassword(String nuovaPassword) {
        password = nuovaPassword;
        System.out.println("Password impostata con successo.");
    }

    private boolean verificaPassword(String inputPassword) {
        return password != null && password.equals(inputPassword);
    }

    public void gestisciChiamate(String azione, String numeroTelefonico, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        switch (azione) {
            case "nascondi":
                spostaInNascosti(numeroTelefonico, inputPassword);
                break;
            case "cancella":
                rimuoviContatto(numeroTelefonico);
                break;
            default:
                System.out.println("Azione non valida!");
        }
    }

    public void eliminaContatto(String numeroTelefonico) {
        rimuoviContatto(numeroTelefonico);
    }

    private void rimuoviContatto(String numeroTelefonico) {
        for (int i = 0; i < contatore; i++) {
            if (contatti[i].numeroTelefonico.equals(numeroTelefonico)) {
                contatti[i] = contatti[--contatore];
                contatti[contatore] = null;
                System.out.println("Contatto eliminato con successo.");
                return;
            }
        }
        System.out.println("Contatto non trovato!");
    }

    private void rimuoviContattoNascosto(String numeroTelefonico) {
        for (int i = 0; i < contatoreNascosti; i++) {
            if (contattiNascosti[i].numeroTelefonico.equals(numeroTelefonico)) {
                contattiNascosti[i] = contattiNascosti[--contatoreNascosti];
                contattiNascosti[contatoreNascosti] = null;
                return;
            }
        }
    }

    public void visualizzaContatti() {
        if (contatore == 0) {
            System.out.println("Nessun contatto in rubrica.");
            return;
        }
        for (int i = 0; i < contatore; i++) {
            System.out.println((i + 1) + ". " + contatti[i].nome + " " + contatti[i].cognome + ": " + contatti[i].numeroTelefonico);
        }
    }

    public void visualizzaContattiNascosti(String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        if (contatoreNascosti == 0) {
            System.out.println("Nessun contatto nascosto.");
            return;
        }
        for (int i = 0; i < contatoreNascosti; i++) {
            System.out.println((i + 1) + ". " + contattiNascosti[i].nome + " " + contattiNascosti[i].cognome + ": " + contattiNascosti[i].numeroTelefonico);
        }
    }

    public void aggiungiChiamataRecente(String numeroTelefonico) {
        for (int i = 0; i < contatore; i++) {
            if (contatti[i].numeroTelefonico.equals(numeroTelefonico)) {
                if (contatoreChiamateRecenti < chiamateRecenti.length) {
                    chiamateRecenti[contatoreChiamateRecenti++] = contatti[i];
                    System.out.println("Chiamata aggiunta alle recenti.");
                } else {
                    System.out.println("Lista chiamate recenti piena!");
                }
                return;
            }
        }
        System.out.println("Contatto non trovato!");
    }

    public void visualizzaChiamateRecenti() {
        if (contatoreChiamateRecenti == 0) {
            System.out.println("Nessuna chiamata recente.");
            return;
        }
        for (int i = 0; i < contatoreChiamateRecenti; i++) {
            System.out.println((i + 1) + ". " + chiamateRecenti[i].nome + " " + chiamateRecenti[i].cognome + ": " + chiamateRecenti[i].numeroTelefonico);
        }
    }

    public void gestisciChiamateRecenti(String azione, int indice, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        if (indice < 1 || indice > contatoreChiamateRecenti) {
            System.out.println("Indice non valido!");
            return;
        }

        CreaContatto chiamata = chiamateRecenti[indice - 1];
        String numeroTelefonico = chiamata.numeroTelefonico;

        switch (azione) {
            case "nascondi":
                spostaChiamataInNascosti(chiamata, inputPassword);
                rimuoviChiamataRecente(indice - 1);
                break;
            case "cancella":
                rimuoviChiamataRecente(indice - 1);
                break;
            default:
                System.out.println("Azione non valida!");
        }
    }

    private void spostaChiamataInNascosti(CreaContatto chiamata, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        if (contatoreChiamateNascoste < chiamateNascoste.length) {
            chiamateNascoste[contatoreChiamateNascoste++] = chiamata;
            System.out.println("Chiamata spostata nella sezione nascosta.");
        } else {
            System.out.println("Sezione chiamate nascoste piena!");
        }
    }

    public void riportaChiamataInRecenti(String numeroTelefonico, String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        for (int i = 0; i < contatoreChiamateNascoste; i++) {
            if (chiamateNascoste[i].numeroTelefonico.equals(numeroTelefonico)) {
                if (contatoreChiamateRecenti < chiamateRecenti.length) {
                    chiamateRecenti[contatoreChiamateRecenti++] = chiamateNascoste[i];
                    rimuoviChiamataNascosta(numeroTelefonico);
                    System.out.println("Chiamata riportata nelle recenti.");
                } else {
                    System.out.println("Lista chiamate recenti piena!");
                }
                return;
            }
        }
        System.out.println("Chiamata nascosta non trovata!");
    }

    private void rimuoviChiamataRecente(int indice) {
        chiamateRecenti[indice] = chiamateRecenti[--contatoreChiamateRecenti];
        chiamateRecenti[contatoreChiamateRecenti] = null;
        System.out.println("Chiamata rimossa dalle recenti.");
    }

    private void rimuoviChiamataNascosta(String numeroTelefonico) {
        for (int i = 0; i < contatoreChiamateNascoste; i++) {
            if (chiamateNascoste[i].numeroTelefonico.equals(numeroTelefonico)) {
                chiamateNascoste[i] = chiamateNascoste[--contatoreChiamateNascoste];
                chiamateNascoste[contatoreChiamateNascoste] = null;
                return;
            }
        }
    }

    public void visualizzaChiamateNascoste(String inputPassword) {
        if (!verificaPassword(inputPassword)) {
            System.out.println("Password errata!");
            return;
        }

        if (contatoreChiamateNascoste == 0) {
            System.out.println("Nessuna chiamata nascosta.");
            return;
        }
        for (int i = 0; i < contatoreChiamateNascoste; i++) {
            System.out.println((i + 1) + ". " + chiamateNascoste[i].nome + " " + chiamateNascoste[i].cognome + ": " + chiamateNascoste[i].numeroTelefonico);
        }
    }
}


