/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *
 * @author hero
 */
public class UtilService {
    
    private static UtilService utilService;
    
    private UtilService(){}
    
    public static UtilService getInstance()
    {
        if(utilService == null) utilService = new UtilService();
        return utilService;
    }
    
    public Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/PiDevEsprit2018-Desktop/src/Images/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(64, 64);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
}
