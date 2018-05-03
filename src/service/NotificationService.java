/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Demande;
import Entity.Notification;
import Entity.Relation;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hero
 */
public class NotificationService {
    private static NotificationService notificationService;
    private NotificationService(){}
    
    public static NotificationService getInstance()
    {
        if(notificationService == null) notificationService = new NotificationService();
        return notificationService;
    }
    
    public List<Notification> getNotifications()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        List<Notification> notifications = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/getmessagenotifications");
        request.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                List<Map<String, Object>> mappedList = (List<Map<String, Object>>) jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray())).get("root");
                for(Map<String,Object> obj:mappedList)
                {
                    Map<String,Object> mappedNotification = (Map<String,Object>) obj.get("0");
                    Notification notification = new Notification();
                    notification.setId((int)Float.parseFloat(mappedNotification.get("id").toString()));
                    notification.setSubject(mappedNotification.get("subject").toString());
                    notification.setMessage(mappedNotification.get("message").toString());
                    notification.setLink(mappedNotification.get("link").toString());
                    notification.setDate(dateFormat.parse(mappedNotification.get("date").toString()));
                    notifications.add(notification);
                }
            } catch (IOException | ParseException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return notifications;
    }
    
    public List<Demande> getDemandeNotifications()
    {
        List<Demande> demandes = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/getdemandenotifications");
        request.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                List<Map<String, Object>> mappedList = (List<Map<String, Object>>) jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray())).get("root");
                for(Map<String,Object> obj:mappedList)
                {
                    Demande demande = new Demande();
                    demande.setId((int)Float.parseFloat(obj.get("id").toString()));
                    User requester = User.createUser((Map<String, Object>) obj.get("sender"));
                    demande.setRequester(requester);
                    demandes.add(demande);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return demandes;
    }
}
