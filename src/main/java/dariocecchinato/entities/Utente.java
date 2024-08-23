package dariocecchinato.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroTessera;
    private String Nome;
    private String Cognome;
    private LocalDate dataDiNascita;


    @OneToMany(mappedBy = "utente")
    // Un solo Utente puo possedere più prestiti
    private List<Prestito> prestiti;

    //***********************************  Costruttori  ****************************************************

    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        Nome = nome;
        Cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public Utente() {
    }

    //***********************************  Getter & Setter  ****************************************************

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public long getNumeroTessera() {
        return numeroTessera;
    }


    //*********************************** Metodo ToString   ****************************************************

    @Override
    public String toString() {
        return "Utente{" +
                ", Nome='" + Nome + '\'' +
                ", Cognome='" + Cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
