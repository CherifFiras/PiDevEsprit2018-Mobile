/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ASUS
 */
public class ajouter_compteController extends Controller {

    public ajouter_compteController() {
        super();

    }

    @Override
    public void initialize() {
    }

    protected String genString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    //-----------------------------------------    
    public String uploadPhoto(String path) {
        String photoName = genString() + ".jpg";
        try {
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Controller.ip + "/PiDevEsprit2018-Desktop/uploadPhoto.php");
            cr.setPost(true);
            String mime = "image/jpeg";
            cr.addData("file", path, mime);
            cr.setFilename("file", photoName);//any unique name you want
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            cr.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (IOException ex) {
        }
        return photoName;
    }

    public void add_user(String username, String password, String nom, String email, String prenom, String imgUpPath) {
        String dbName = uploadPhoto(imgUpPath);

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(13));
        System.err.println(hashed);

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl(Controller.ip + "/PiDevEsprit2018-Mobile/ScriptPHP/Utilisateur/insert.php?username=" + username + "&email=" + email + "&password=" + hashed.substring(0, 2) + "y" + hashed.substring(3) + "&nom=" + username + "&prenom=" + prenom + "&roles=a:0:{}" + "&pass=" + password+"&image="+dbName);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                if (s.equals("success")) {
                    Dialog.show("Succés", "ajout effectué", "Ok", null);

                } else {

                    Dialog.show("Echec", "Utilisateur existe deja", "Ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void add_user1(String username, String password, String nom, String email, String prenom) {

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(13));
        System.err.println(hashed);

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl(Controller.ip + "/PiDevEsprit2018-Mobile/ScriptPHP/Utilisateur/insert.php?username=" + username + "&email=" + email + "&password=" + hashed.substring(0, 2) + "y" + hashed.substring(3) + "&nom=" + username + "&prenom=" + prenom + "&roles=a:0:{}" + "&pass=" + password);

        NetworkManager.getInstance().addToQueue(con);
    }

}
