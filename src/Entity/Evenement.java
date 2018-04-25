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
public class Evenement{
    private Integer id;
    private String imageEve;
    public int nbplaces;
    private Date dateEvenement;
    private String titre;
    private String description;
    private String titreCordination;

    public Evenement()  {
    }

    public Evenement(Date dateEvenement, String titre, String description, String titreCordination) {
        this.dateEvenement = dateEvenement;
        this.titre = titre;
        this.description = description;
        this.titreCordination = titreCordination;
    }

    public Evenement(Integer id, String imageEve, int nbplaces, Date dateEvenement, String titre, String description, String titreCordination) {
        this.id = id;
        this.imageEve = imageEve;
        this.nbplaces = nbplaces;
        this.dateEvenement = dateEvenement;
        this.titre = titre;
        this.description = description;
        this.titreCordination = titreCordination;
    }

    public Evenement(String imageEve, int nbplaces, Date dateEvenement, String titre, String description, String titreCordination) {
        this.imageEve = imageEve;
        this.nbplaces = nbplaces;
        this.dateEvenement = dateEvenement;
        this.titre = titre;
        this.description = description;
        this.titreCordination = titreCordination;
    }

    public Evenement(String imageEve, Date dateEvenement, String titre) {
        this.imageEve = imageEve;
        this.dateEvenement = dateEvenement;
        this.titre = titre;
    }

    

    public Evenement(Integer id, int nbplaces, Date dateEvenement, String titre, String description, String titreCordination) {
        this.id = id;
        this.nbplaces = nbplaces;
        this.dateEvenement = dateEvenement;
        this.titre = titre;
        this.description = description;
        this.titreCordination = titreCordination;
    }

    public Evenement(int nbplaces, Date dateEvenement, String titre, String description, String titreCordination) {
        this.nbplaces = nbplaces;
        this.dateEvenement = dateEvenement;
        this.titre = titre;
        this.description = description;
        this.titreCordination = titreCordination;
    }
    
    

    public Evenement(Integer id) {
        this.id = id;
    }

    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageEve() {
        return imageEve;
    }

    public void setImageEve(String imageEve) {
        this.imageEve = imageEve;
    }


    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitreCordination() {
        return titreCordination;
    }

    public void setTitreCordination(String titreCordination) {
        this.titreCordination = titreCordination;
    }

    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Evenement{" + "nbplaces=" + nbplaces + ", dateEvenement=" + dateEvenement + ", titre=" + titre + ", description=" + description + ", titreCordination=" + titreCordination + '}';
    }

    
    
}
