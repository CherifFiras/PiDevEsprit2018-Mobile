/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author ASUS
 */
public class sujet {
    
        private int id_sujet;
    private String titre;
    private String contenu;
    private String datePublication;
    private String image;
    private Object Categorie;
    private Object User;

    public sujet(int id_sujet, String titre, String contenu, String datePublication, String image, Category Categorie , User user) {
        this.id_sujet = id_sujet;
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
        this.Categorie = Categorie;
        this.User = user;
    }

    
    
    public sujet(int id_sujet, String titre, String contenu, String datePublication) {
        this.id_sujet = id_sujet;
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public sujet(int id_sujet, String titre, String contenu, String datePublication, String image, Object Categorie, Object User) {
        this.id_sujet = id_sujet;
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
        this.Categorie = Categorie;
        this.User = User;
    }

    

    public sujet(String titre, String contenu, String datePublication, String image, Category Categorie, User user) {
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
        this.Categorie = Categorie;
        this.User = user;
    }

  
    

    public sujet(String titre, String contenu, String datePublication, String image, Category Categorie) {
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
        this.Categorie = Categorie;
    }

    public sujet(String titre, String contenu, String datePublication, String image) {
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
    }

    @Override
    public String toString() {
        return "sujet{" + "id_sujet=" + id_sujet + ", titre=" + titre + ", contenu=" + contenu + ", datePublication=" + datePublication + ", image=" + image + ", Categorie=" + Categorie + ", User=" + User + '}';
    }



    public Object getCategorie() {
        return Categorie;
    }

    public void setCategorie(Object Categorie) {
        this.Categorie = Categorie;
    }

    public Object getUser() {
        return User;
    }

    public void setUser(Object User) {
        this.User = User;
    }

    
    
    
    public sujet() {
    }






    public sujet(String titre, String contenu, String image) {
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
    }

    
    
    
    
    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    
}
