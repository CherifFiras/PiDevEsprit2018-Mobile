/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author hero
 */
public class NotifiableNotification implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private boolean seen;
    private Notifiable notifiableEntityId;
    private Notification notificationId;

    public NotifiableNotification() {
    }

    public NotifiableNotification(Integer id) {
        this.id = id;
    }

    public NotifiableNotification(Integer id, boolean seen) {
        this.id = id;
        this.seen = seen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Notifiable getNotifiableEntityId() {
        return notifiableEntityId;
    }

    public void setNotifiableEntityId(Notifiable notifiableEntityId) {
        this.notifiableEntityId = notifiableEntityId;
    }

    public Notification getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Notification notificationId) {
        this.notificationId = notificationId;
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
        if (!(object instanceof NotifiableNotification)) {
            return false;
        }
        NotifiableNotification other = (NotifiableNotification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NotifiableNotification[ id=" + id + " ]";
    }
    
}
