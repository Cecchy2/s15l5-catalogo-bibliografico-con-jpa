package dariocecchinato;

import com.github.javafaker.Faker;
import dariocecchinato.Dao.*;
import dariocecchinato.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("s15l5");

    public static void main(String[] args) {

        //***********************************  Creo EntityManager e collego i Dao  *****************************
        EntityManager em = emf.createEntityManager();

        LibroDao ld = new LibroDao(em);
        RivistaDao rd = new RivistaDao(em);
        UtenteDao ud = new UtenteDao(em);
        PublicationDao pd = new PublicationDao(em);
        PrestitoDao prd = new PrestitoDao(em);

        //***********************************  Creo Libri Random con Faker  *****************************

        Supplier<Libro> randomLibroSupplier = () -> {
            Faker bookFaker = new Faker();
            Random random = new Random();
            String codiceISBN = bookFaker.code().isbn10();
            String titolo = bookFaker.book().title();
            int annoPubblicazione =
                    bookFaker.number().numberBetween(1900, 2024);
            int numeroPagine = bookFaker.number().numberBetween(100, 1000);
            String autore = bookFaker.book().author();
            Genere genere = Genere.values()[random.nextInt(Genere.values().length)];
            return new Libro(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere);
        };

        //***********************************  Salvo i Libri su DB  ****************************************
        /*for (int i = 0; i < 50; i++) {
            ld.save(randomLibroSupplier.get());
        }*/

        //***********************************  Creo Libri Random con Faker  *****************************

        Supplier<Rivista> randomRivistaSupplier = () -> {
            Faker bookFaker = new Faker();
            Random random = new Random();
            String codiceISBN = bookFaker.code().isbn10();
            String titolo = bookFaker.book().title();
            int annoPubblicazione = bookFaker.number().numberBetween(1980, 2024);
            int numeroPagine = bookFaker.number().numberBetween(20, 60);
            Periodicita periodicita = Periodicita.values()[random.nextInt(Periodicita.values().length)];
            return new Rivista(codiceISBN, titolo, annoPubblicazione, numeroPagine, periodicita);
        };

        //***********************************  Salvo le Riviste su DB  ****************************************
        /*for (int i = 0; i < 50; i++) {
            rd.save(randomRivistaSupplier.get());
        }*/

        //***********************************  Creo Utenti con Faker  *****************************

        Supplier<Utente> randomUtenteSupplier = () -> {
            Faker f = new Faker(Locale.ITALY);

            String nomeUtente = f.lebowski().character();
            String cognomeUtente = f.name().lastName();
            LocalDate annoDiNascita = LocalDate.of(f.number().numberBetween(1980, 2024),
                    f.number().numberBetween(1, 12),
                    f.number().numberBetween(1, 28));

            return new Utente(nomeUtente, cognomeUtente, annoDiNascita);
        };

        //***********************************  Salvo gi Utenti su DB  ****************************************

        /*for (int i = 0; i < 10; i++) {
            ud.save(randomUtenteSupplier.get());
        }*/

        //***********************************  Richiamo con un FindAll le altre lista da DB  *********************

        List<Publication> publicationsFromDb = pd.findAll();
        //publicationsFromDb.forEach(System.out::println);

        List<Utente> utentiFromDb = ud.findAll();
        //utentiFromDb.forEach(System.out::println);


        //***********************************  Creo  Prestiti con Faker  *****************************

        Supplier<Prestito> randomPrestitoSupplier = () -> {
            Faker prestitoFaker = new Faker();
            Random random = new Random();

            Utente utente = utentiFromDb.get(random.nextInt(utentiFromDb.size()));
            Publication elementoPrestato = publicationsFromDb.get(random.nextInt(publicationsFromDb.size()));
            LocalDate dataInizioPrestito = LocalDate.of(prestitoFaker.number().numberBetween(2022, 2024),
                    prestitoFaker.number().numberBetween(1, 12),
                    prestitoFaker.number().numberBetween(1, 28));
            LocalDate dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
            LocalDate dataRestituzioneEffettiva;
            if (random.nextBoolean()) {
                dataRestituzioneEffettiva = dataInizioPrestito.plusDays(prestitoFaker.number().numberBetween(15, 45));
                // Ho messo tra 15 e 45 perchè alcuni saranno restituiti prima dei 30 giorni , altri in ritardo.
            } else {
                dataRestituzioneEffettiva = null;  // Nessuna restituzione effettiva
            }

            return new Prestito(utente, elementoPrestato, dataInizioPrestito, dataRestituzionePrevista, dataRestituzioneEffettiva);
        };

        //***********************************  Salvo i Prestiti su DB  ****************************************
        /*for (int i = 0; i < 20; i++) {  // Creazione e salvataggio di 20 prestiti
            prd.save(randomPrestitoSupplier.get());
        }*/


        //***********************************  Delete libro from db  ****************************************
        //ld.delete("0004124456");

        //***********************************  FIND BY YEAR  ****************************************
        System.out.println("Ricerca per anno");
        List<Publication> libriPerAnno = pd.findByYear(1984);
        System.out.println(libriPerAnno);


        //***********************************  FIND BY AUTORE  ****************************************

        System.out.println("Ricerca per Autore");
        List<Publication> libriPerAutore = pd.findByAuthor("Millard Lynch");
        System.out.println(libriPerAutore);


        //***********************************  FIND BY TITOLO O PARTE DI ESSO ****************************************

        System.out.println("Ricerca per titolo");
        List<Publication> libriPerTitolo = pd.findByTitolo("ths of Glor");
        System.out.println(libriPerTitolo);


        //***********************************  FIND PUBLICATIONS ATTUALMENTE IN PRESTITO PER TESSERA ****************************************

        System.out.println("Ricerca prestito per tessera");
        List<Prestito> prestitoPerTessera = prd.findPublicationPrestateperTessera(2);
        System.out.println(prestitoPerTessera);


        //*********************************** FIND PRESTITI SCADUTI NON RESTITUITI ****************************************

        System.out.println("Ricerca prestiti scaduti non restituiti");
        List<Prestito> prestitiScadutiNonRestituiti = prd.findPrestitiScadutiNonRestituiti();
        System.out.println(prestitiScadutiNonRestituiti);
    }
}
