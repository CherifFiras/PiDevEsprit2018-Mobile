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
public class favoris {
    
    
    public int id;
    private Object User;
    private Object Post;

    @Override
    public String toString() {
        return "favoris{" + "id=" + id + ", User=" + User + ", Post=" + Post + '}';
    }

    public favoris(int id, Object User, Object Post) {
        this.id = id;
        this.User = User;
        this.Post = Post;
    }

    public favoris(Object User, Object Post) {
        this.User = User;
        this.Post = Post;
    }

    
 
    


    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUser() {
        return User;
    }

    public void setUser(Object User) {
        this.User = User;
    }

    public Object getPost() {
        return Post;
    }

    public void setPost(Object Post) {
        this.Post = Post;
    }
    
    
    
    
    
}
