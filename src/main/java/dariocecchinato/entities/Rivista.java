package dariocecchinato.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Rivista extends Publication {
    private Periodicita periodicita;

    //***********************************  Costruttori  ****************************************************


    public Rivista(String codiceISBN, String titolo, LocalDate annoPubblicazione, int numeroPagine, List<Prestito> prestiti, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine, prestiti);
        this.periodicita = periodicita;
    }

    public Rivista() {
    }

    //***********************************  Getter & Setter  ****************************************************


    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    //*********************************** Metodo ToString   ****************************************************


    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                '}';
    }
}
