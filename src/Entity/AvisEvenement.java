/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author hero
 */

public class AvisEvenement{


    private Integer id;
    private String contenu;
    private Date dateAvis;
    private short valeur;
    private int idEvenement;
    private int idUser;

    public AvisEvenement() {
    }

    public AvisEvenement(String contenu, int idEvenement) {
        this.contenu = contenu;
        this.idEvenement = idEvenement;
    }

    public AvisEvenement(String contenu) {
        this.contenu = contenu;
    }

    
    public AvisEvenement(String contenu, Date dateAvis, short valeur,int idEvenement,int idUser) {
        this.contenu = contenu;
        this.dateAvis = dateAvis;
        this.valeur = valeur;
        this.idEvenement = idEvenement;
        this.idUser = idUser;
    }

    public AvisEvenement(Integer id, String contenu, Date dateAvis, short valeur,int idEvenement,int idUser) {
        this.id = id;
        this.contenu = contenu;
        this.dateAvis = dateAvis;
        this.valeur = valeur;
        this.idEvenement = idEvenement;
        this.idUser = idUser;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(Date dateAvis) {
        this.dateAvis = dateAvis;
    }

    public short getValeur() {
        return valeur;
    }

    public void setValeur(short valeur) {
        this.valeur = valeur;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvisEvenement)) {
            return false;
        }
        AvisEvenement other = (AvisEvenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.AvisEvenement[ id=" + id + " ]";
    }
    
}
