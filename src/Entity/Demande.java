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

public class Demande {
    private Integer id;
    private Date dateDemande;
    private User requester;
    private User acceptor;

    public Demande() {
    }

    public Demande(Date dateDemande,User requester,User acceptor) {
        this.dateDemande = dateDemande;
        this.requester = requester;
        this.acceptor = acceptor;
    }

    public Demande(Integer id, Date dateDemande,User requester,User acceptor) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.requester = requester;
        this.acceptor = acceptor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(User acceptor) {
        this.acceptor = acceptor;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Demande[ id=" + id + " ]";
    }
    
}
