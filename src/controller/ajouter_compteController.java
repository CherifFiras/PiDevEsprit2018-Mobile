/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ASUS
 */
class ajouter_compteController extends Controller {
    
        public ajouter_compteController  ()
    {
        super();
         
    }

    @Override
    public void initialize() {
    }
    
    public void add_user(String username , String password , String nom, String email , String prenom) {
      
String hashed = BCrypt.hashpw(password, BCrypt.gensalt(13));
        System.err.println(hashed);
        
        ConnectionRequest con=new ConnectionRequest();
  
        con.setUrl("http://localhost/PiDevEsprit2018-Mobile/ScriptPHP/Utilisateur/insert.php?username=" + username+"&email="+email+"&password=" +hashed.substring(0, 2)+"y"+hashed.substring(3)+"&nom=" + username +"&prenom="+prenom+"&roles=a:0:{}"+"&pass="+password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData(); 
                    String s = new String(data);  
                    if (s.equals("success")) {
                        Dialog.show("Succés", "ajout effectué", "Ok", null);

                 
                    } 
                    else {
                      
                        Dialog.show("Echec", "Utilisateur existe deja", "Ok", null);
                        }
                }
            });
        NetworkManager.getInstance().addToQueue(con);
    }
     
     
        
        
    }


   



