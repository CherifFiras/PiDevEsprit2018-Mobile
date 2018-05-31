/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;




/**
 *
 * @author ASUS
 */
public class Beblio {
    
    public static Integer ids ,id_user;
    public static String contenus;
    public static Integer idc;
    public static String recherche;
    public static String saltStr;
    public static String mail;
public static String image;
public static String image1;
public static String username;

    public static String getImage1() {
        return image1;
    }

    public static void setImage1(String image1) {
        Beblio.image1 = image1;
    }




    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Beblio.username = username;
    }




    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        Beblio.image = image;
    }





    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        Beblio.mail = mail;
    }

    
    
    
    
    public static String getSaltStr() {
        return saltStr;
    }

    public static void setSaltStr(String saltStr) {
        Beblio.saltStr = saltStr;
    }
    
    
    

    public static String getRecherche() {
        return recherche;
    }

    public static void setRecherche(String recherche) {
        Beblio.recherche = recherche;
    }
    
    
    
     public void setIds(int ids , String contenus , int id_user)  {
        this.ids = ids;
        this.contenus = contenus;
        this.id_user = id_user;
    }
     
     
     

    public static Integer getIds() {
        return ids;
    }

    public static void setIds(Integer ids) {
        Beblio.ids = ids;
    }

    public static Integer getId_user() {
        return id_user;
    }

    public static void setId_user(Integer id_user) {
        Beblio.id_user = id_user;
    }

    public static String getContenus() {
        return contenus;
    }

    public static void setContenus(String contenus) {
        Beblio.contenus = contenus;
    }

    public static Integer getIdc() {
        return idc;
    }

    public static void setIdc(Integer idc) {
        Beblio.idc = idc;
    }

    public Beblio() {
    }
    
    

    
}

