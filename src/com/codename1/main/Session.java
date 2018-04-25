/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.main;

import Entity.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.xml.XMLParser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author hero
 */
public class Session {
    
    private String username;
    private String password;
    private User user;
    private static Session session;
    
    private Session()
    {
        
    }
    
    public User getConnectedUeser()
    {
        return new User();
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
    
    public void initSession()
    {
        try {
            ConnectionRequest con = new ConnectionRequest();
            con.setCookiesEnabled(true);
            con.setUrl("http://127.0.0.1:8888/piintegration/web/app_dev.php/login");
            NetworkManager.getInstance().addToQueueAndWait(con);
            XMLParser xmlParsr = new XMLParser();
            xmlParsr.setIncludeWhitespacesBetweenTags(true);
            String token =  xmlParsr.parse(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8")).getElementById("tokenInput").getAttribute("value");
            con = new ConnectionRequest("http://127.0.0.1:8888/piintegration/web/app_dev.php/login_check", true);
            con.addArgument("_username", username);
            con.addArgument("_password", password);
            con.addArgument("_csrf_token", token);
            NetworkManager.getInstance().addToQueueAndWait(con);
            System.out.print(con.getUrl());
        } catch (UnsupportedEncodingException ex) {
        }
    }
    
}
