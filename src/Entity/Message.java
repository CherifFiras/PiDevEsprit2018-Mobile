/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hero
 */

public class Message implements Serializable,Externalizable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String text;
    private Date date;
    private User receiver;
    private User sender;

    public Message() {
    }

    public Message(Integer id,User sender,User receiver,String text,Date date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = date;
    }

    public Message(Integer id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Message[ id=" + id + " ]";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeObject(id, out);
        Util.writeObject(sender, out);
        Util.writeObject(receiver, out);
        Util.writeObject(text, out);
        Util.writeObject(date, out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        id = (Integer) Util.readObject(in);
        sender =(User) Util.readObject(in);
        receiver =(User) Util.readObject(in);
        text = (String) Util.readObject(in);
        date = (Date) Util.readObject(in);
    }

    @Override
    public String getObjectId() {
        return "Message";
    }

}

