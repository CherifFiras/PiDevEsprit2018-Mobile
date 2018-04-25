/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author Achrafoun
 */

public class CentreInteret {

    private Integer id;
    private String type;
    private String contenu;
    private User idUser;

    public CentreInteret() {
    }

    public CentreInteret(String type, String contenu, User idUser) {
        this.type = type;
        this.contenu = contenu;
        this.idUser = idUser;
    }

    public CentreInteret(Integer id, String type, String contenu, User idUser) {
        this.id = id;
        this.type = type;
        this.contenu = contenu;
        this.idUser = idUser;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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
        if (!(object instanceof CentreInteret)) {
            return false;
        }
        CentreInteret other = (CentreInteret) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CentreInteret{" + "id=" + id + ", type=" + type + ", contenu=" + contenu + ", idUser=" + idUser + '}';
    }

    
}
