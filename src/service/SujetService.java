/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entity.sujet;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Session;
import com.codename1.ui.Dialog;

import com.codename1.ui.events.ActionListener;
import com.codename1.util.regex.StringReader;
import controller.Beblio;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;





/**
 *
 * @author ASUS
 */
public class SujetService {
    public ArrayList<sujet> getList2() {
        ArrayList<sujet> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/piIntegration/web/app_dev.php/forum/forum/getall/"+Beblio.getIdc());
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
           

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        sujet task = new sujet();
       float id = Float.parseFloat(obj.get("id").toString());
                        task.setId_sujet((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setContenu(obj.get("contenu").toString());
                        task.setImage(obj.get("image").toString());
                        
  
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public int getList3() {
        ArrayList<sujet> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piIntegration/web/app_dev.php/forum/forum/mobile_calcule/"+Beblio.getIds());
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();   
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        sujet task = new sujet();
       float id = Float.parseFloat(obj.get("id").toString());
                        task.setId_sujet((int) id);
                        listTasks.add(task);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        int i =0;
        for (sujet r : listTasks){
        i = i+1;
        }
        return i;
    }

    public void ajouter(String titre, String description) {
        Session.getInstance().getConnectedUser().getId();
        System.err.println(Beblio.getId_user());
        ConnectionRequest con=new ConnectionRequest();
  
        con.setUrl("http://127.0.0.1/piIntegration/web/app_dev.php/forum/forum/ajouter_mobile/"+Beblio.getIdc() + "/"+Beblio.getId_user() + "/" + titre + "/" + description); 
        NetworkManager.getInstance().addToQueue(con);
        System.err.println(con.getUrl().toString());
        Dialog.show("Error", "Sujet Ajouté", "Ok", null);
    }  

    public ArrayList<sujet> getList4() {
         ArrayList<sujet> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piIntegration/web/app_dev.php/forum/forum/tri/"+Beblio.getIdc());
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
           

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        sujet task = new sujet();
       float id = Float.parseFloat(obj.get("id").toString());
                        task.setId_sujet((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setContenu(obj.get("contenu").toString());
                        task.setImage(obj.get("image").toString());
  
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}
