package tn.esprit.spring.khaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Etudiant  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    private  Option op;




    @Override
    public String toString() {
        return "Etudiant{" +
                "idEtudiant=" + idEtudiant +
                ", prenomE='" + prenomE + '\'' +
                ", nomE='" + nomE + '\'' +
                ", op=" + op +
                ", departement=" + departement +
                ", equipes=" + equipes +
                ", contrats=" + contrats +
                '}';
    }

    public Etudiant() {
    }

    public Etudiant(Integer idEtudiant, String prenomE, String nomE, Option op, Departement departement, List<Equipe> equipes, List<Contrat> contrats) {
        this.idEtudiant = idEtudiant;
        this.prenomE = prenomE;
        this.nomE = nomE;
        this.op = op;
        this.departement = departement;
        this.equipes = equipes;
        this.contrats = contrats;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public Option getOp() {
        return op;
    }

    public void setOp(Option op) {
        this.op = op;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

            @ManyToOne
            @JsonIgnore
            private Departement departement;
    @ManyToMany
    @JsonIgnore
    private List<Equipe> equipes;
    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private List<Contrat> contrats;



}
