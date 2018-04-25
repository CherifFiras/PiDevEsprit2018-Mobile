/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Achrafoun
 */
public class Publication {

    private Integer id;
    private String contenu;
    private Date datePublication;
    private User idUser;

    public Publication() {
    }

    public Publication(String contenu, User idUser) {
        this.contenu = contenu;
        this.idUser = idUser;
    }
    
    public Publication(String contenu, Date datePublication, User idUser) {
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.idUser = idUser;
    }

    public Publication(Integer id, String contenu, Date datePublication, User idUser) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
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

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
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
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", contenu=" + contenu + ", datePublication=" + datePublication + ", idUser=" + idUser + '}';
    }


    
}
