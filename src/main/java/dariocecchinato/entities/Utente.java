package dariocecchinato.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String Nome;
    private String Cognome;
    private LocalDate dataDiNascita;
    private int numeroTessera;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti;

    //***********************************  Costruttori  ****************************************************


    public Utente(String nome, String cognome, int numeroTessera, LocalDate dataDiNascita) {
        Nome = nome;
        Cognome = cognome;
        this.numeroTessera = numeroTessera;
        this.dataDiNascita = dataDiNascita;
    }

    public Utente() {
    }

    //***********************************  Getter & Setter  ****************************************************


    public UUID getId() {
        return id;
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

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    //*********************************** Metodo ToString   ****************************************************


    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", Nome='" + Nome + '\'' +
                ", Cognome='" + Cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
