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

public class Album {

    private Integer id;
    private String url;
    private Date datePublication;
    private User idUser;

    public Album() {
    }

    public Album(String url, User idUser) {
        this.url = url;
        this.idUser = idUser;
    }

    
    public Album(Integer id, String url, Date datePublication, User idUser) {
        this.id = id;
        this.url = url;
        this.datePublication = datePublication;
        this.idUser = idUser;
    }

    public Album(String url, Date datePublication, User idUser) {
        this.url = url;
        this.datePublication = datePublication;
        this.idUser = idUser;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", url=" + url + ", datePublication=" + datePublication + ", idUser=" + idUser + '}';
    }
    
    
}
