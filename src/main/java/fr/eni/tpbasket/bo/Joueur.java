package fr.eni.tpbasket.bo;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class Joueur {

    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 1, max = 30)
    private String nom;
    @NotNull
    @Size(min = 1, max = 30)
    private String prenom;
    @Email
    private String email;
    @NotNull
    private Equipe nEquipe;

    public Joueur(Integer id, String nom, String prenom, String email, Equipe nEquipe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.nEquipe = nEquipe;
    }
    public Joueur(){

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Equipe getnEquipe() {
        return nEquipe;
    }

    public void setnEquipe(Equipe nEquipe) {
        this.nEquipe = nEquipe;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Joueur joueur)) return false;
        return Objects.equals(nom, joueur.nom);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", nEquipe=" + nEquipe +
                '}';
    }
}
