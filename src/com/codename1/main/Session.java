/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.main;

import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.xml.XMLParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author hero
 */
public class Session {
    
    private String username;
    private String password;
    private static Session session;
    private User user;
    
    private Session()
    {
        
    }
    
    public User getConnectedUser()
    {
        if(user!= null) return user;
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/user");
        List<User> list = new ArrayList<>();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                list.add(User.createUser(mapUser));
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        user = list.get(0);
        return list.get(0);
    }
    
    public static Session getInstance()
    {
        if(session == null) session = new Session();
        return session;
    }
    
    public void setParameters(String username,String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public boolean initSession()
    {
        User u = getUserByUsername(username);
        String hashed =u.getPassword().substring(0, 2)+"a"+u.getPassword().substring(3);
        if (!BCrypt.checkpw(password,hashed)) return false;
        try {
            ConnectionRequest con = new ConnectionRequest();
            con.setCookiesEnabled(true);
            con.setUrl(Controller.ip+"/piintegration/web/app_dev.php/login");
            NetworkManager.getInstance().addToQueueAndWait(con);
            XMLParser xmlParsr = new XMLParser();
            xmlParsr.setIncludeWhitespacesBetweenTags(true);
            String token =  xmlParsr.parse(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8")).getElementById("tokenInput").getAttribute("value");
            con = new ConnectionRequest(Controller.ip+"/piintegration/web/app_dev.php/login_check", true);
            con.addArgument("_username", username);
            System.out.println(u.getPassword());
            con.addArgument("_password", u.getPassword());
            con.addArgument("_csrf_token", token);
            NetworkManager.getInstance().addToQueueAndWait(con);
            if(con.getUrl().equals(Controller.ip+"/piintegration/web/app_dev.php/"))
                return true;
        } catch (UnsupportedEncodingException ex) {
        }
        return false;
    }
    
    private User getUserByUsername(String username)
    {   
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Controller.ip+"/piintegration/web/app_dev.php/userbyusername");
        con.addArgument("username", username);
        List<User> list = new ArrayList<>();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                List<Map<String, Object>> mapList = (List<Map<String, Object>>) jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray())).get("root");
                Map<String, Object> mapUser = mapList.get(0);
                list.add(User.createUser(mapUser));
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        user = list.get(0);
        return list.get(0);
    }
    
	public User getFullConnectedUser()
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/user");
        User user = new User();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String, Object> mapUser =  jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                user.setId((int)Float.parseFloat(mapUser.get("id").toString()));
                user.setNom(mapUser.get("nom").toString());
                user.setPrenom(mapUser.get("prenom").toString());                
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    user.setDateNaissance(format.parse(mapUser.get("dateNaissance").toString()));
                } catch (ParseException ex) {                           
                }                
                user.setGenre(mapUser.get("genre").toString());
                user.setEmail(mapUser.get("email").toString());
                user.setTel(mapUser.get("tel").toString());
                user.setPays(mapUser.get("pays").toString());
                user.setVille(mapUser.get("ville").toString());
                user.setRegion(mapUser.get("region").toString());
                user.setPlaceNaiss(mapUser.get("placeNaiss").toString());
                user.setReligion(mapUser.get("relegion").toString());
                user.setApropos(mapUser.get("apropos").toString());
                if(mapUser.get("facebook") != null)
                    user.setFacebook(mapUser.get("facebook").toString());
                if(mapUser.get("twitter") != null)
                    user.setTwitter(mapUser.get("twitter").toString());
                if(mapUser.get("instagram") != null)
                    user.setInstagram(mapUser.get("instagram").toString());
                user.setOccupation(mapUser.get("occupation").toString());                
                try {
                    user.setLastLogin(format.parse(mapUser.get("lastLogin").toString()));
                } catch (ParseException ex) {                           
                } 
                
                user.setImage(mapUser.get("image").toString());
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }
    
}
