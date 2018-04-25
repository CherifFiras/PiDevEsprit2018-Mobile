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
public class Emploi {

    private Integer id;
    private String contenu;
    private String dateDebut;
    private String dateFin;
    private User idUser;

    public Emploi() {
    }

    public Emploi(String contenu, String dateDebut, String dateFin, User idUser) {
        this.contenu = contenu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idUser = idUser;
    }

    public Emploi(Integer id, String contenu, String dateDebut, String dateFin, User idUser) {
        this.id = id;
        this.contenu = contenu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
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
        if (!(object instanceof Emploi)) {
            return false;
        }
        Emploi other = (Emploi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Emploi{" + "id=" + id + ", contenu=" + contenu + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", idUser=" + idUser + '}';
    }


    
}
