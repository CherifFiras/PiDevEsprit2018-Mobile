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

public class Signaler {

    private Integer id;
    private String cause;
    private int idUser;

    public Signaler() {
    }

    public Signaler(String cause, int idUser) {
        this.cause = cause;
        this.idUser = idUser;
    }

    public Signaler(Integer id, String cause, int idUser) {
        this.id = id;
        this.cause = cause;
        this.idUser = idUser;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
        if (!(object instanceof Signaler)) {
            return false;
        }
        Signaler other = (Signaler) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Signaler{" + "id=" + id + ", cause=" + cause + ", idUser=" + idUser + '}';
    }


    
}
