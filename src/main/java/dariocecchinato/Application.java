package dariocecchinato;

import com.github.javafaker.Faker;
import dariocecchinato.Dao.LibroDao;
import dariocecchinato.entities.Genere;
import dariocecchinato.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("s15l5");

    public static void main(String[] args) {

        //***********************************  Creo EntityManager e collego i Dao  *****************************
        EntityManager em = emf.createEntityManager();

        LibroDao ld = new LibroDao(em);

        //***********************************  Creo Lista Libri Randomizzata con Faker  *****************************
        //List<Libro> bookList = new ArrayList<>();

        Supplier<Libro> randomLibroSupplier = () -> {
            Faker bookFaker = new Faker();
            Random random = new Random();

            String codiceISBN = bookFaker.code().isbn10();
            String titolo = bookFaker.book().title();
            LocalDate annoPubblicazione = LocalDate.of(
                    bookFaker.number().numberBetween(1900, 2024),
                    bookFaker.number().numberBetween(1, 12),
                    bookFaker.number().numberBetween(1, 28));
            int numeroPagine = bookFaker.number().numberBetween(100, 1000);
            String autore = bookFaker.book().author();
            Genere genere = Genere.values()[random.nextInt(Genere.values().length)];

            return new Libro(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere);
        };

        for (int i = 0; i < 50; i++) {
            //bookList.add(randomLibroSupplier.get());
            ld.save(randomLibroSupplier.get());
        }
        /*System.out.println("*****************************  LIBRI  *****************************");
        bookList.forEach(System.out::println);*/

        //***********************************  Salvo i Libri su DB  ****************************************


    }
}
