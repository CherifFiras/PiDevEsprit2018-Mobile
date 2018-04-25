/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author hero
 */
public class PhotoEspace {
    private Integer id;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private int idEspace;

    public PhotoEspace() {
    }

    public PhotoEspace(Integer id,String photo1,String photo2,String photo3,String photo4,int idEspace) {
        this.id = id;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.idEspace = idEspace;
    }
    
        public PhotoEspace(String photo1,String photo2,String photo3,String photo4,int idEspace) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.idEspace = idEspace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public int getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(int idEspace) {
        this.idEspace = idEspace;
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
        if (!(object instanceof PhotoEspace)) {
            return false;
        }
        PhotoEspace other = (PhotoEspace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PhotoEspace[ id=" + id + " ]";
    }
    
}
