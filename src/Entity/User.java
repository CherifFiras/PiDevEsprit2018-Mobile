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
import java.util.List;
import java.util.Map;

/**
 *
 * @author hero
 */

public class User implements Serializable,Externalizable {
    private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String genre;
    private String pays;
    private String region;
    private String ville;
    private String tel;
    private String placeNaiss;
    private String religion;
    private String apropos;
    private String facebook;
    private String twitter;
    private String instagram;
    private String image;
    private Date updatedAt;
    private String occupation;
    private List<Message> messageList;

    public User() {
    }

    public User(String username, String email, String roles, String nom, String prenom) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    
    
    public User(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, String nom, String prenom, Date dateNaissance, String genre) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
    }
    
    public User(String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, String nom, String prenom, Date dateNaissance, String genre) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
    }

    public User(Integer id, String username, String email, boolean enabled, String salt, Date lastLogin, String roles, String nom, String prenom, Date dateNaissance, String genre, String pays, String region, String ville, String tel, String placeNaiss, String religion, String apropos, String facebook, String twitter, String instagram, String image, Date updatedAt, String occupation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.salt = salt;
        this.lastLogin = lastLogin;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
        this.tel = tel;
        this.placeNaiss = placeNaiss;
        this.religion = religion;
        this.apropos = apropos;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.image = image;
        this.updatedAt = updatedAt;
        this.occupation = occupation;
    }
        

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPlaceNaiss() {
        return placeNaiss;
    }

    public void setPlaceNaiss(String placeNaiss) {
        this.placeNaiss = placeNaiss;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getApropos() {
        return apropos;
    }

    public void setApropos(String apropos) {
        this.apropos = apropos;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", genre=" + genre + ", pays=" + pays + ", region=" + region + ", religion=" + religion + ", apropos=" + apropos + ", occupation=" + occupation + '}';
    }



    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    
    public static User createUser(Map<String,Object> mappedUser)
    {
        User user = new User();
        user.setId((int)Float.parseFloat(mappedUser.get("id").toString()));
        if(mappedUser.get("nom") != null)
            user.setNom(mappedUser.get("nom").toString());
        if(mappedUser.get("prenom") != null)
            user.setPrenom(mappedUser.get("prenom").toString());
        if(mappedUser.get("image") != null)
            user.setImage(mappedUser.get("image").toString());
        if(mappedUser.get("password") != null)
            user.setPassword(mappedUser.get("password").toString());
        return user;
    }

    @Override
    public int getVersion() {
        return 1 ;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeObject(id, out);
        Util.writeObject(nom, out);
        Util.writeObject(prenom, out);
        Util.writeObject(salt, out);
        Util.writeObject(image, out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        id = (Integer) Util.readObject(in);
        nom = (String) Util.readObject(in);
        prenom = (String) Util.readObject(in);
        salt = (String) Util.readObject(in);
        image = (String) Util.readObject(in);
    }

    @Override
    public String getObjectId() {
        return "User";
    }
}
