/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author souhaib
 */
@Entity
@Table(name = "CITOYEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citoyen.findAll", query = "SELECT c FROM Citoyen c")
    , @NamedQuery(name = "Citoyen.findByIdcitoyen", query = "SELECT c FROM Citoyen c WHERE c.idcitoyen = :idcitoyen")
    , @NamedQuery(name = "Citoyen.findByNom", query = "SELECT c FROM Citoyen c WHERE c.nom = :nom")
    , @NamedQuery(name = "Citoyen.findByPrenom", query = "SELECT c FROM Citoyen c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Citoyen.findByIdNomPrenom", query = "SELECT c FROM Citoyen c WHERE c.idcitoyen = :idcitoyen AND c.nom = :nom AND c.prenom = :prenom")
    , @NamedQuery(name = "Citoyen.findByDatenaissance", query = "SELECT c FROM Citoyen c WHERE c.datenaissance = :datenaissance")
    , @NamedQuery(name = "Citoyen.findByAdresse", query = "SELECT c FROM Citoyen c WHERE c.adresse = :adresse")
    , @NamedQuery(name = "Citoyen.findByCodepostal", query = "SELECT c FROM Citoyen c WHERE c.codepostal = :codepostal")
    , @NamedQuery(name = "Citoyen.findByLocalite", query = "SELECT c FROM Citoyen c WHERE c.localite = :localite")
    , @NamedQuery(name = "Citoyen.findByPays", query = "SELECT c FROM Citoyen c WHERE c.pays = :pays")
    , @NamedQuery(name = "Citoyen.updateAdresse", query = "Update Citoyen c SET c.adresse = :adresse WHERE c.idcitoyen=:idcitoyen")
    , @NamedQuery(name = "Citoyen.findByNationalite", query = "SELECT c FROM Citoyen c WHERE c.nationalite = :nationalite")})
public class Citoyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCITOYEN")
    private Integer idcitoyen;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "PRENOM")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "DATENAISSANCE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date datenaissance;
    @Basic(optional = false)
    @Column(name = "ADRESSE")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "CODEPOSTAL")
    private int codepostal;
    @Basic(optional = false)
    @Column(name = "LOCALITE")
    private String localite;
    @Basic(optional = false)
    @Column(name = "PAYS")
    private String pays;
    @Basic(optional = false)
    @Column(name = "NATIONALITE")
    private String nationalite;

    public Citoyen() {
    }

    public Citoyen(Integer idcitoyen) {
        this.idcitoyen = idcitoyen;
    }

    public Citoyen(Integer idcitoyen, String nom, String prenom, Date datenaissance, String adresse, int codepostal, String localite, String pays, String nationalite) {
        this.idcitoyen = idcitoyen;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.localite = localite;
        this.pays = pays;
        this.nationalite = nationalite;
    }

    public Integer getIdcitoyen() {
        return idcitoyen;
    }

    public void setIdcitoyen(Integer idcitoyen) {
        this.idcitoyen = idcitoyen;
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

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcitoyen != null ? idcitoyen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citoyen)) {
            return false;
        }
        Citoyen other = (Citoyen) object;
        if ((this.idcitoyen == null && other.idcitoyen != null) || (this.idcitoyen != null && !this.idcitoyen.equals(other.idcitoyen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Citoyen[ idcitoyen=" + idcitoyen + " ]";
    }
    
}
