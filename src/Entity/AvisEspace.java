/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author hero
 */
public class AvisEspace {
    private Integer id;
    private int nbrating;
    private int rating;
    private int idEspace;
    private int idUser;

    public AvisEspace() {
    }

    public AvisEspace(int nbrating, int rating,int idEspace,int idUser) {
        this.nbrating = nbrating;
        this.rating = rating;
        this.idEspace  = idEspace;
        this.idUser = idUser;
    }

    public AvisEspace(Integer id, int nbrating, int rating,int idEspace,int idUser) {
        this.id = id;
        this.nbrating = nbrating;
        this.rating = rating;
        this.idEspace  = idEspace;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNbrating() {
        return nbrating;
    }

    public void setNbrating(int nbrating) {
        this.nbrating = nbrating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(int idEspace) {
        this.idEspace = idEspace;
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
        if (!(object instanceof AvisEspace)) {
            return false;
        }
        AvisEspace other = (AvisEspace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.AvisEspace[ id=" + id + " ]";
    }
    
}
