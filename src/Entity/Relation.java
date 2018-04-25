/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hero
 */
public class Relation {


    private Integer id;
    private Date dateRelation;
    private User requester;
    private User acceptor;

    public Relation() {
    }

    public Relation(Date dateRelation,User requester,User acceptor) {
        this.dateRelation = dateRelation;
        this.requester = requester;
        this.acceptor = acceptor;
    }

    public Relation(Integer id, Date dateRelation,User requester,User acceptor) {
        this.id = id;
        this.dateRelation = dateRelation;
        this.requester = requester;
        this.acceptor = acceptor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateRelation() {
        return dateRelation;
    }

    public void setDateRelation(Date dateRelation) {
        this.dateRelation = dateRelation;
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
        if (!(object instanceof Relation)) {
            return false;
        }
        Relation other = (Relation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Relation[ id=" + id + " ]";
    }
    
}
